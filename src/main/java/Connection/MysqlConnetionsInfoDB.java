package Connection;

import de.akquinet.jbosscc.guttenbase.connector.DatabaseType;
import de.akquinet.jbosscc.guttenbase.connector.impl.URLConnectorInfoImpl;

/**
 * Created by mfehler on 26.05.17.
 */
public class MysqlConnetionsInfoDB extends URLConnectorInfoImpl  {

    private static final long serialVersionUID = 1L;

    public MysqlConnetionsInfoDB() {
        super("jdbc:mysql://localhost:3306/", "mary", "password",
                "com.mysql.jdbc.Driver", "fitness", DatabaseType.MYSQL);
    }

}