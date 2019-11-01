package sparkLab;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class TFlightTableApp {
    private static final int AIRPORT_ID_CLMN = 0;
    private static final int AIRPORT_NAME_CLMN = 0;
    private static final int ID_CLMN = 0;


    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
    }
}
