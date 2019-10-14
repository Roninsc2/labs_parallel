package reduceSideJoin;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
	String[] words = line.replaceAll("[^\\p{L}\\p{Nd}]+", " ").toLowerCase().replaceAll("[\\s]{2,}", "").split(" ");
	for (String word : words) {
		context.write(new Text(word), new IntWritable(1));
	}
    }
}
