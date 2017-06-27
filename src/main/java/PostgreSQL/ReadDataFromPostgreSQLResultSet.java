package PostgreSQL;

import de.akquinet.jbosscc.guttenbase.meta.ColumnMetaData;
import de.akquinet.jbosscc.guttenbase.meta.DatabaseMetaData;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.*;

import static PostgreSQL.ReadDataFromPostgreSQL.resultTimePostgre;


/**
 * Created by mfehler on 13.06.17.
 */
public class ReadDataFromPostgreSQLResultSet {

  public static final Map<String, BigDecimal> resultFieldSetPostgre_1 = new HashMap<String, BigDecimal>();
  public static final Map<String, BigDecimal> resultFieldSetPostgre_2 = new HashMap<String, BigDecimal>();
  public static final Map<String, String> resultTagsPostgre_= new HashMap<String, String>();
  public static final Map<String, Date> resultTimePostgre_ = new HashMap <String, Date>();

  public static void main(final String[] args) throws Exception {

    Connection con = null;
    Statement stmt = null;


    try {

      con = ConnetionsInfoPostgreSQL.getConnection();
      stmt = con.createStatement();

      String sql = "SELECT liftanddrivetime, readoutduration, identifier, readouttime FROM tdm_liftanddrivetimes ladt," +
              "tdm_vehicle v, tdm_vehicledataunit du WHERE ladt.vehicle_id =" +
              "v.id LIMIT 10";

      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {

        int size = rs.getRow();
        System.out.println("size : " + size);

        BigDecimal fieldValue1 = rs.getBigDecimal("readoutduration");
        String fieldName1 =rs.getMetaData().getColumnName(2);

        BigDecimal fieldValue2 = rs.getBigDecimal("liftanddrivetime");
        String fieldName2 =rs.getMetaData().getColumnName(1);

        String tagValue = rs.getString("identifier");
        String tagName =rs.getMetaData().getColumnName(3);

        Date timeValue= rs.getTimestamp("readouttime");
        String timeName =rs.getMetaData().getColumnName(4);


        resultFieldSetPostgre_1 .put(fieldName1,fieldValue1);
        resultFieldSetPostgre_2 .put(fieldName2,fieldValue2);
        resultTagsPostgre_.put(tagName, tagValue);
        resultTimePostgre_.put(timeName,timeValue);

        for(String key1 : resultFieldSetPostgre_1 .keySet())
        {
          System.out.print("Key 1: " + key1 + " - ");
          System.out.print("Value 1: " + resultFieldSetPostgre_1 .get(key1) + "\n");
        }

        for(String key2 : resultFieldSetPostgre_2 .keySet())
        {
          System.out.print("Key 2: " + key2 + " - ");
          System.out.print("Value 2: " + resultFieldSetPostgre_2 .get(key2) + "\n");
        }

        for(String key3 : resultTagsPostgre_ .keySet())
        {
          System.out.print("Key 3: " + key3 + " - ");
          System.out.print("Value 3: " + resultTagsPostgre_ .get(key3) + "\n");
        }

        for(String key4 :  resultTimePostgre_ .keySet())
        {
          System.out.print("Key 4: " + key4 + " - ");

        }
      }


    } finally {

      stmt.close();
      con.close();
    }


  }

}


