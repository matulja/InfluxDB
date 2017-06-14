package InfluxDB;

import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import static Connection.ConnectionInfoInfluxDB.influxDB;

import static java.util.stream.StreamSupport.stream;

/**
 * Created by mfehler on 13.06.17.
 */
public class ReadDataFromInfluxDB {

  static String dbName = "still_test";
  static String dbName2 = "NOAA_water_database";
  static String dbName3= "still";

  public static void main(final String[] args) throws Exception {

    String meaurements = "liftanddrivetime";
    String meaurements2 = "h2o_feet";

    final Query query = new Query("SELECT * FROM " + meaurements + " LIMIT 10", dbName);
    final QueryResult queryResult = influxDB.query(query);


      for (QueryResult.Result result : queryResult.getResults()) {

          System.out.println(result.toString());


          for (QueryResult.Series series : result.getSeries()) {
            System.out.println("series.getName() = " + series.getName());
            System.out.println("series.getColumns() = " + series.getColumns());
            System.out.println("series.getValues() = " + series.getValues());
            System.out.println("series.getTags() = " + series.getTags());

          }

    }

  }

}
