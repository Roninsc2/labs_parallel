package reduceSideJoin;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TFlightMapper extends Mapper<LongWritable, Text, TAirportKeyComparable, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        TCsvFlightReader reader = new TCsvFlightReader(value);
        int key_val = reader.getId();
        if (key_val != -1) {
            context.write(new TAirportKeyComparable(key_val, 1), new Text(reader.getDelay()));
        }
    }
}
