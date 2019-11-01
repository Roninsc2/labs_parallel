package sparkLab;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class TFlightTableApp {
    private static final int AIRPORT_ID_CLMN = 0;
    private static final int AIRPORT_NAME_CLMN = 1;
    private static final int ORIGIN_ID_CLMN = 11;
    private static final int DEST_ID_CLMN = 14;
    private static final int DELAY_ID_CLMN = 18;
    private static final int CANCEL_ID_CLMN = 19;
    private static final String AIRPORT_FILE = "L_AIRPORT_ID.csv";
    private static final String FLIGHTS_FILE = "664600583_T_ONTIME_sample.csv";
    private static final String OUTPUT_FILE = "lab3_output";

    private Integer getId(String[] val, int clmn) {
        return Integer.parseInt(val[clmn]);
    }

    private String getName(String[] val) {
        if (val.length == 3) {
            return val[AIRPORT_NAME_CLMN] + val[AIRPORT_NAME_CLMN + 1];
        }
        return val[AIRPORT_NAME_CLMN];
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String[]> airportClmn = sc.textFile(AIRPORT_FILE).
                map(TCsvParser::getColumns).
                filter(val -> !val[AIRPORT_ID_CLMN].equals("Code"));
        JavaRDD<String[]> flightClmn = sc.textFile(FLIGHTS_FILE).
                map(TCsvParser::getColumns).
                filter(val -> !val[CANCEL_ID_CLMN].equals("CANCELLED"));


    }
}
