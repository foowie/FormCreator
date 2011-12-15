
package org.netbeans.modules.php.nette.generators.form;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.db.explorer.DatabaseConnection;
import org.openide.util.Exceptions;

public class TableMetaDataFactory {

	protected static List<IColumnMetaDataDriver> drivers = new ArrayList<IColumnMetaDataDriver>();

	static {
		register(new MySQLColumnMetaDataDriver());
		register(new PostgresColumnMetaDataDriver());
		register(new DefaultColumnMetaDataDriver());

	}

	public TableMetaData createTableMetaData(TableInfo tableInfo) {
		IColumnMetaDataDriver driver = getDriver(tableInfo.getDatabaseConnection());
		if(driver == null)
			Exceptions.printStackTrace(new NullPointerException("No database driver found ! Driver class=[" + tableInfo.getDatabaseConnection().getDriverClass() + "]"));
		TableMetaData tableMetaData = new TableMetaData();
		tableMetaData.setTableName(tableInfo.getTable());
		tableMetaData.setSchema(tableInfo.getSchema());
		try {
			Connection conn = tableInfo.getDatabaseConnection().getJDBCConnection();
			ResultSet rs = conn.getMetaData().getColumns(null, tableInfo.getSchema(), tableInfo.getTable(), null);
			while(rs.next()) {
				String name = rs.getString("COLUMN_NAME");
				ColumnMetaData metaData = driver.getMetaData(tableInfo, name);
				tableMetaData.addColumnMetaData(metaData);
			}
		} catch (SQLException ex) {
			Exceptions.printStackTrace(ex);
		}
		return tableMetaData;
	}

	public static void register(IColumnMetaDataDriver driver) {
		drivers.add(driver);
	}

	public IColumnMetaDataDriver getDriver(DatabaseConnection conn) {
		for(IColumnMetaDataDriver driver : drivers)
			if(driver.match(conn))
				return driver;
		return null;
	}
	
}
