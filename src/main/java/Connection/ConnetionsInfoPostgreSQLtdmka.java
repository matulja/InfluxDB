package Connection;

import de.akquinet.jbosscc.guttenbase.connector.DatabaseType;
import de.akquinet.jbosscc.guttenbase.connector.impl.URLConnectorInfoImpl;

/**
 * Created by mfehler on 21.03.17.
 */
public class ConnetionsInfoPostgreSQLtdmka extends URLConnectorInfoImpl {

private static final long serialVersionUID = 1L;

public ConnetionsInfoPostgreSQLtdmka() {
        super("jdbc:postgresql://localhost/tdmka", "tdm", "secret",
        "org.postgresql.Driver", "public", DatabaseType.POSTGRESQL);}

}
