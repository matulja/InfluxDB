import PostgreSQL.ConnetionsInfoPostgreSQL;
import de.akquinet.jbosscc.guttenbase.connector.Connector;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * Created by mfehler on 27.06.17.
 */
public class MyResultSet {

  public static final String SOURCE = "source";

  public static void main(final String[] args) throws Exception {

    final ConnectorRepository connectorRepository = new ConnectorRepositoryImpl();
    connectorRepository.addConnectionInfo(SOURCE, new ConnetionsInfoPostgreSQL());
    final Connector connector = connectorRepository.createConnector(SOURCE);
    Connection connection = connector.openConnection();
    Statement statement = connection.createStatement();

    String sql = "SELECT liftanddrivetime, readoutduration, identifier, readouttime FROM tdm_liftanddrivetimes ladt," +
            "tdm_vehicle v, tdm_vehicledataunit du WHERE ladt.vehicle_id =" +
            "v.id LIMIT 10";


    final ResultSet resultSet = statement.executeQuery(sql);

    while (resultSet.next()) {

      int size = resultSet.getRow();
      System.out.println("size : " + size);

      BigDecimal fieldValue1 = resultSet.getBigDecimal("readoutduration");
      String fieldName1 = resultSet.getMetaData().getColumnName(2);

      BigDecimal fieldValue2 = resultSet.getBigDecimal("liftanddrivetime");
      String fieldName2 = resultSet.getMetaData().getColumnName(1);

      String tagValue = resultSet.getString("identifier");
      String tagName = resultSet.getMetaData().getColumnName(3);

      Date timeValue = resultSet.getTimestamp("readouttime");
      String timeName = resultSet.getMetaData().getColumnName(4);

      System.out.println("fieldValue1 " + fieldValue1);
      System.out.println("fieldValue2 " + fieldValue2);
    }


    final ReadDataTool reader = new ReadDataTool(resultSet);
    final WriteDataTool writer = new WriteDataTool();
    Point point = new Point("a",2, "b");

    List<String> getColumnNames= point.getColumnNames(resultSet);
    System.out.println("Method: " + getColumnNames);

    /*  while ((point = reader.readLine(resultSet)) != null) {
      writer.writeLine(point);
      System.out.println("Point " + point);

    }*/

  }
}


