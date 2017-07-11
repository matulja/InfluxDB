package _MigrateAllData;

import Connection.ConnetionsInfoPostgreSQLStill;
import de.akquinet.jbosscc.guttenbase.connector.Connector;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static Connection.ConnectionInfoInfluxDB.influxDB;


/**
 * Created by mfehler on 27.06.17.
 */
public class Main_All {

  public static final String SOURCE = "source";

  public static void main(final String[] args) throws Exception {

    final ConnectorRepository connectorRepository = new ConnectorRepositoryImpl();
    connectorRepository.addConnectionInfo(SOURCE, new ConnetionsInfoPostgreSQLStill());
    final Connector connector = connectorRepository.createConnector(SOURCE);

    String selectSQL = "SELECT liftanddrivetime, readoutduration, identifier, extract (epoch from readouttime) * 1000 as time" +
            " FROM tdm_liftanddrivetimes ladt, tdm_vehicle v, tdm_vehicledataunit du " +
            "WHERE ladt.vehicle_id =v.id AND v.vehicledataunit_id = du.id AND du.deletedsince = 0";

    Connection connection = connector.openConnection();
    connection.setAutoCommit(false);

    Statement statement = connection.createStatement();
    statement.setFetchSize(1000);

    final ResultSet resultSet = statement.executeQuery(selectSQL);
    final ReadDataTool_All readDataToolAll = new ReadDataTool_All(resultSet);
    final WriteDataTool_All2 writeDataTool_all2 = new WriteDataTool_All2();

   // System.out.println("Rows: " + readDataToolAll.getRowCount(resultSet)); //499990947 - 40 min


    DataRecord_All dataRecordAll;

    while((dataRecordAll = readDataToolAll.readLine()) != null)  {
      writeDataTool_all2.writeLine(dataRecordAll);
    }

    statement.close();


  }

}










