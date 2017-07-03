package InfluxDB;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static Connection.ConnectionInfoInfluxDB.influxDB;

/**
 * Created by mfehler on 14.06.17.
 */
public class WriteMultiplePointsToInfluxDB {

  static String dbName = "still_test";
  static Integer POINT_COUNT = 3;

  public static void main(final String[] args) throws Exception {

    String measurements = "liftanddrivetime";

    List<String> values = Arrays.asList("1", "2", "3");
    String myTagKey = "identifier";

    List<String> keysTags = Arrays.asList("identifier", "identifier", "identifier");
    List<String> valuesTags = Arrays.asList("1", "2", "3");

    List<String> keysFields = Arrays.asList("liftanddrivetime", "liftanddrivetime", "liftanddrivetime");
    List<Integer> valuesFields = Arrays.asList(1, 2, 3);

    List<String> keysFields2 = Arrays.asList("readoutduration", "readoutduration", "readoutduration");
    List<Integer> valuesFields2 = Arrays.asList(25000, 8000, 5000);


    String fieldKey = "";
    Integer fieldValue = null;
    String fieldKey2 = "";
    Integer fieldValue2 = null;
    String tagValue = "";
    String tagKey = "";


    List<Long> time = new ArrayList<Long>();
    time.add(160774L);
    time.add(7212775L);
    time.add(6980272L);

    long milies = 360000;

    /*if (influxDB.databaseExists(dbName)) {

      influxDB.deleteDatabase(dbName);

    } else {

    }*/


    for (int i = 0; i < valuesTags.size(); i++) {

      {

        tagValue = valuesTags.get(i);
        tagKey = "identifier";

        System.out.println("TagValue: " + tagValue);
        System.out.println("TagKey: " + tagKey);

      }

      for (int j = 0; j < valuesFields.size(); j++) {

        fieldValue = valuesFields.get(j);
        fieldKey = "liftanddrivetime";
        j++;




          for (int k = 0; k < valuesFields2.size(); k++) {

            fieldValue2 = valuesFields2.get(k);
            fieldKey2 = "readoutduration";
            k++;


            for (int t = 0; t < time.size(); t++) {
              milies = time.get(t);


              influxDB.createDatabase(dbName);
              System.out.println("Datenbank" + dbName);
              BatchPoints batchPoints = BatchPoints
                      .database(dbName)
                      .retentionPolicy("autogen")
                      .consistency(InfluxDB.ConsistencyLevel.ALL)
                      .build();


              Point point = null;

              while (j < POINT_COUNT) {

                point = Point.measurement(measurements)
                        .time(milies, TimeUnit.MILLISECONDS)
                        .addField(fieldKey, fieldValue)
                        .addField(fieldKey2, fieldValue2)
                        .tag(tagKey, tagValue)
                        .build();

                batchPoints.point(point);
                influxDB.write(batchPoints);
                System.out.println("DataRecord " + point.toString());
                j++;

              }

            }

          }

        }

      }


    System.out.println("InfluxDB: " + dbName + " created");
    influxDB.close();

  }
}


/*this.influxDB.write(dbName, "default", InfluxDB.ConsistencyLevel.ONE, Arrays.asList(
        "cpu,atag=test1 idle=100,usertime=10,system=1",
        "cpu,atag=test2 idle=200,usertime=20,system=2",
        "cpu,atag=test3 idle=300,usertime=30,system=3"
        ));
*/


//TODO
//https://docs.influxdata.com/influxdb/v0.9/guides/writing_data/


