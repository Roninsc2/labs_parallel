package sparkLab;

public class TCsvFlightParser {
    private int id;
    private String delay;
    private static final int INVALID_ID = -1;
    private static final String DELIMITER = ",";
    private static final int ID_LEN = 5;
    private static final int ID_CLMN = 14;
    private static final int DELAY_CLMN = 17;

    public TCsvFlightReader(Text text) {
        String[] lines = text.toString().split(DELIMITER);
        String idStr = lines[ID_CLMN];
        delay = lines[DELAY_CLMN];
        if (idStr.length() == ID_LEN && delay.length() > 0) {
            id = Integer.parseInt(idStr);
        } else {
            id = INVALID_ID;
        }
    }

    public int getId() {
        return id;
    }

    public String getDelay() {
        return delay;
    }
}
