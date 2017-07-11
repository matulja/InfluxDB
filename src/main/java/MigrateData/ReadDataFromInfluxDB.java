package MigrateData;

import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import static Connection.ConnectionInfoInfluxDB.influxDB;

/**
 * Created by mfehler on 13.06.17.
 */
public class ReadDataFromInfluxDB {

  static String dbName = "stillDB";

  public static void main(final String[] args) throws Exception {

    String meaurements = "liftanddrivetime";

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
