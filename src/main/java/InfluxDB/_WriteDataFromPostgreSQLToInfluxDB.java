package InfluxDB;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Connection.ConnectionInfoInfluxDB.influxDB;
import static PostgreSQL._ReadDataFromPostgres.*;

/**
 * Created by mfehler on 21.06.17.
 */
public class _WriteDataFromPostgreSQLToInfluxDB {

  public static final String dbName = "stillDB";
  public static final Integer points = 1000;


  public static void main(final String[] args) throws Exception {


    String measurements = "liftanddrivetime";
    int j=0;

    List<Date> timeList = new ArrayList<>();

    System.out.println("Start: " + resultFieldSet1.get(0));


    for (int i=0; i<resultTime.size(); i++)
    {

      System.out.println("Start2 ");
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
      Date date = (Date)formatter.parse(String.valueOf(resultTime.get(i)));
      long mills = date.getTime();

      timeList.add(resultTime.get(i));
      System.out.println("Time: "+ timeList.toString());

    }


      if (influxDB.databaseExists(dbName)) {

        influxDB.deleteDatabase(dbName);

      } else {

      }

              influxDB.createDatabase(dbName);
              BatchPoints batchPoints = BatchPoints
                      .database(dbName)
                      .retentionPolicy("autogen")
                      .consistency(InfluxDB.ConsistencyLevel.ALL)
                      .build();


              Point point = null;

              for (int i = 0; i < resultTime.size(); i++)

             /* while (j < points) {


                point = Point.measurement(measurements)
                        //.time(timeList.get(i), TimeUnit.MILLISECONDS)


                        .addField(resultFieldSet1,resultFieldSet1)
                        .addField(resultFieldSet2, resultFieldSet2)
                        .tag(resultTags, resultTags)
                        .build();

                batchPoints.point(point);
                influxDB.write(batchPoints);
                j++;


              }*/


    System.out.println("InfluxDB: " + dbName + " created");
    influxDB.close();

  }
}


