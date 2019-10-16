package reduceSideJoin;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
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
        FileInputFormat.addInputPath(job, new Path(args[0]), );
        FileOutputFormat.setOutputPath(job, new Path(args[1])); 
        job.setMapperClass(TAirportMapper.class);
        job.setReducerClass(TAirportReducer.class);
        job.setOutputKeyClass(Text.class); 
        job.setOutputValueClass(IntWritable.class); 
        job.setNumReduceTasks(2); 
        System.exit(job.waitForCompletion(true) ? 0 : 1); 
    } 
} 

