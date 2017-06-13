package PostgreSQL;

import de.akquinet.jbosscc.guttenbase.connector.DatabaseType;
import de.akquinet.jbosscc.guttenbase.connector.impl.URLConnectorInfoImpl;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Created by mfehler on 21.03.17.
 */
public class ConnetionsInfoPostgreSQL extends URLConnectorInfoImpl {

private static final long serialVersionUID = 1L;

public ConnetionsInfoPostgreSQL() {
        super("jdbc:postgresql://localhost/still", "user", "pass",
        "org.postgresql.Driver", "public", DatabaseType.POSTGRESQL);
}

        public static Connection getConnection() throws ClassNotFoundException, SQLException {

                Connection con = null;

                // load the Driver Class
                Class.forName("org.postgresql.Driver");

                // create the connection now
                con = DriverManager.getConnection("jdbc:postgresql://localhost/still", "user", "pass");

                System.out.println("DB Connection created successfully");
                return con;
        }


}
