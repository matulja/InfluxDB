import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Connection.ConnectionInfoInfluxDB.influxDB;

/**
 * Created by mfehler on 27.06.17.
 */
public class WriteDataTool {


  static Integer POINT_COUNT = 5;

//  private RowData _rowdata;


 /* public WriteDataTool (final RowData rowdata){

    _rowdata = rowdata;

  }*/

  public static final String dbName = "stillDB";

  public void writeLine(RowData rowdata)

  {

   // influxDB.deleteDatabase(dbName);

    influxDB.createDatabase(dbName);
    BatchPoints batchPoints = BatchPoints
            .database(dbName)
            .retentionPolicy("autogen")
            .consistency(InfluxDB.ConsistencyLevel.ALL)
            .build();

    Point  point = Point.measurement(rowdata.getMeasurement())
              .time(rowdata.getTime(), TimeUnit.MILLISECONDS)
              .addField(rowdata.getFieldKey(), rowdata.getFieldValue())
              .addField(rowdata.getFieldKey2(), rowdata.getFieldValue2())
              .tag(rowdata.getTagsKey(), rowdata.getTagsValue())
              .build();

    batchPoints.point(point);
    influxDB.write(batchPoints);
    System.out.println("RowData " + point.toString());


  }

}


