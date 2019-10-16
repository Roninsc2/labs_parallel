package reduceSideJoin;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TAirportMapper extends Mapper<LongWritable, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        TCsvAirPortReader reader = new TCsvAirPortReader(value);
        for (int i = 0; i < reader.getDataSize(); i++) {
            int key = Integer.parseInt(reader.getId(i));
            context.write(key);
        }
    }
}
