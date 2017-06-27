package Connection;

import de.akquinet.jbosscc.guttenbase.connector.DatabaseType;
import de.akquinet.jbosscc.guttenbase.connector.impl.URLConnectorInfoImpl;

/**
 * Created by mfehler on 27.06.17.
 */
public class ConnectionPostgreSQL extends URLConnectorInfoImpl {

  private static final long serialVersionUID = 1L;

  public ConnectionPostgreSQL() {
    super("jdbc:postgresql://localhost/still", "user", "pass",
            "org.postgresql.Driver", "public", DatabaseType.POSTGRESQL);
  }

}
