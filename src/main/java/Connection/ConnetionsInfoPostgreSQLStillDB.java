package Connection;

import de.akquinet.jbosscc.guttenbase.connector.DatabaseType;
import de.akquinet.jbosscc.guttenbase.connector.impl.URLConnectorInfoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mfehler on 21.03.17.
 */
public class ConnetionsInfoPostgreSQLStillDB extends URLConnectorInfoImpl {

private static final long serialVersionUID = 1L;

public ConnetionsInfoPostgreSQLStillDB() {
        super("jdbc:postgresql://still-db.spree.de/tdmka", "tdm", "secret",
        "org.postgresql.Driver", "public", DatabaseType.POSTGRESQL);
}

        public static Connection getConnection() throws ClassNotFoundException, SQLException {

                Connection con = null;
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://still-db.spree.de/tdmka", "tdm", "secret");

                System.out.println("DB Connection created successfully");
                return con;
        }


}
