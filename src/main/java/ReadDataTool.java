import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;



/**
 * Created by mfehler on 27.06.17.
 */
public class ReadDataTool {

  private ResultSet _resultSet;

  private RowData rowdata;
  private String measurements;
  private Long time;
  private TimeUnit precision;

  private String fieldKey1;
  private BigDecimal fieldValue1;

  private String fieldKey2;
  private  BigDecimal fieldValue2;

  private String tagsKey;
  private String tagsValue;

  public static List<Date> resultTime2 = new ArrayList<Date>();


  public ReadDataTool(final ResultSet resultSet) {

    _resultSet = resultSet;
  }

 public RowData readLine(final ResultSet resultSet) throws Exception {


     while (resultSet.next()) {

       measurements = resultSet.getMetaData().getColumnName(1);
       Date timeValue= resultSet.getDate("readouttime");
       time = timeValue.getTime();
       precision = TimeUnit.NANOSECONDS;


       fieldValue1 = resultSet.getBigDecimal("readoutduration");
       fieldKey1 = resultSet.getMetaData().getColumnName(2);
       fieldValue2 = resultSet.getBigDecimal("liftanddrivetime");
       fieldKey2 = resultSet.getMetaData().getColumnName(1);

       tagsValue = resultSet.getString("identifier");
       tagsKey = resultSet.getMetaData().getColumnName(3);

       rowdata = new RowData(measurements, time, precision, fieldKey1, fieldValue1, fieldKey2, fieldValue2, tagsKey, tagsValue);

       return rowdata;

     }

   return rowdata;

 }

}
