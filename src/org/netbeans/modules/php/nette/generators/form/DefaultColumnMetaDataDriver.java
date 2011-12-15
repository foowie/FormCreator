
package org.netbeans.modules.php.nette.generators.form;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.netbeans.api.db.explorer.DatabaseConnection;
import org.openide.util.Exceptions;

public class DefaultColumnMetaDataDriver implements IColumnMetaDataDriver {

	protected IColumnTypeTranslator translator;

	protected IColumnTypeTranslator getTranslator() {
		if(translator == null)
			translator = new DefaultColumnTranslator();
		return translator;
	}

	public void setTranslator(IColumnTypeTranslator translator) {
		this.translator = translator;
	}

	protected String translateType(String type, ColumnMetaData metaData) {
		return getTranslator().translateType(type);
	}


	@Override
	public boolean match(DatabaseConnection connection) {
		return true;
	}

	@Override
	public ColumnMetaData getMetaData(TableInfo tableInfo, String columnName) {
		ColumnMetaData md = new ColumnMetaData();

		try {
			Connection conn = tableInfo.getDatabaseConnection().getJDBCConnection();

			// column name, type, size, nullable, auto increment, default value
			ResultSet rs = conn.getMetaData().getColumns(conn.getCatalog(), tableInfo.getSchema(), tableInfo.getTable(), columnName);
			if(rs.next()) {
				md.setName(rs.getString("COLUMN_NAME"));
				
				md.setSize(rs.getInt("COLUMN_SIZE"));

				int nullable = rs.getInt("NULLABLE");
				if(nullable == 0)
					md.setNullable(false);
				else if(nullable == 1)
					md.setNullable(true);
				else
					md.setNullable(null);

				try { // pg driver dont have that column
					md.setAutoIncrement("YES".equals(rs.getString("IS_AUTOINCREMENT")));
				} catch(SQLException e) {
					md.setAutoIncrement(false);
				}
				md.setDefaultValue(rs.getString("COLUMN_DEF"));

				String type = rs.getString("TYPE_NAME");
				md.setUntranslatedType(type);
				md.setType(translateType(type, md));
				md.setUnsigned(type.toUpperCase().contains("UNSIGNED"));

			}
			rs.close();
			
			// column primary key
			md.setPrimaryKey(false);
			rs = conn.getMetaData().getPrimaryKeys(conn.getCatalog(), tableInfo.getSchema(), tableInfo.getTable());
			while(rs.next()) { // cache?
				String colName = rs.getString("COLUMN_NAME");
				if(columnName.equals(colName)) {
					md.setPrimaryKey(true);
					break;
				}
			}
			rs.close();

			// column foreign key
			md.setForeignKeyReferenceName(null);
			rs = conn.getMetaData().getImportedKeys(conn.getCatalog(), tableInfo.getSchema(), tableInfo.getTable());
			while(rs.next()) { // cache?
				String colName = rs.getString("FKCOLUMN_NAME");
				if(columnName.equals(colName)) {
					md.setForeignKeyReferenceName(rs.getString("PKTABLE_NAME") + "." + rs.getString("PKCOLUMN_NAME"));
					break;
				}
			}
			rs.close();

		} catch (SQLException ex) {
			Exceptions.printStackTrace(ex);
		}
		return md;
	}


	
}
