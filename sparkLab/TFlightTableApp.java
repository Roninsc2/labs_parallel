package sparkLab;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class TFlightTableApp {
    private static final int ID_LEN = 7;
    private static final int BEGIN_ID_INDX = 1;
    private static final int END_ID_INDX = 6;
    private static final int ID_CLMN = 0;

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
    }
}
