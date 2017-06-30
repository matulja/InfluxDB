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

  static String dbName = "still_test";

  public static void main(final String[] args) throws Exception {

    String measurements = "liftanddrivetime";
    String fieldName = "liftanddrivetime";
    Integer fieldKey = 0;
    String fieldName2 = "readoutduration";
    Integer fieldKey2 = 25000;
    String tagName = "identifier";
    String tagKey = "516210023354";


    long milies = 360000;
    Timestamp timestamp = new Timestamp(milies);
    System.out.println("timestamp: " + timestamp);


  /*  if (influxDB.databaseExists(dbName)) {

          influxDB.deleteDatabase(dbName);

    } else {

    }*/

      influxDB.createDatabase(dbName);
      influxDB.enableBatch(2000, 1000, TimeUnit.MILLISECONDS);

      BatchPoints batchPoints = BatchPoints
              .database(dbName)
              .retentionPolicy("autogen")
              .consistency(InfluxDB.ConsistencyLevel.ALL)
              .build();


      Point point1 = Point.measurement(measurements)
              .time(milies, TimeUnit.MILLISECONDS)
              .addField(fieldName, fieldKey)
              .addField(fieldName2, fieldKey2)
              .tag(tagName, tagKey)
              .build();

      batchPoints.point(point1);
      influxDB.write(batchPoints);

      System.out.println("InfluxDB: " + dbName + " created");
      influxDB.close();


      }
}



