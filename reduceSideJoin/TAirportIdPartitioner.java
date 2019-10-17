package reduceSideJoin;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

public class TAirportIdPartitioner extends Partitioner<TAirportKeyComparable, Text>
{
    public int getPartition(TAirportKeyComparable key, Text text, int numTasks) {
        return key.hashCode() % numTasks;
    }
}
