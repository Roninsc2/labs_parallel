package reduceSideJoin;

import org.apache.hadoop.mapreduce.Partitioner;
import org.w3c.dom.Text;

public class TAirportIdPart extends Partitioner<TAirportKeyComparable, Text>
{
    public int getPartition(TAirportKeyComparable key, Text text, int numTasks) {
        return key.hashCode() % numTasks;
    }
}
