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

    public int	compare(WritableComparable a, WritableComparable b) {
        TAirportKeyComparable x = (TAirportKeyComparable) a;
        TAirportKeyComparable y = (TAirportKeyComparable) b;
        return x.compareKeys(y);
    }

}
