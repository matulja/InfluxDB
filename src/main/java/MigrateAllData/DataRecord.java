package MigrateAllData;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by mfehler on 04.07.17.
 */

public class DataRecord {

  public String measurements;

  private Long time;
  private TimeUnit precision = TimeUnit.NANOSECONDS;

  private  Map<String, BigDecimal> fieldsData = new LinkedHashMap<>();
  private  Map<String, BigDecimal> fieldsData2 = new LinkedHashMap<>();

  private  Map<String, String> tagsData = new TreeMap<>();

  //private Map<String, String> tags;

  /*public MigrateData_old.DataRecord(String measurements, Long time, TimeUnit precision) {
    this.measurements = measurements;
    this.time = time;
    this.precision = precision;
  }*/

  public DataRecord(String measurements, Long time, TimeUnit precision,
                    Map<String, BigDecimal> fieldData1, Map<String, BigDecimal> fieldData2,
                    Map<String, String> tagsData) {

    this.measurements = measurements;
    this.time = time;
    this.precision = precision;
    this.fieldsData=fieldData1;
    this.fieldsData2=fieldData2;
    this.tagsData=tagsData;

  }


  public DataRecord addFieldValue(String key, BigDecimal value){

    fieldsData.put(key, value);
    return this;
  }

  public DataRecord addFieldValue2(String key, BigDecimal value){

    fieldsData2.put(key, value);
    return this;
  }

  public DataRecord addTagValue(String key, String value){

    tagsData.put(key, value);
    return this;
  }


 /* public MigrateData_old.DataRecord setTags(final Map<String, String> tags) {
    this.tags = tags;
    return this;
  }*/



  public String getMeasurements() {
    return measurements;
  }

  public Long getTime() {
    return time;
  }

  public TimeUnit getPrecision() {
    return precision;
  }

  public Map<String, BigDecimal> getFieldsData() {
    return fieldsData;
  }

  public Map<String, BigDecimal> getFieldsData2() {
    return fieldsData2;
  }

  public Map<String, String> getTagsData() {
    return tagsData;
  }

}
