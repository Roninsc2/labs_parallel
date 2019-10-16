package reduceSideJoin;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.util.Iterator;

public class TAirportReducer extends Reducer<Text, IntWritable, Text, LongWritable> {
    @Override
    protected void reduce(TAirportKeyComparable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
       Iterator iter = values.iterator();
       float min = 1000000;
       float max = 0;
       float sum = 0;
       int count = 0;
       iter.next();
       while(iter.hasNext()) {
           iter.next();
           float delay = Float.parseFloat(iter.toString());
           if (delay >= 0) {
               min = Float.min(min, delay);
               max = Float.max(max, delay);
               sum += delay;
           }
           count++;

       }
       context.write(new );
    }
}
