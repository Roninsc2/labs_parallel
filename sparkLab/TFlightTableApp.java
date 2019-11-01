package sparkLab;

import org.apache.spark.SparkConf;
import org.apache.spark.api.JavaPairRDD;
import org.apache.spark.api.JavaPairRDD;

public class TFlightTableApp {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
    }
}
