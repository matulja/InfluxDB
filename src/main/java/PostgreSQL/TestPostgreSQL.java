package PostgreSQL;

import de.akquinet.jbosscc.guttenbase.meta.ColumnMetaData;
import de.akquinet.jbosscc.guttenbase.meta.DatabaseMetaData;
import de.akquinet.jbosscc.guttenbase.meta.TableMetaData;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

/**
 * Created by mfehler on 13.06.17.
 */
public class TestPostgreSQL {

  public static final String SOURCE = "source";

  public static void main(final String[] args) throws Exception {


    final ConnectorRepository connectorRepository = new ConnectorRepositoryImpl();
    connectorRepository.addConnectionInfo(SOURCE, new ConnetionsInfoPostgreSQL());

    DatabaseMetaData source = connectorRepository.getDatabaseMetaData(SOURCE);

    System.out.println("Schema " +source.getSchema());
    System.out.println("Table Name " + source.getTableMetaData().toString());

    TableMetaData tableMetaData= connectorRepository.getDatabaseMetaData(SOURCE).getTableMetaData("tdm_liftanddrivetimes");
    tableMetaData.getColumnMetaData();

    System.out.println("Name Column "+ tableMetaData.getColumnMetaData() );
    System.out.println("Type Column "+ tableMetaData.getTableName());
    System.out.println("Column Count " +  tableMetaData.getColumnCount());



  }
}
