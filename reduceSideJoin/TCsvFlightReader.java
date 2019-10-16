package reduceSideJoin;

import java.util.ArrayList;
import org.apache.hadoop.io.Text;

public class TCsvFlightReader {
    //Dest_id, arr_delay
    private ArrayList<ArrayList<String>> flightData;

    public TCsvFileReader(Text text) {
        String fileData = text.toString();
        String[] lines = fileData.split("\n");
        flightData = new ArrayList<ArrayList<String>>();
        for (int k = 1; k < lines.length; k++) {
            int count = 0;
            String line = lines[k];
            ArrayList<String> dataLine = new ArrayList<String>();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ',') {
                    count++;
                }
                if (count == 13) {
                    i++;
                    dataLine.add(line.substring(i, i+5));
                    i+=5;
                }
                if (count == 15) {
                    i++;
                    String delay = "";
                    while (line.charAt(i) != ',') {
                        delay += line.charAt(i);
                    }
                    dataLine.add(delay);
                    break;
                }
            }
            flightData.add(dataLine);
        }
    }

    public int getDataSize() {
        return flightData.size();
    }

    public String getId(int i) {
        return flightData.get(i).get(0);
    }

    public String getDelay(int i) {
        return flightData.get(i).get(1);
    }
}
