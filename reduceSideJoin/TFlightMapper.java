package reduceSideJoin;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TFlightMapper extends Mapper<LongWritable, Text, TAirportKeyComparable, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        TCsvFlightReader reader = new TCsvFlightReader(value);
        for (int i = 0; i < reader.getDataSize(); i++) {
            int key = Integer.parseInt(reader.getId(i));
            String delay = reader.getDelay(i);
            context.write(new TAirportKeyComparable(key, 1), new Text(delay));
        }
    }
}
