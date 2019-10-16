package reduceSideJoin;

import java.util.ArrayList;
import org.apache.hadoop.io.Text;

public class TCsvAirPortReader {
    //Dest_id, arr_delay
    private ArrayList<String> airportData;

    public TCsvAirPortReader(Text text) {
        String fileData = text.toString();
        String[] lines = fileData.split("\n");
        airportData = new ArrayList<String>();
        for (int k = 1; k < lines.length; k++) {
            String dataLine = lines[k].substring(1, 6);
            airportData.add(dataLine);
        }
    }

    public int getDataSize() {
        return airportData.size();
    }

    public String getId(int i) {
        return airportData.get(i);
    }
}
