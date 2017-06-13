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
public class WriteDataToInfluxDB {

  static String dbName = "still_test";

  public static void main(final String[] args) throws Exception {

    String measurements = "liftanddrivetime";
    String fieldName = "liftanddrivetime";
    Integer fieldKey = 0;

    String fieldName2 = "readoutduration";
    Integer fieldKey2 = 25000;

    String tagName = "identifier";
    String tagKey = "516210023354";

    String tagName2 = "identifier";
    String tagKey2 = "516210023388";

    long milies = 360000;
    long milies2 = 500000;
    Timestamp timestamp = new Timestamp(milies);
    System.out.println("timestamp: " + timestamp);

    List<String> keys = Arrays.asList("readoutduration", "readoutduration", "readoutduration");
    List<Integer> values = Arrays.asList(25000, 8000, 5000);


    if (influxDB.databaseExists(dbName)) {
      influxDB.deleteDatabase(dbName);

    } else {

      influxDB.createDatabase(dbName);
      influxDB.enableBatch(2000, 1000, TimeUnit.MILLISECONDS);

      BatchPoints batchPoints = BatchPoints
              .database(dbName)
              .retentionPolicy("autogen")
              .consistency(InfluxDB.ConsistencyLevel.ALL)
              .build();


     /* Point point1 = Point.measurement(measurements)
              .time(milies, TimeUnit.MILLISECONDS)
              .addField(fieldName, fieldKey)
              .addField(fieldName2, fieldKey2)
              .tag(tagName, tagKey)
              .build();*/


        for (int i = 0; i < keys.size(); i++) {

          for (int o = 0; o < values.size(); o++) {

            Point point2 = Point.measurement(measurements)
                    .time(milies, TimeUnit.MILLISECONDS)
                    .addField(keys.get(i), values.get(o))
                    .addField(fieldName2, fieldKey2)
                    .tag(tagName, tagKey)
                    .build();

            Point point3 = Point.measurement(measurements)
                    .time(milies2, TimeUnit.MILLISECONDS)
                    .addField(keys.get(i), values.get(o))
                    .addField(fieldName2, fieldKey2)
                    .tag(tagName2, tagKey2)
                    .build();

            // batchPoints.point(point1);
            batchPoints.point(point2);
            batchPoints.point(point3);
            influxDB.write(batchPoints);

          }

        }

        System.out.println("InfluxDB: " + dbName + " created");
        influxDB.close();


      }
    }
}


