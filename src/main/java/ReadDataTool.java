import java.sql.ResultSet;
import java.util.Date;

/**
 * Created by mfehler on 27.06.17.
 */
public class ReadDataTool {

  private final ResultSet _resultSet;

  public ReadDataTool(final ResultSet resultSet) {

    _resultSet = resultSet;
  }

  public Point readLine(final ResultSet resultSet) {

    return new Point("1",2, "c");
  }


  public static void main(final String[] args) throws Exception {



  }
}
