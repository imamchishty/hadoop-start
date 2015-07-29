package com.shedhack.hadoop.starter.mapreduce;

import com.shedhack.hadoop.starter.model.OdiBattingModel;
import com.shedhack.hadoop.starter.parser.OdiBattingParser;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by ichishty on 28/07/15.
 */
public class RunsAgainstEachCountryMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    enum RECORD_STATUS {
        GOOD,
        BAD;
    }

    /**
     * Aim for the mapper is to return the opponent and the number of runs scored by freddie.
     * Eventually via the reducer we will have a list of countries and the total number of runs Flintoff scored against them.
     *
     * NLineInputFormat implements InpuInputFormat<LongWritable,Text>
     */
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        try {

            // Convert the string (csv) record to the correct model.
            OdiBattingModel model = OdiBattingParser.parse(value.toString());

            // extract out the opponent and the runs scored
            context.write(new Text(model.getOpposition()), new IntWritable(model.getRuns()));

            // mark this as a good request
            context.getCounter(RECORD_STATUS.GOOD).increment(1);
        }
        catch(Exception ex) {
            context.getCounter(RECORD_STATUS.BAD).increment(1);
            throw ex;
        }
    }
}
