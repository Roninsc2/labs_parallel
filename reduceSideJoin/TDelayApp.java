package reduceSideJoin;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TDelayApp {
    public static void main(String[] args) throws Exception { 
        if (args.length != 3) {
            System.err.println("Usage: TDelayApp <input airport path> <input flight path> <output path>");
            System.exit(-1);
        }
        Job job = Job.getInstance(); 
        job.setJarByClass(TDelayApp.class);
        job.setJobName("Airports delay");
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, TAirportMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, TFlightMapper.class);
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        job.setPartitionerClass(TAirportIdPart.class);
        job.setReducerClass(TAirportReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        job.setNumReduceTasks(2); 
        System.exit(job.waitForCompletion(true) ? 0 : 1); 
    } 
} 

