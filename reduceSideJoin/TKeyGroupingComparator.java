package reduceSideJoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


public class TKeyGroupingComparator extends WritableComparator
{
    public TKeyGroupingComparator() {
    }

    public int	compare(WritableComparable a, WritableComparable b) {
        TAirportKeyComparable x = (TAirportKeyComparable) a;
        TAirportKeyComparable y = (TAirportKeyComparable) b;
        return x.compareKeys(y);
    }
}
