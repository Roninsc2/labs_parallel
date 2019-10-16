package reduceSideJoin;

import org.apache.hadoop.io.WritableComparable;
import  java.io.DataInput;
import  java.io.DataOutput;
import  java.io.IOException;


public class TAirportKeyComparable implements WritableComparable<TAirportKeyComparable>
{
    private int key;
    private int type;

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
        out.writeLong(type);
    }

    
    public void readFields(DataInput in) throws IOException {
        key = in.readInt();
        type = in.readInt();
    }

    @Override
    public int compareTo(TAirportKeyComparable o) {
        int diff = Integer.compare(this.key, o.key);
        if (diff == 0) {
            return Integer.compare(this.type, o.type);
        }
        return diff;
    }

    public int hashCode() {
        return Integer.hashCode(key);
    }
}
