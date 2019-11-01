package sparkLab;

public class TCsvFlightParser {
    private int id;
    private String delay;
    private static final int INVALID_ID = -1;
    private static final String DELIMITER = ",";
    private static final int ID_LEN = 5;
    private static final int DEST_CLMN = 14;
    private static final int ORIGIN_CLMN = 14;
    private static final int DELAY_CLMN = 18;
    private static final int CANCEL_CLMN = 18;

    public TCsvFlightParser(String text) {
        String[] lines = text.split(DELIMITER);
        String idStr = lines[DEST_CLMN];
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
