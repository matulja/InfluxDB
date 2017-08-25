package Connection;

import de.akquinet.jbosscc.guttenbase.connector.DatabaseType;
import de.akquinet.jbosscc.guttenbase.connector.impl.URLConnectorInfoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mfehler on 21.06.17.
 */
public class ConnectionsInfoPostgreSQLtdmka extends URLConnectorInfoImpl {

private static final long serialVersionUID = 1L;

public ConnectionsInfoPostgreSQLtdmka() {
        super("jdbc:postgresql://still-db.spree.de/tdmka", "tdm", "secret",
        "org.postgresql.Driver", "public", DatabaseType.POSTGRESQL);}

}
