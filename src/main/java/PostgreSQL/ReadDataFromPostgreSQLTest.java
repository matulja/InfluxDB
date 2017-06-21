package PostgreSQL;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mfehler on 13.06.17.
 */
public class ReadDataFromPostgreSQLTest {

   static Map<String, Integer> myMap = new HashMap<String, Integer>();

  public static void main(final String[] args) throws Exception {

    Connection con = null;
    Statement stmt = null;

    try {
      con = ConnetionsInfoPostgreSQL.getConnection();
      stmt = con.createStatement();

      String sql = "SELECT liftanddrivetime, readoutduration FROM tdm_liftanddrivetimes";
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {

        int liftTime = rs.getInt("liftanddrivetime");
        int readDur = rs.getInt("readoutduration");
        String colName =rs.getMetaData().getColumnName(1);
        String colName2 =rs.getMetaData().getColumnName(2);

        myMap.put(colName2,readDur);

        for(String key : myMap.keySet())
        {
          System.out.print("Key: " + key + " - ");
          System.out.print("Value: " + myMap.get(key) + "\n");
        }
      }

        //System.out.println("Map: " +myMap);

       // System.out.println("colName " + colName);
       // System.out.println("colName2 " + colName2);

       // System.out.print("liftTime: " + liftTime);
       // System.out.print("ReaDur: " + readDur);



    } finally {

      stmt.close();
      con.close();
    }





  }



}


