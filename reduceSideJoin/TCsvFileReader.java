package reduceSideJoin;

import java.util.ArrayList;
import org.apache.hadoop.io.Text;

public class TCsvFileReader {
    private ArrayList<ArrayList<String>> flightData;

    public TCsvFileReader(Text text) {
        String fileData = text.toString();
    }
}
