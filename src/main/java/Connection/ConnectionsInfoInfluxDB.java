package Connection;


import com.mysql.cj.core.util.TestUtils;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;


/**
 * Created by mfehler on 21.06.17.
 */
public class ConnectionsInfoInfluxDB {

   public static final InfluxDB influxDB =
           InfluxDBFactory.connect("http://localhost:8086", "mary", "password");



}


