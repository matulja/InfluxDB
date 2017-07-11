import com.google.common.base.Stopwatch;
import com.mysql.cj.core.util.TestUtils;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static Connection.ConnectionInfoInfluxDB.influxDB;

/**
 * Created by mfehler on 05.07.17.
 */
public class PerformanceTest {

  private final static int COUNT = 1;
  private final static int POINT_COUNT = 100000;
  private final static int SINGLE_POINT_COUNT = 10000;


  public static void main(final String[] args) throws Exception {

      String dbName = "test";
      influxDB.createDatabase(dbName);

      Stopwatch watch = Stopwatch.createStarted();
      for (int i = 0; i < COUNT; i++) {

        BatchPoints batchPoints = BatchPoints
                .database(dbName)
                .tag("blubber", "bla")
                .retentionPolicy("autogen")
                .build();
        for (int j = 0; j < POINT_COUNT; j++) {
          Point point = Point
                  .measurement("cpu")
                  .addField("idle", (double) j)
                  .addField("user", 2.0 * j)
                  .addField("system", 3.0 * j)
                  .build();
          batchPoints.point(point);
        }

        influxDB.write(batchPoints);
      }
      System.out.println("WritePoints for " + COUNT + " writes of " + POINT_COUNT + " Points took:" + watch);
      influxDB.deleteDatabase(dbName);
    }



  }
