package reduceSideJoin;

import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.io.Text;

public class TAirportIdPart extends Partitioner<TAirportKeyComparable, Text>
{
    public int getPartition(TAirportKeyComparable key, Text text, int numTasks) {
        return key.getHa
    }
}
