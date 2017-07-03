import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by mfehler on 27.06.17.
 */

//Read decimalData by rows from ResultSet

public class DataRecordBuilder {
  public String measurement="";

  private Long time=0L;
  private TimeUnit precision = TimeUnit.NANOSECONDS;

  private String tagsKey="";
  private String tagsValue="";
  private final Map<String, BigDecimal> decimalData = new LinkedHashMap<>();
  private final Map<String, Date> dateData = new LinkedHashMap<>();

  private int totalRows;


  public DataRecordBuilder(String measurement, Long time, TimeUnit precision) {
    this.measurement = measurement;
    this.time = time;
    this.precision = precision;
  }

  public DataRecordBuilder setTags(String key, String tagsValue){
    tagsKey=key;
    this.tagsValue = tagsValue;

    return this;
  }

  public DataRecordBuilder addValue(String key, BigDecimal value){
    decimalData.put(key, value);

    return this;
  }

  public DataRecordBuilder addValue(String key, Date value){
    dateData.put(key, value);

    return this;
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

  public Map<String, BigDecimal> getDecimalData() {
    return decimalData;
  }

  public String getTagsKey() {
    return tagsKey;
  }

  public String getTagsValue() {
    return tagsValue;
  }


  /*public List<String> getColumnNames(ResultSet rs) throws SQLException {
    List<String> names = new ArrayList<String>();
    ResultSetMetaData metadata = rs.getMetaData();

    for (int i = 0; i < metadata.getColumnCount(); i++) {
      names.add(metadata.getColumnName(i+1));
    }

    return names;
  }*/


}
