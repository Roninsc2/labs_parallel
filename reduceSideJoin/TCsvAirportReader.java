package reduceSideJoin;

import org.apache.hadoop.io.Text;

public class TCsvAirportReader {
    private int id;
    private static final int INVALID_ID = -1;
    private static final String DELIMITER = ",";
    private static final int ID_LEN = 7;
    private static final int BEGIN_ID_INDX = 1;
    private static final int END_ID_INDX = 6;
    private static final int ID_CLMN = 0;

    public TCsvAirportReader(Text text) {
        String fileData = text.toString();
        String[] lines = fileData.split(DELIMITER);
        String idStr = lines[ID_CLMN];
        if (idStr.length() == ID_LEN) {
            id = Integer.parseInt(idStr.substring(BEGIN_ID_INDX, END_ID_INDX));
        } else {
            id = INVALID_ID;
        }
    }

    public int getId() {
        return id;
    }
}
