package InfluxDB;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Connection.ConnectionInfoInfluxDB.influxDB;



/**
 * Created by mfehler on 07.06.17.
 */
public class WriteSinglePointsToInfluxDB {

  static String dbName = "testDB";

  public static void main(final String[] args) throws Exception {

      influxDB.createDatabase(dbName);
    influxDB.enableBatch(2000, 1000, TimeUnit.MILLISECONDS);

      BatchPoints batchPoints = BatchPoints
              .database(dbName)
              .retentionPolicy("autogen")
              .consistency(InfluxDB.ConsistencyLevel.ALL)
              .build();


      Point point1 = Point.measurement("liftanddrivetime")
              .time(360000, TimeUnit.MILLISECONDS)
              .addField("liftanddrivetime", 0)
              .addField("readoutduration", 25000)
              .tag("identifier", "516210023354")
              .build();

      batchPoints.point(point1);
      influxDB.write(batchPoints);
      influxDB.close();


      }
}



