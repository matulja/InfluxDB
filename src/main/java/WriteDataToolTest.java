import _MigrateAllData.DataRecord;
import com.google.common.base.Stopwatch;
import com.mysql.cj.core.util.TestUtils;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static Connection.ConnectionInfoInfluxDB.influxDB;

/**
 * Created by mfehler on 05.07.17.
 */
public class WriteDataToolTest {

  public static final String dbName = "test";
  private final static int SINGLE_POINT_COUNT = 100000;

  public WriteDataToolTest() {

    influxDB.enableBatch(1000, 1000, TimeUnit.SECONDS);
  }

  public void writeLineTest(DataRecord dataRecordAll)  {

    for (int j = 0; j < SINGLE_POINT_COUNT; j++) {
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
    //influxDB.close();


  }


}
