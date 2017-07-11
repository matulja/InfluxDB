package Trash;

import Trash.DataRecord_;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.concurrent.TimeUnit;

import static Connection.ConnectionInfoInfluxDB.influxDB;

/**
 * Created by mfehler on 27.06.17.
 */
public class WriteDataTool_ {

  public static final String dbName = "stillDB";

  public void writeLine(DataRecord_ dataRecord) {

    influxDB.disableBatch();
    influxDB.enableBatch(2000, 100, TimeUnit.MICROSECONDS);

    influxDB.createDatabase(dbName);
    BatchPoints batchPoints = BatchPoints
            .database(dbName)
            .retentionPolicy("autogen")
            .consistency(InfluxDB.ConsistencyLevel.ALL)
            .build();

    Point  point = Point.measurement(dataRecord.getMeasurement())
              .time(dataRecord.getTime(), TimeUnit.MILLISECONDS)
              .addField(dataRecord.getFieldKey(), dataRecord.getFieldValue())
              .addField(dataRecord.getFieldKey2(), dataRecord.getFieldValue2())
              .tag(dataRecord.getTagsKey(), dataRecord.getTagsValue())
              .build();
    batchPoints.point(point);
    influxDB.write(batchPoints);
  //  System.out.println(" " + point.toString());

  }

}


