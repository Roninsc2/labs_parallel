package sparkLab;

public class TCsvParser {
        private static final String COMMA = ",";
        private static final String QUOTES = "\"";

        public TCsvParser(String text) {
            String fileData = text.toString();
            String[] lines = fileData.split(DELIMITER);
            String idStr = lines[ID_CLMN];
            if (idStr.length() == ID_LEN) {
                id = Integer.parseInt(idStr.substring(BEGIN_ID_INDX, END_ID_INDX));
            } else {
                id = INVALID_ID;
            }
        }
    }

}
