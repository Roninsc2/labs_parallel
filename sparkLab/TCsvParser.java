package sparkLab;

public class TCsvParser {
        private static final String COMMA = ",";
        private static final String QUOTES = "\"";
        private static final String NULL_STR = "";

        static String[] getColumns(String line) {
            return line.replaceAll("\"", NULL_STR).split(COMMA);
        }
}
