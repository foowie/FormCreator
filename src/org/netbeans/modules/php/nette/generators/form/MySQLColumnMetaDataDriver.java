
package org.netbeans.modules.php.nette.generators.form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.netbeans.api.db.explorer.DatabaseConnection;
import org.openide.util.Exceptions;

public class MySQLColumnMetaDataDriver extends DefaultColumnMetaDataDriver {

	@Override
	public boolean match(DatabaseConnection connection) {
		return "com.mysql.jdbc.Driver".equals(connection.getDriverClass());
	}

	@Override
	public ColumnMetaData getMetaData(TableInfo tableInfo, String columnName) {
		ColumnMetaData md = super.getMetaData(tableInfo, columnName);
		try {
			Connection conn = tableInfo.getConnection();
			PreparedStatement ps = conn.prepareStatement("SHOW FULL COLUMNS FROM " + tableInfo.getTable() + " WHERE Field = '" + columnName + "'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// fixing size on columns (JDBC error on INT type)
				String type = rs.getString("Type");
				int left = type.indexOf("(");
				
				if("ENUM".equals(md.getType()) || "SET".equals(md.getType())) {
					int right = type.indexOf(")");
					String[] items = type.substring(left+1, right).replace("'", "").split(",");
					md.setValues(items);
				} else {
					int right = type.indexOf(",") != -1 ? type.indexOf(",") : type.indexOf(")"); // , coz of decimal
					if(left >= 0) {
						md.setSize(Integer.parseInt(type.substring(left+1, right)));
					} else {
						md.setSize(null);
					}
				}
			} else {
				Exceptions.printStackTrace(new IllegalStateException("Column not found in database :("));
			}
		} catch (SQLException ex) {
			Exceptions.printStackTrace(ex);
		}
		return md;
	}
	

}
