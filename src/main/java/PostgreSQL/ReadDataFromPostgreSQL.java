package PostgreSQL;

import de.akquinet.jbosscc.guttenbase.meta.ColumnMetaData;
import de.akquinet.jbosscc.guttenbase.meta.DatabaseMetaData;
import de.akquinet.jbosscc.guttenbase.repository.ConnectorRepository;
import de.akquinet.jbosscc.guttenbase.repository.impl.ConnectorRepositoryImpl;
import de.akquinet.jbosscc.guttenbase.tools.ReadTableDataTool;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import static PostgreSQL._ReadDataFromPostgresAndWriteToInfluxDB.SOURCE;

/**
 * Created by mfehler on 27.06.17.
 */
public class ReadDataFromPostgreSQL {

  public static final Map<String, BigDecimal> resultFieldSetPostgre1 = new HashMap<String, BigDecimal>();
  public static final Map<String, BigDecimal> resultFieldSetPostgre2 = new HashMap<String, BigDecimal>();
  public static final Map<String, String> resultTagsPostgre = new HashMap<String, String>();
  public static final List<Date> resultTimePostgre = new ArrayList<Date>();

  final ConnectorRepository connectorRepository = new ConnectorRepositoryImpl();
  final ReadTableDataTool readTableDataTool = new ReadTableDataTool(connectorRepository);


  public Map<String, BigDecimal> getFieldSetPostgre1(DatabaseMetaData databaseMetaData) throws SQLException {


    ColumnMetaData columnMetaData = databaseMetaData.getTableMetaData("tdm_liftanddrivetimes").getColumnMetaData("readoutduration");
    int columnSize = columnMetaData.getTableMetaData().getTotalRowCount();
    final List<Map<String, Object>> tableData = readTableDataTool.readTableData(SOURCE, databaseMetaData.getTableMetaData("tdm_liftanddrivetimes"), columnSize);

    for (int i = 0; i < columnSize; i++) {

      final BigDecimal valueField1 = (BigDecimal) tableData.get(i).get("readoutduration");
      resultFieldSetPostgre1.put(columnMetaData.getColumnName(), valueField1);
    }

    return resultFieldSetPostgre1;
  }


}
