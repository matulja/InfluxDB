package _MigrateAllData;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Created by mfehler on 27.06.17.
 */
public class ReadDataTool {

  private ResultSet _resultSet;
  private DataRecord _dataRecordAll;
  private int totalRows;

  public ReadDataTool(final ResultSet resultSet) {

    _resultSet = resultSet;

  }

  public DataRecord readLine() throws Exception {


    if (_resultSet.next()) {

      String measurements = _resultSet.getMetaData().getColumnName(1);

      Long time = _resultSet.getLong("time");
      TimeUnit precision = TimeUnit.MILLISECONDS;

      Map<String, BigDecimal> fieldData1 = new LinkedHashMap<>();
      fieldData1.put(_resultSet.getMetaData().getColumnName(2), _resultSet.getBigDecimal("readoutduration"));

      Map<String, BigDecimal> fieldData2 = new LinkedHashMap<>();
      fieldData2.put(_resultSet.getMetaData().getColumnName(1), _resultSet.getBigDecimal("liftanddrivetime"));

      Map<String, String> tagsData = new TreeMap<>();
      tagsData.put(_resultSet.getMetaData().getColumnName(3), _resultSet.getString("identifier"));

      _dataRecordAll = new DataRecord(measurements, time, precision, fieldData1, fieldData2, tagsData);
      return _dataRecordAll;

    } else {
      _resultSet.close();
      return null;
    }

  }

  public List<String> getColumnNames(final ResultSet resultSet) throws SQLException {
    List<String> names = new ArrayList<String>();
    ResultSetMetaData metadata = resultSet.getMetaData();

    for (int i = 0; i < metadata.getColumnCount(); i++) {
      names.add(metadata.getColumnName(i + 1));
    }

    return names;
  }

  public int getRowCount(final ResultSet resultSet) throws SQLException {

    while (resultSet.next()) {
      totalRows = resultSet.getRow();
    }
    return totalRows;
  }

}
