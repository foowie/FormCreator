package org.netbeans.modules.php.nette.generators.form;

import java.util.ArrayList;
import java.util.List;

public class TableMetaData {

	private String tableName;
	private String schema;
	private List<ColumnMetaData> columns = new ArrayList<ColumnMetaData>();

	public List<ColumnMetaData> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnMetaData> columns) {
		this.columns = columns;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void addColumnMetaData(ColumnMetaData metaData) {
		this.columns.add(metaData);
	}
}
