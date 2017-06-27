import java.sql.ResultSet;

/**
 * Created by mfehler on 27.06.17.
 */
public class Main {

  public static void main(final String[] args) throws Exception {



    final ResultSet resultSet =null;

    final ReadDataTool reader = new ReadDataTool(resultSet);
    final WriteDataTool writer = new WriteDataTool();
    Point line;

    while((line=reader.readLine(resultSet)) != null){
      writer.writeLine(line);
    }





  }

}









