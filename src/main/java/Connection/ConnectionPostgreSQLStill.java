package Connection;

import de.akquinet.jbosscc.guttenbase.connector.DatabaseType;
import de.akquinet.jbosscc.guttenbase.connector.impl.URLConnectorInfoImpl;

/**
 * Created by mfehler on 27.06.17.
 */
public class ConnectionPostgreSQLStill extends URLConnectorInfoImpl {

  private static final long serialVersionUID = 1L;

  public ConnectionPostgreSQLStill() {
    super("jdbc:postgresql://still-db.spree.de/tdmka", "tdm", "secret",
            "org.postgresql.Driver", "public", DatabaseType.POSTGRESQL);
  }

}
