package reduceSideJoin;

import org.apache.hadoop.io.WritableComparable;
import  java.io.DataInput;
import  java.io.DataOutput;
import  java.io.IOException;


public class TAirportKeyComparable implements WritableComparable<TAirportKeyComparable>
{
    private int key;
    private int type;

    public TAirportKeyComparable() {
        key = 0;
        type = 0;
    }

    public  TAirportKeyComparable(int a, int b) {
        key = a;
        type = b;
    }

    public int compareKeys(TAirportKeyComparable o) {
        return Integer.compare(key, o.key);
    }

    public int getKey() {
        return key;
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(key);
        out.writeInt(type);
    }

    public void readFields(DataInput in) throws IOException {
        key = in.readInt();
        type = in.readInt();
    }

    public int compareTo(TAirportKeyComparable o) {
        int diff = Integer.compare(key, o.key);
        if (diff == 0) {
            return Integer.compare(type, o.type);
        }
        return diff;
    }

    public int hashCode() {
        return Integer.hashCode(key);
    }
}
