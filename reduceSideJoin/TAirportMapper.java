package reduceSideJoin;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TAirportMapper extends Mapper<LongWritable, Text, TAirportKeyComparable, Text> {
    private static final int INVALID_KEY = -1;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        TCsvAirportReader reader = new TCsvAirportReader(value);
        int keyVal = reader.getId();
        if (keyVal != INVALID_KEY) {
            context.write(new TAirportKeyComparable(keyVal, 0), new Text());
        }
    }
}
