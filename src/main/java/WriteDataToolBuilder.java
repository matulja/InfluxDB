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
public class WriteDataToolBuilder {


  static Integer POINT_COUNT = 5;

//  private DataRecord _rowdata;


 /* public WriteDataTool (final DataRecord rowdata){

    _rowdata = rowdata;

  }*/

  public static final String dbName = "stillDB";

  public void writeLine(DataRecord dataRecord)

  {

   // influxDB.deleteDatabase(dbName);

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


    DataRecordBuilder dataRecordBuilder = new DataRecordBuilder("measuer", 98982332L, TimeUnit.HOURS)
    .setTags("bla", "blu").addValue("1", new BigDecimal(12));

    Point.Builder builder = Point.measurement(dataRecordBuilder.getMeasurement())
            .time(dataRecordBuilder.getTime(), TimeUnit.MILLISECONDS)
            .tag(dataRecordBuilder.getTagsKey(), dataRecordBuilder.getTagsValue());

    for (Map.Entry<String, BigDecimal> entry : dataRecordBuilder.getDecimalData().entrySet()) {
      builder.addField(entry.getKey(), entry.getValue());
    }

    Point  point2 = (Point) builder.build();





    batchPoints.point(point);
    influxDB.write(batchPoints);
    System.out.println(" " + point.toString());


  }

}


