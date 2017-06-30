import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by mfehler on 27.06.17.
 */

//Read data by rows from ResultSet

public class RowData {

  public String measurement;

  private Long time;
  private TimeUnit precision = TimeUnit.NANOSECONDS;

  private String fieldKey;
  private BigDecimal fieldValue;
  private String fieldKey2;
  private BigDecimal fieldValue2;

  private String tagsKey;
  private String tagsValue;


  public RowData(String measurement, Long time, TimeUnit precision, String fieldKey, BigDecimal fieldValue,
                 String fieldKey2, BigDecimal fieldValue2, String tagsKey, String tagsValue) {
    this.measurement = measurement;
    this.time = time;
    this.precision = precision;
    this.fieldKey = fieldKey;
    this.fieldValue = fieldValue;
    this.fieldKey2 = fieldKey2;
    this.fieldValue2 = fieldValue2;
    this.tagsKey = tagsKey;
    this.tagsValue = tagsValue;
  }

  public String getMeasurement() {
    return measurement;
  }

  public Long getTime() {
    return time;
  }

  public TimeUnit getPrecision() {
    return precision;
  }

  public String getFieldKey() {
    return fieldKey;
  }

  public BigDecimal getFieldValue() {
    return fieldValue;
  }

  public String getFieldKey2() {
    return fieldKey2;
  }

  public BigDecimal getFieldValue2() {
    return fieldValue2;
  }

  public String getTagsKey() {
    return tagsKey;
  }

  public String getTagsValue() {
    return tagsValue;
  }


  public List<String> getColumnNames(ResultSet rs) throws SQLException {
    List<String> names = new ArrayList<String>();
    ResultSetMetaData metadata = rs.getMetaData();

    for (int i = 0; i < metadata.getColumnCount(); i++) {
      names.add(metadata.getColumnName(i+1));
    }

    return names;
  }

  public int getRowCount(ResultSet rs) throws SQLException {

    int totalRows = 0;

    while (rs.next())
    {
      totalRows++;
    }
    return totalRows;
  }


}
