package PostgreSQL;

import de.akquinet.jbosscc.guttenbase.meta.ColumnMetaData;
import de.akquinet.jbosscc.guttenbase.meta.DatabaseMetaData;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;
import de.akquinet.jbosscc.guttenbase.tools.ReadTableDataTool;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static Connection.ConnectionInfoInfluxDB.influxDB;

/**
 * Created by mfehler on 21.06.17.
 */
public class _ReadDataFromPostgresAndWriteToInfluxDB {

  public static final String SOURCE = "source";
  public static final String dbName = "stillDB";
  public static final Integer points = 12;
  public static Integer columnSize = null;



  public static final List<String> PostgresTable = Arrays.asList("tdm_liftanddrivetimes", "tdm_vehicledataunit", "tdm_vehicle");
  public static final List<String> PostgresColumns = Arrays.asList("liftanddrivetime", "readoutduration", "readouttime");


  public static final Map<String, BigDecimal> resultFieldSet1 = new HashMap<String, BigDecimal>();
  public static final Map<String, BigDecimal> resultFieldSet2 = new HashMap<String, BigDecimal>();
  public static final Map<String, String> resultTags = new HashMap<String, String>();
  public static final List<Date> resultTime = new ArrayList<Date>();


  public static void main(final String[] args) throws SQLException {

    final ConnectorRepository connectorRepository = new ConnectorRepositoryImpl();
    connectorRepository.addConnectionInfo(SOURCE, new ConnetionsInfoPostgreSQL());

    final ReadTableDataTool readTableDataTool = new ReadTableDataTool(connectorRepository);
    final DatabaseMetaData databaseMetaData = connectorRepository.getDatabaseMetaData(SOURCE);


    // get Field Set 1

    ColumnMetaData columnMetaData = databaseMetaData.getTableMetaData("tdm_liftanddrivetimes").getColumnMetaData("readoutduration");
    columnSize = columnMetaData.getTableMetaData().getTotalRowCount();
    System.out.println("ColumnSize: " + columnSize);
    final List<Map<String, Object>> tableData = readTableDataTool.readTableData(SOURCE, databaseMetaData.getTableMetaData("tdm_liftanddrivetimes"), columnSize);

    for (int i = 0; i < columnSize; i++) {

      final BigDecimal valueField1 = (BigDecimal) tableData.get(i).get("readoutduration");
      resultFieldSet1.put(columnMetaData.getColumnName(), valueField1);
      System.out.println("Column readoutduration" + resultFieldSet1.toString());

    }

    for (String key : resultFieldSet1.keySet()) {
      System.out.print("Key: " + key + " - ");
      System.out.print("Value: " + resultFieldSet1.get(key) + "\n");
    }


    // get Field Set 2

    columnMetaData = databaseMetaData.getTableMetaData("tdm_liftanddrivetimes").getColumnMetaData("liftanddrivetime");
    final List<Map<String, Object>> tableData2 = readTableDataTool.readTableData(SOURCE, databaseMetaData.getTableMetaData("tdm_liftanddrivetimes"), columnSize);

    for (int i = 0; i < columnSize; i++) {

      final BigDecimal valueField2 = (BigDecimal) tableData2.get(i).get("liftanddrivetime");
      resultFieldSet2.put(columnMetaData.getColumnName(), valueField2);
      System.out.println("Column liftanddrivetime " + resultFieldSet2.toString());

    }

    for (String key : resultFieldSet2.keySet()) {
      System.out.print("Key: " + key + " - ");
      System.out.print("Value: " + resultFieldSet2.get(key) + "\n");
    }


    // get Tags
    columnMetaData = databaseMetaData.getTableMetaData("tdm_vehicledataunit").getColumnMetaData("identifier");
    final List<Map<String, Object>> tableData3 = readTableDataTool.readTableData(SOURCE, databaseMetaData.getTableMetaData("tdm_vehicledataunit"), columnSize);

    for (int i = 0; i < columnSize; i++) {

      final String valueTags = (String) tableData3.get(i).get("identifier");
      resultTags.put(columnMetaData.getColumnName(), valueTags);
      System.out.println("Column identifier " + resultTags.toString());

    }

    for (String key : resultTags.keySet()) {
      System.out.print("Key: " + key + " - ");
      System.out.print("Value: " + resultTags.get(key) + "\n");
    }

    // get time




    columnMetaData = databaseMetaData.getTableMetaData("tdm_liftanddrivetimes").getColumnMetaData("readouttime");

    final List<Map<String, Object>> tableData4 = readTableDataTool.readTableData(SOURCE, databaseMetaData.getTableMetaData("tdm_liftanddrivetimes"), columnSize);

    for (int i = 0; i < columnSize; i++) {

      final Date valueTags = (Date) tableData.get(i).get("readouttime");
      resultTime.add(valueTags);
      System.out.println("Column readouttime " + resultTime.toString());


    }



    /////////////////////////////////////////////////Part 2 ////////////////////////////////////////////////////////////////

    //Write Data to InfluxDB

    String measurements = "liftanddrivetime";
    List<Long> timeList = new ArrayList<>();

    int j = 0;


    // convert Date to long format in InfluxDB

    for (int i = 0; i < resultTime.size(); i++)

    {
      long millis = resultTime.get(i).getTime();
      timeList.add(millis);


    }

    String fieldKey = "";
    BigDecimal fieldValue = null;
    String fieldKey2 = "";
    BigDecimal fieldValue2 = null;
    String tagValue = "";
    String tagKey = "";

    Date timeValue;
    String timeName;


    for (Map.Entry<String, BigDecimal> e : resultFieldSet1.entrySet()) {

      fieldKey = e.getKey();
      fieldValue = e.getValue();
      e.getValue();
      System.out.println("key " + fieldKey + " value " + fieldValue);
    }

    for (Map.Entry<String, BigDecimal> e : resultFieldSet2.entrySet()) {

      fieldKey2 = e.getKey();
      fieldValue2 = e.getValue();
      System.out.println("key " + fieldKey2 + " value " + fieldValue2);
    }


    for (Map.Entry<String, String> e : resultTags.entrySet()) {

      tagKey = e.getKey();
      tagValue = e.getValue();
      System.out.println("key " + tagKey + " value " + tagValue);
    }



    Iterator<String> keySetIterator = resultTags.keySet().iterator();
    while(keySetIterator.hasNext())
    {
          String key = keySetIterator.next();
          System.out.println("key: " + key + " value: " + resultTags.get(key));


    }



    if (influxDB.databaseExists(dbName)) {

      influxDB.deleteDatabase(dbName);

    } else {

    }

    influxDB.createDatabase(dbName);
    BatchPoints batchPoints = BatchPoints
            .database(dbName)
            .retentionPolicy("autogen")
            .consistency(InfluxDB.ConsistencyLevel.ALL)
            .build();


    Point point = null;

    for (int t = 1; t < resultTime.size(); t++) {

        while (j < points && t<resultTime.size()) {

        System.out.println("Time in point: " + resultTime.get(t).getTime());
        point = Point.measurement(measurements)
                .time(resultTime.get(t).getTime(), TimeUnit.MILLISECONDS)
                .addField(fieldKey, fieldValue)
                .addField(fieldKey2, fieldValue2)
                .tag(tagKey, tagValue)
                .build();

        batchPoints.point(point);
        influxDB.write(batchPoints);
        j++;
        t++;
      }

    }

    System.out.println("Erste Point " + point.toString());

    System.out.println("InfluxDB: " + dbName + " created");
    influxDB.close();

  }

}














