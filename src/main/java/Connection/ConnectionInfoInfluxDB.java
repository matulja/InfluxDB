package Connection;


import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;


/**
 * Created by mfehler on 06.06.17.
 */
public class ConnectionInfoInfluxDB {

   static String dbName = "still_test";
   static String dbName2= "NOAA_water_database";
   static String dbName3= "still";


   public static InfluxDB influxDB =
           InfluxDBFactory.connect("http://localhost:8086", "mary", "password");


}


