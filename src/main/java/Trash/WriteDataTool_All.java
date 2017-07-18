package Trash;

import _MigrateAllData.DataRecord;
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
public class WriteDataTool_All {

  public static final String dbName = "tdmka";

  public WriteDataTool_All() {

    influxDB.enableBatch(5000, 1000, TimeUnit.SECONDS);
  }

  public void writeLine(DataRecord dataRecordAll) {


    BatchPoints batchPoints = BatchPoints
            .database(dbName)
            .retentionPolicy("autogen")
            .consistency(InfluxDB.ConsistencyLevel.ALL)
            .build();

    Point.Builder builder = Point.measurement(dataRecordAll.getMeasurements())
            .time(dataRecordAll.getTime(), TimeUnit.MILLISECONDS)
            .tag(dataRecordAll.getTagsData());

    for (Map.Entry<String, BigDecimal> entry1 : dataRecordAll.getFieldsData().entrySet()) {
      builder.addField(entry1.getKey(), entry1.getValue());
    }

    for (Map.Entry<String, BigDecimal> entry2 : dataRecordAll.getFieldsData2().entrySet()) {
      builder.addField(entry2.getKey(), entry2.getValue());
    }

     // builder.build();
      Point point = (Point) builder.build();
      batchPoints.point(point);
      influxDB.write(batchPoints);


    }
  }





