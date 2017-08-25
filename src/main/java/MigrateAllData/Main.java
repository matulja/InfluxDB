package MigrateAllData;

import Connection.ConnectionsInfoPostgreSQL;
import de.akquinet.jbosscc.guttenbase.connector.Connector;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import static Connection.ConnectionsInfoInfluxDB.influxDB;


/**
 * Created by mfehler on 04.07.17.
 */
public class Main {

  public static final String SOURCE = "source";

  public static void main(final String[] args) throws Exception {

    final ConnectorRepository connectorRepository = new ConnectorRepositoryImpl();
    connectorRepository.addConnectionInfo(SOURCE, new ConnectionsInfoPostgreSQL());
    final Connector connector = connectorRepository.createConnector(SOURCE);

    String selectSQL = "SELECT liftanddrivetime, readoutduration, identifier, extract (epoch from readouttime) * 1000 as time" +
            " FROM tdm_liftanddrivetimes ladt, tdm_vehicle v, tdm_vehicledataunit du " +
            "WHERE ladt.vehicle_id =v.id AND v.vehicledataunit_id = du.id AND du.deletedsince = 0 ";


    Connection connection = connector.openConnection();
    connection.setAutoCommit(false);

    Statement statement = connection.createStatement();
    statement.setFetchSize(5);

    final ResultSet resultSet = statement.executeQuery(selectSQL);
    final ReadDataTool readDataToolAll = new ReadDataTool(resultSet);
    final WriteDataTool writeDataTool= new WriteDataTool();


    DataRecord dataRecordAll;

    while((dataRecordAll = readDataToolAll.readLine()) != null)  {
      writeDataTool.writeLine(dataRecordAll);

    }

    statement.close();
    influxDB.close();
  }

}











