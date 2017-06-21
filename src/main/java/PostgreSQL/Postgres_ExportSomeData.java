package PostgreSQL;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by mfehler on 20.06.17.
 */
public class Postgres_ExportSomeData {

  public static void main(final String[] args) throws Exception {

    //String filename= "C:/Users/mfehler/Desktop/fitness.txt";


    String filename= "C:/Users/mfehler/Desktop/fitness.txt/fitness.txt";


    Connection con = null;
    Statement stmt = null;

    try {
      con = ConnetionsInfoPostgreSQL.getConnection();
      stmt = con.createStatement();
      String query;

        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);


        query = "SELECT readouttime, liftanddrivetime, readoutduration FROM tdm_liftanddrivetimes LIMIT 3";
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("" + rs.toString());

      } catch(Exception e) {
        e.printStackTrace();
        stmt = null;
      }
    }


}










