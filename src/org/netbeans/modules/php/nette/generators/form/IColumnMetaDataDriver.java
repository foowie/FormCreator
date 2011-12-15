package org.netbeans.modules.php.nette.generators.form;

import org.netbeans.api.db.explorer.DatabaseConnection;

public interface IColumnMetaDataDriver {

	public boolean match(DatabaseConnection connection);

	public ColumnMetaData getMetaData(TableInfo tableInfo, String columnName);

}
