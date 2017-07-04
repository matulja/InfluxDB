package Trash;

import Connection.ConnetionsInfoPostgreSQL;
import Trash.DataRecord_;
import de.akquinet.jbosscc.guttenbase.connector.Connector;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by mfehler on 27.06.17.
 */
public class Main_ {

  public static final String SOURCE = "source";

  public static void main(final String[] args) throws Exception {

    final ConnectorRepository connectorRepository = new ConnectorRepositoryImpl();
    connectorRepository.addConnectionInfo(SOURCE, new ConnetionsInfoPostgreSQL());
    final Connector connector = connectorRepository.createConnector(SOURCE);

    Connection connection = connector.openConnection();
    Statement statement = connection.createStatement();

    String sql = "SELECT liftanddrivetime, readoutduration, identifier, extract (epoch from readouttime) * 1000 as time" +
            " FROM tdm_liftanddrivetimes ladt, tdm_vehicle v, tdm_vehicledataunit du " +
            "WHERE ladt.vehicle_id =v.id";

    final ResultSet resultSet = statement.executeQuery(sql);

    final ReadDataTool_ readDataTool = new ReadDataTool_(resultSet);
    final WriteDataTool_ writeDataTool= new WriteDataTool_();

    DataRecord_ dataRecord;
    while((dataRecord=readDataTool.readLine()) != null)  {
      writeDataTool.writeLine(dataRecord);
    }



  }

}











