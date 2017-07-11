package Connection;


import com.mysql.cj.core.util.TestUtils;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;


/**
 * Created by mfehler on 06.06.17.
 */
public class ConnectionInfoInfluxDB {

   public static final InfluxDB influxDB =
           InfluxDBFactory.connect("http://localhost:8086", "mary", "password");



}


