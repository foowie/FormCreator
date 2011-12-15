
package org.netbeans.modules.php.nette.generators.form;

import org.netbeans.api.db.explorer.DatabaseConnection;


public class PostgresColumnMetaDataDriver extends DefaultColumnMetaDataDriver {


	@Override
	public boolean match(DatabaseConnection connection) {
		return "org.postgresql.Driver".equals(connection.getDriverClass());
	}

	@Override
	protected String translateType(String type, ColumnMetaData metaData) {
		type = type.toUpperCase();
		if("SERIAL".equals(type) || "BIGSERIAL".equals(type))
			metaData.setAutoIncrement(true);
		if("NUMERIC".equals(type) || "DECIMAL".equals(type))
			metaData.setSize(null);
		return super.translateType(type, metaData);
	}

	@Override
	public ColumnMetaData getMetaData(TableInfo tableInfo, String columnName) {
		return super.getMetaData(tableInfo, columnName);
	}

}
