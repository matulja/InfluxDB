import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mfehler on 27.06.17.
 */
public class Point {
  private final String _a;
  private final Integer _b;
  private final String _c;

  public Point(String a, Integer b, String c) {
    _a = a;
    _b = b;
    _c = c;
  }

  public List<String> getColumnNames(ResultSet rs) throws SQLException {
    List<String> names = new ArrayList<String>();
    ResultSetMetaData metadata = rs.getMetaData();

    for (int i = 0; i < metadata.getColumnCount(); i++) {
      names.add(metadata.getColumnName(i+1));
    }

    return names;
  }

}
