import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import static Connection.ConnectionInfoInfluxDB.influxDB;

/**
 * Created by mfehler on 13.06.17.
 */
public class ReadDataFromInfluxDB_All {

  static String dbName = "tdmka";

  public static void main(final String[] args) throws Exception {

    String meaurements = "liftanddrivetime";

    final Query query = new Query("SELECT * FROM " + meaurements + " LIMIT 10", dbName);
    final QueryResult queryResult = influxDB.query(query);

      for (QueryResult.Result result : queryResult.getResults()) {

          for (QueryResult.Series series : result.getSeries()) {
            System.out.println("series.Name: " + series.getName());
            System.out.println("series.Columns: " + series.getColumns());
            System.out.println("series.Values: " + series.getValues());
            System.out.println("series.Tags: " + series.getTags());

          }
    }

  }

}
