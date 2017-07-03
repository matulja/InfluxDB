import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by mfehler on 27.06.17.
 */
public class ReadDataTool {

  private ResultSet _resultSet;

  private DataRecord _dataRecord;
  private String measurements;
  private Long time;
  private TimeUnit precision;

  private String fieldKey1;
  private BigDecimal fieldValue1;

  private String fieldKey2;
  private  BigDecimal fieldValue2;

  private String tagsKey;
  private String tagsValue;

  private int totalRows;

  public ReadDataTool(final ResultSet resultSet) {

    _resultSet = resultSet;
  }

 public DataRecord readLine() throws Exception {

     if (_resultSet.next()) {
       measurements = _resultSet.getMetaData().getColumnName(1);

       Long time= _resultSet.getLong("time");
       precision = TimeUnit.MILLISECONDS;

       fieldValue1 = _resultSet.getBigDecimal("readoutduration");
       fieldKey1 = _resultSet.getMetaData().getColumnName(2);
       fieldValue2 = _resultSet.getBigDecimal("liftanddrivetime");
       fieldKey2 = _resultSet.getMetaData().getColumnName(1);

       tagsValue = _resultSet.getString("identifier");
       tagsKey = _resultSet.getMetaData().getColumnName(3);

       _dataRecord = new DataRecord(measurements, time, precision, fieldKey1, fieldValue1, fieldKey2, fieldValue2, tagsKey, tagsValue);
       return _dataRecord;
     }
else
   return null;

 }

  public List<String> getColumnNames(final ResultSet resultSet) throws SQLException {
    List<String> names = new ArrayList<String>();
    ResultSetMetaData metadata = resultSet.getMetaData();

    for (int i = 0; i < metadata.getColumnCount(); i++) {
      names.add(metadata.getColumnName(i+1));
    }

    return names;
  }

  public int getRowCount(final ResultSet resultSet) throws SQLException {

    while (resultSet.next())
    {
      totalRows=resultSet.getRow();
    }
    return totalRows;
  }

}
