package PostgreSQL;

import de.akquinet.jbosscc.guttenbase.meta.ColumnMetaData;
import de.akquinet.jbosscc.guttenbase.meta.DatabaseMetaData;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;
import de.akquinet.jbosscc.guttenbase.tools.ReadTableDataTool;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Created by mfehler on 21.06.17.
 */
public class ReadDataFromPostgresAndWriteToInfluxDBTrash {

  public static final String SOURCE = "source";
  public static final String dbName = "stillDB";
  public static final Integer points = 12;
  public static Integer columnSize = null;
  static Integer POINT_COUNT = 3;

  public static final List<BigDecimal>  valuesFields = new ArrayList<>();
  public static final List<BigDecimal> valuesFields2 = new ArrayList<>();
  public static final List<String> valuesTags = new ArrayList<>();
  public static final List<Date> resultTime = new ArrayList<Date>();


  public static void main(final String[] args) throws SQLException {

  /*  final ConnectorRepository connectorRepository = new ConnectorRepositoryImpl();
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
      valuesFields.add(valueField1);
      System.out.println("Column readoutduration" + valuesFields.toString());

    }


    // get Field Set 2

    columnMetaData = databaseMetaData.getTableMetaData("tdm_liftanddrivetimes").getColumnMetaData("liftanddrivetime");
    final List<Map<String, Object>> tableData2 = readTableDataTool.readTableData(SOURCE, databaseMetaData.getTableMetaData("tdm_liftanddrivetimes"), columnSize);

    for (int i = 0; i < columnSize; i++) {

      final BigDecimal valueField2 = (BigDecimal) tableData2.get(i).get("liftanddrivetime");
      valuesFields2.add(valueField2);
      System.out.println("Column liftanddrivetime " + valuesFields2.toString());

    }


    // get Tags
    columnMetaData = databaseMetaData.getTableMetaData("tdm_vehicledataunit").getColumnMetaData("identifier");
    final List<Map<String, Object>> tableData3 = readTableDataTool.readTableData(SOURCE, databaseMetaData.getTableMetaData("tdm_vehicledataunit"), columnSize);

    for (int i = 0; i < columnSize; i++) {

      final String valueTags = (String) tableData3.get(i).get("identifier");
      valuesTags.add(valueTags);
      System.out.println("Column identifier " + valueTags.toString());

    }


    // get time
    columnMetaData = databaseMetaData.getTableMetaData("tdm_liftanddrivetimes").getColumnMetaData("readouttime");
    final List<Map<String, Object>> tableData4 = readTableDataTool.readTableData(SOURCE, databaseMetaData.getTableMetaData("tdm_liftanddrivetimes"), columnSize);

    for (int i = 0; i < columnSize; i++) {

      final Date valueTags = (Date) tableData.get(i).get("readouttime");
      resultTime.add(valueTags);
      System.out.println("Column readouttime " + resultTime.toString());

    }


    /////////////////////////////////////////Write Data to InfluxDB////////////////////////////////////

    String measurements = "liftanddrivetime";

    String fieldKey = "";
    BigDecimal fieldValue = null;
    String fieldKey2 = "";
    BigDecimal fieldValue2 = null;
    String tagValue = "";
    String tagKey = "";

    System.out.println("resultFieldSetPostgre "+  resultTagsPostgre_.toString());


    List<Long> time = new ArrayList<Long>();
    time.add(160774L);
    time.add(7212775L);
    time.add(6980272L);

    long milies = 360000;

    if (influxDB.databaseExists(dbName)) {

      influxDB.deleteDatabase(dbName);

    } else {

    }


    for (int i = 0; i < valuesTags.size(); i++) {

        tagValue = valuesTags.get(i);
        tagKey = "identifier";

        System.out.println("TagValue: " + tagValue);
        System.out.println("TagKey: " + tagKey);


      for (int j = 0; j < valuesFields.size(); j++) {

        fieldValue = valuesFields.get(j);
        fieldKey = "liftanddrivetime";
        j++;



        for (int k = 0; k < valuesFields2.size(); k++) {

          fieldValue2 = valuesFields2.get(k);
          fieldKey2 = "readoutduration";
          k++;


          for (int t = 0; t < time.size(); t++) {
            milies = time.get(t);
            influxDB.createDatabase(dbName);
            BatchPoints batchPoints = BatchPoints
                    .database(dbName)
                    .retentionPolicy("autogen")
                    .consistency(InfluxDB.ConsistencyLevel.ALL)
                    .build();


            Trash.DataRecord_ point = null;

             while (j < POINT_COUNT) {

              point = Trash.DataRecord_.measurement(measurements)
                      .time(milies, TimeUnit.MILLISECONDS)
                      .addField(fieldKey, fieldValue)
                      .addField(fieldKey2, fieldValue2)
                      .tag(tagKey, tagValue)
                      .build();

              batchPoints.point(point);
              influxDB.write(batchPoints);
              System.out.println("Trash.DataRecord_ " + point.toString());
              j++;

            }

          }

        }

      }

    }

    System.out.println("InfluxDB: " + dbName + " created");
    influxDB.close();*/

  }
}





