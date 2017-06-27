package InfluxDB;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import static PostgreSQL.ReadDataFromPostgreSQL.resultFieldSetPostgre1;
import static PostgreSQL.ReadDataFromPostgreSQLResultSet.resultFieldSetPostgre_1;


/**
 * Created by mfehler on 21.06.17.
 */
public class WriteDataFromPostgreSQLToInfluxDBTest {

  public static final String dbName = "stillDB";
  public static final Integer points = 12;


  public static void main(final String[] args) throws Exception {

    int j=0;


    String measurements = "liftanddrivetime";
    System.out.println("MyResultSet Set 1: " + resultFieldSetPostgre1.get("readoutduration"));
    System.out.println("MyResultSet Set 2: " + resultFieldSetPostgre_1.get("readoutduration"));

  }

}



