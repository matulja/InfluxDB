import PostgreSQL.ConnetionsInfoPostgreSQL;
import de.akquinet.jbosscc.guttenbase.connector.Connector;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by mfehler on 27.06.17.
 */
public class Main {

  public static final String SOURCE = "source";

  public static void main(final String[] args) throws Exception {

    final ConnectorRepository connectorRepository = new ConnectorRepositoryImpl();
    connectorRepository.addConnectionInfo(SOURCE, new ConnetionsInfoPostgreSQL());
    final Connector connector = connectorRepository.createConnector(SOURCE);
    Connection connection = connector.openConnection();
    Statement statement = connection.createStatement();

    String sql = "SELECT liftanddrivetime, readoutduration, identifier, readouttime FROM tdm_liftanddrivetimes ladt," +
            "tdm_vehicle v, tdm_vehicledataunit du WHERE ladt.vehicle_id =" +
            "v.id LIMIT 12";

    final ResultSet resultSet = statement.executeQuery(sql);

    final ReadDataTool readDataTool = new ReadDataTool(resultSet);
    final WriteDataTool writeDataTool= new WriteDataTool();

    //RowData rowdata;

    /*while((rowdata=readDataTool.readLine(resultSet)) != null)  {

      System.out.println("Write the Data");
      writeDataTool.writeLine(readDataTool.readLine(resultSet));
    //  System.out.println("Rows " + rowdata.getRowCount(resultSet));
      System.out.println("Key Value: " + rowdata.getTagsValue());
      System.out.println("time Value: " + rowdata.getTime());

    }*/

    int i=0;
    int countRows=15;

    while (i<countRows) {
      writeDataTool.writeLine(readDataTool.readLine(resultSet));
      i++;
    }

  }

}











