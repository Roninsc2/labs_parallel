package reduceSideJoin;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.hadoop.io.Text;

public class TCsvAirPortReader {
    //Dest_id, arr_delay
    private int id;

    public TCsvAirPortReader(Text text) {
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
