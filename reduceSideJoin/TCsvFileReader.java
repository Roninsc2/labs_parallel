package reduceSideJoin;

import java.util.ArrayList;
import org.apache.hadoop.io.Text;

public class TCsvFileReader {
    //Dest_id, arr_delay
    private ArrayList<ArrayList<String>> flightData;

    public TCsvFileReader(Text text) {
        String fileData = text.toString();
        String[] lines = fileData.split("\n");
        flightData = new ArrayList<ArrayList<String>>();
        for (String line : lines) {
            int count = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ',') {
                    count++;
                }
                if (count == 13) {
                    i++;
                    while (char)
                }
            }
        }
    }
}
