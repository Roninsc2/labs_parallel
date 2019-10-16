package reduceSideJoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import  java.io.DataInput;
import  java.io.DataOutput;
import  java.io.IOException;


public class TKeyGroupingComparator extends WritableComparator
{
    public TKeyGroupingComparator() {

    }
    int	compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2)
    Optimization hook.
    int	compare(Object a, Object b)

    int	compare(WritableComparable a, WritableComparable b) {
        TAirportKeyComparable x = 
    }
    Compare two WritableComparables.
}
