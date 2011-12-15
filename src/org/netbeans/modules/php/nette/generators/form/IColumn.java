package org.netbeans.modules.php.nette.generators.form;


public interface IColumn {

	public static final String COLUMN_INT = "INT";
	public static final String COLUMN_VARCHAR = "VARCHAR";
	public static final String COLUMN_FLOAT = "FLOAT";
	public static final String COLUMN_BOOLEAN = "BOOLEAN";
	public static final String COLUMN_TEXT = "TEXT";
	public static final String COLUMN_DATE = "DATE";
	public static final String COLUMN_DATETIME = "DATETIME";
	public static final String COLUMN_TIME = "TIME";

	/**
	 * Is this column applicable for given type?
	 * @param type Name of column
	 * @return Y/N
	 */
	public boolean match(ColumnMetaData md);

	/**
	 * Create new column from metadata
	 * @param rs
	 */
	public IColumn fromValues(ColumnMetaData md);

	/**
	 * Renter column
	 * @param sb
	 */
	public String render();
}
