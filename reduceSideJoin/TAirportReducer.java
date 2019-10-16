package reduceSideJoin;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.Iterator;
import org.apache.hadoop.io.LongWritable;

public class TAirportReducer extends Reducer<Text, IntWritable, Text, LongWritable> {
    @Override
    protected void reduce(TAirportKeyComparable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
       Iterator iter = values.iterator();
       int min = 0;
       float max = 0;
       while(iter.hasNext()) {
           iter.next();
           count++;
       }
       context.write(key, new LongWritable(count));
    }
}
