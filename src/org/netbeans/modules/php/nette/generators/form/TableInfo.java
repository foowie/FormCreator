package org.netbeans.modules.php.nette.generators.form;

import java.sql.Connection;
import org.netbeans.api.db.explorer.DatabaseConnection;

public class TableInfo {

	private String table;
	private String schema;
	private DatabaseConnection databaseConnection;

	public TableInfo(String table, String schema, DatabaseConnection databaseConnection) {
		this.table = table;
		this.schema = schema;
		this.databaseConnection = databaseConnection;
	}

	public DatabaseConnection getDatabaseConnection() {
		return databaseConnection;
	}

	public String getSchema() {
		return schema;
	}

	public String getTable() {
		return table;
	}

	public Connection getConnection() {
		return databaseConnection.getJDBCConnection();
	}

	@Override
	public String toString() {
		return ((schema == null) ? "" : schema + ".") + table;
	}
}
