package MigrateData;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static Connection.ConnectionInfoInfluxDB.influxDB;

/**
 * Created by mfehler on 27.06.17.
 */
public class WriteDataTool {


  public static final String dbName2 = "test";


  public void writeLine(DataRecord dataRecord) {

    influxDB.createDatabase(dbName2);
    BatchPoints batchPoints = BatchPoints
            .database(dbName2)
            .retentionPolicy("autogen")
            .consistency(InfluxDB.ConsistencyLevel.ALL)
            .build();

    Point.Builder builder = Point.measurement(dataRecord.getMeasurements())
            .time(dataRecord.getTime(), TimeUnit.MILLISECONDS)
            .tag(dataRecord.getTagsData());

    for (Map.Entry<String, BigDecimal> entry1 : dataRecord.getFieldsData().entrySet()) {
      builder.addField(entry1.getKey(), entry1.getValue());
    }

    for (Map.Entry<String, BigDecimal> entry2 : dataRecord.getFieldsData2().entrySet()) {
      builder.addField(entry2.getKey(), entry2.getValue());
    }

      builder.build();
      Point point = (Point) builder.build();
      batchPoints.point(point);

    System.out.println("Batch Point: " + point);
      influxDB.write(batchPoints);
 //     influxDB.disableBatch();



    }
  }





