package reduceSideJoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;


public class TKeyGroupingComparator extends WritableComparator
{
    public TKeyGroupingComparator() {
    }
    
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
        int a = readInt(b1, s1);
        int b = readInt(b2, s2);
        return Integer.compare(a, b);
    }

    public int	compare(WritableComparable a, WritableComparable b) {
        TAirportKeyComparable x = (TAirportKeyComparable) a;
        TAirportKeyComparable y = (TAirportKeyComparable) b;
        return x.compareKeys(y);
    }
}
