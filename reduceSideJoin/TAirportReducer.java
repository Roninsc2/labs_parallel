package reduceSideJoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.Iterator;

public class TAirportReducer extends Reducer<TAirportKeyComparable, Text, IntWritable, Text> {
    @Override
    protected void reduce(TAirportKeyComparable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
       Iterator<Text> iter = values.iterator();
       float min = 1000000;
       float max = 0;
       float sum = 0;
       int count = 0;
       iter.next();
       while(iter.hasNext()) {
           Text x = iter.next();
           float delay = Float.parseFloat(x.toString());
           if (delay >= 0) {
               min = Float.min(min, delay);
               max = Float.max(max, delay);
               sum += delay;
           }
           count++;
       }
       if (count > 0) {
           if (min == 1000000) {

           }
           String text = min + " " + max + " " + sum/count;
           context.write(new IntWritable(key.getKey()), new Text(text));
       }
    }
}
