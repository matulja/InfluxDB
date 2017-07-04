import Connection.ConnectionPostgreSQL;
import Trash.DataRecord_;
import de.akquinet.jbosscc.guttenbase.meta.TableMetaData;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;
import de.akquinet.jbosscc.guttenbase.tools.ReadTableDataTool;

import java.math.BigDecimal;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by mfehler on 03.07.17.
 */
public class ReadDataToolMarkus {

  private DataRecord_ _dataRecord;
  private String measurements;
  private Long time;
  private TimeUnit precision;

  private String fieldKey1;
  private BigDecimal fieldValue1;

  private String fieldKey2;
  private BigDecimal fieldValue2;

  private String tagsKey;
  private String tagsValue;

  public static final String SOURCE = "source";
  private ReadTableDataTool _readTool;

  public  ReadDataToolMarkus() throws Exception {

    final ConnectorRepository connectorRepository = new ConnectorRepositoryImpl();
    connectorRepository.addConnectionInfo(SOURCE, new ConnectionPostgreSQL());

    final TableMetaData tableMetaData = connectorRepository.getDatabaseMetaData(SOURCE).getTableMetaData("tdm_liftanddrivetimes");
    _readTool = new ReadTableDataTool(connectorRepository, SOURCE, tableMetaData);
  }

  public void start() throws SQLException {
    _readTool.start();
  }

  public void end() throws SQLException {
    _readTool.end();
  }

  public DataRecord_ readLine() throws SQLException {
    List<Map<String, Object>> tableData;

    if ((tableData= _readTool.readTableData(1))!= null){
      Map<String, Object> map =tableData.get(0);
        Timestamp time= (Timestamp) map.get("readouttime");
        precision = TimeUnit.MILLISECONDS;

        fieldValue1 = (BigDecimal)map.get("readoutduration");
        fieldKey1 = "readoutduration";
        fieldValue2 = (BigDecimal)map.get("liftanddrivetime");
        fieldKey2 = "liftanddrivetime";

        tagsValue = String.valueOf(map.get("id"));
        tagsKey = "identifier";

        return new DataRecord_("liftanddrivetime", time.getTime(), precision, fieldKey1, fieldValue1, fieldKey2, fieldValue2, tagsKey, tagsValue);
    }

    return null;
  }

  public static void main(final String[] args) throws Exception {
    ReadDataToolMarkus readDataToolMarkus = new ReadDataToolMarkus();
    readDataToolMarkus.start();

    DataRecord_ dataRecord;

    while (((dataRecord=readDataToolMarkus.readLine()) != null)){
      System.out.println(dataRecord.getTagsValue());
    }

    readDataToolMarkus.end();
  }

}





