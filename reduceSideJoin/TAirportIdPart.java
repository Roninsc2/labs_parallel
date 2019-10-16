package reduceSideJoin;

import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.io.Text;

public class TAirportIdPart implements Partitioner<TAirportKeyComparable, Text> {
    public int getPartition(TAirportKeyComparable key, Text text, int numTasks) {
        return key.hashCode() % numTasks;
    }
}
