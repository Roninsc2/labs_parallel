package reduceSideJoin;

import org.apache.hadoop.io.Text;

public class TCsvAirportReader {
    private int id;

    public TCsvAirportReader(Text text) {
        String fileData = text.toString();
        String[] lines = fileData.split( ",");
        if (lines[0].length() == 7) {
            id = Integer.parseInt(lines[0].substring(1, 6));
        } else {
            id = -1;
        }
    }

    public int getId() {
        return id;
    }
}
