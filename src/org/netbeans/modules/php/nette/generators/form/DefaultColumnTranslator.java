
package org.netbeans.modules.php.nette.generators.form;

import java.util.HashMap;
import java.util.Map;

public class DefaultColumnTranslator implements IColumnTypeTranslator {

	protected Map<String, String> typesTranslator = new HashMap<String, String>();


	public DefaultColumnTranslator() {
		typesTranslator.put("DATE", Column.COLUMN_DATE);

		typesTranslator.put("DATETIME", Column.COLUMN_DATETIME);
		typesTranslator.put("TIMESTAMP", Column.COLUMN_DATETIME);

		typesTranslator.put("FLOAT", Column.COLUMN_FLOAT);
		typesTranslator.put("DOUBLE", Column.COLUMN_FLOAT);
		typesTranslator.put("REAL", Column.COLUMN_FLOAT);
		typesTranslator.put("DECIMAL", Column.COLUMN_FLOAT);
		typesTranslator.put("NUMERIC", Column.COLUMN_FLOAT);

		typesTranslator.put("INT", Column.COLUMN_INT);
		typesTranslator.put("BIGINT", Column.COLUMN_INT);
		typesTranslator.put("MEDIUMINT", Column.COLUMN_INT);
		typesTranslator.put("SMALLINT", Column.COLUMN_INT);
		typesTranslator.put("TINYINT", Column.COLUMN_INT);

		// pg
		typesTranslator.put("SERIAL", Column.COLUMN_INT);
		typesTranslator.put("BIGSERIAL", Column.COLUMN_INT);
		typesTranslator.put("INT2", Column.COLUMN_INT); // smallint
		typesTranslator.put("INT4", Column.COLUMN_INT); // int
		typesTranslator.put("INT8", Column.COLUMN_INT); // bigint
		typesTranslator.put("FLOAT4", Column.COLUMN_FLOAT); // real
		typesTranslator.put("FLOAT8", Column.COLUMN_FLOAT); // double precision


		typesTranslator.put("CHAR", Column.COLUMN_VARCHAR);
		typesTranslator.put("VARCHAR", Column.COLUMN_VARCHAR);
		typesTranslator.put("BPCHAR", Column.COLUMN_VARCHAR);

		typesTranslator.put("TEXT", Column.COLUMN_TEXT);
		typesTranslator.put("BLOB", Column.COLUMN_TEXT);
		typesTranslator.put("LONGVARCHAR", Column.COLUMN_TEXT);

		typesTranslator.put("BOOLEAN", Column.COLUMN_BOOLEAN);
		typesTranslator.put("BOOL", Column.COLUMN_BOOLEAN);
	}



	@Override
	public String translateType(String type) {
		type = type.toUpperCase();

		if(type.contains(" UNSIGNED")) {
			type = type.substring(0, type.indexOf(" UNSIGNED"));
		}

		if(typesTranslator.containsKey(type))
			return typesTranslator.get(type);
		else
			return type;
	}

}
