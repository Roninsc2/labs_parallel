package sparkLab;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class TFlightTableApp {
    private static final int AIRPORT_ID_CLMN = 0;
    private static final int AIRPORT_NAME_CLMN = 1;
    private static final int ORIGIN_ID_CLMN = 11;
    private static final int DEST_ID_CLMN = 14;
    private static final int DELAY_ID_CLMN = 18;
    private static final int CANCEL_ID_CLMN = 19;
    private static final String AIRPORT_FILE = "L_AIRPORT_ID.csv";
    private static final String FLIGHTS_FILE = 19;
    private static final String OUTPUT_FILE = 19;

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
    }
}
