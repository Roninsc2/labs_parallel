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

    public void write(DataOutput out) throws IOException {
        out.writeInt(key);
        out.writeLong(type);
    }

    public void readFields(DataInput in) throws IOException {
        key = in.readInt();
        type = in.readInt();
    }

    public int compareTo(MyWritableComparable o) {
        int thisValue = this.value;
        int thatValue = o.value;
        return (thisValue < thatValue ? -1 : (thisValue==thatValue ? 0 : 1));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + counter;
        result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
        return result
    }
}
