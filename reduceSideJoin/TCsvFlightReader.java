package reduceSideJoin;

import org.apache.hadoop.io.Text;

public class TCsvFlightReader {
    int id;
    String delay;

    public TCsvFlightReader(Text text) {
        String[] lines = text.toString().split(",");
        if (lines[14].length() == 5 && lines[17].length() > 0) {
            id = Integer.parseInt(lines[14]);
            delay = lines[17];
        } else {
            id = -1;
        }
    }

    public int getId() {
        return id;
    }

    public String getDelay() {
        return delay;
    }
}
