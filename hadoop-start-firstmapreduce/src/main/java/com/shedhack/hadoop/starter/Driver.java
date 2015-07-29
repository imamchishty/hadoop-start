package com.shedhack.hadoop.starter;

import com.shedhack.hadoop.starter.mapreduce.RunsAgainstEachCountryMapper;
import com.shedhack.hadoop.starter.mapreduce.RunsAgainstEachCountryReducer;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.UUID;

/**
 * Simple MapReduce example which can be used cut your teeth into Hadoop.
 *
 * Created by ichishty on 28/07/15.
 */
public class Driver extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.err.printf("Usage: %s [generic options] <input> <output>\n",
                    getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }

        // Get a job instance, previous 'new Job(...)' has been deprecated
        Job job = Job.getInstance(getConf(), "Runs Count");

        // The main class which is the entry point for the application
        job.setJarByClass(Driver.class);

        // Mapper
        job.setMapperClass(RunsAgainstEachCountryMapper.class);

        // Reducer
        job.setReducerClass(RunsAgainstEachCountryReducer.class);

        // The output type from the Mapper must match the input type for the reducer
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // Final types which will be in the output file
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        /**
         *  InputFormat has several implementations, I've decided to use NLineInputFormat
         *  refer to https://hadoop.apache.org/docs/current/api/org/apache/hadoop/mapred/FileInputFormat.html
         *
         *  Options:
         *
         *  {@link org.apache.hadoop.mapred.FixedLengthInputFormat}
         *  {@link org.apache.hadoop.mapred.KeyValueTextInputFormat}
         *  {@link org.apache.hadoop.mapred.MultiFileInputFormat}
         *  {@link org.apache.hadoop.mapred.lib.NLineInputFormat}
         *  {@link org.apache.hadoop.mapred.SequenceFileInputFormat}
         *  {@link org.apache.hadoop.mapred.TextInputFormat}
         */
        job.setInputFormatClass(NLineInputFormat.class);

        // Input path to the file, passed in via the command line arg, file is /data/flintoff-batting-odi.txt
        // Had I used FileOutputFormat: FileInputFormat.addInputPath(job, new Path(args[0]));
        NLineInputFormat.addInputPath(job, new Path(args[0]));

        // Each mapper will get 5 lines each - not necessary but has been added to show how to add conf props.
        job.getConfiguration().setInt("mapreduce.input.lineinputformat.linespermap", 5);

        // The output path must not already exist. For the sake of simplicity I'm adding a sub-dir
        FileOutputFormat.setOutputPath(job, new Path(args[1] + "/" + UUID.randomUUID().toString()));

        // Wait for the job to complete before we exit
        return job.waitForCompletion(true) ? 0 : -1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Driver(), args);
        System.exit(exitCode);
    }

}
