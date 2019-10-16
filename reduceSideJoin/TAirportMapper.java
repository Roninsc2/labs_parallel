package reduceSideJoin;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TAirportMapper extends Mapper<LongWritable, Text, TAirportKeyComparable> {
    @Override
    protected void map(LongWritable key, Context context) throws IOException, InterruptedException {
        TCsvAirPortReader reader = new TCsvAirPortReader(value);
        for (int i = 0; i < reader.getDataSize(); i++) {
            int key_val = Integer.parseInt(reader.getId(i));
            context.write(new TAirportKeyComparable(key_val, 0));
        }
    }
}
