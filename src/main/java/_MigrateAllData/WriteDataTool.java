package _MigrateAllData;

import org.influxdb.dto.Point;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static Connection.ConnectionInfoInfluxDB.influxDB;


/**
 * Created by mfehler on 27.06.17.
 */
public class WriteDataTool {

  public static final String dbName = "tdmka";

  public WriteDataTool() {

    influxDB.enableBatch(100, 100, TimeUnit.SECONDS);
  }


  public void writeLine(DataRecord dataRecordAll) throws Exception {


    Point.Builder builder = Point.measurement(dataRecordAll.getMeasurements())
            .time(dataRecordAll.getTime(), TimeUnit.MILLISECONDS)
            .tag(dataRecordAll.getTagsData());

    for (Map.Entry<String, BigDecimal> entry1 : dataRecordAll.getFieldsData().entrySet()) {
      builder.addField(entry1.getKey(), entry1.getValue());

    }
    for (Map.Entry<String, BigDecimal> entry2 : dataRecordAll.getFieldsData2().entrySet()) {
      builder.addField(entry2.getKey(), entry2.getValue());
    }

    Point point = (Point) builder.build();
    influxDB.write(dbName, "autogen", point);


  }


}






