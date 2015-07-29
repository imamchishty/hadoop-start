package com.shedhack.hadoop.starter.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reduces all of the results from the multiple mappers.
 */
public class RunsAgainstEachCountryReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    /**
     * Key in this example is the country that Freddie played against.
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)  throws IOException, InterruptedException {

        int totalRuns = 0;

        for (IntWritable value : values) {
            totalRuns += value.get();
        }

        context.write(key, new IntWritable(totalRuns));
    }

}
