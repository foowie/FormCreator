package org.netbeans.modules.php.nette.generators.form;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.Exceptions;

public class ColumnFactory {

	protected static List<IColumn> columns = new ArrayList<IColumn>();

	static {
		register(new HiddenColumn());
		register(new SelectboxColumn());
		register(new CheckboxColumn());
		register(new IntColumn());
		register(new FloatColumn());
		register(new TextColumn());
		register(new DateColumn());
		register(new TextareaColumn());
		register(new DateTimeColumn());
		register(new UnknownColumn());
	}


	public List<IColumn> createColumns(TableMetaData tableMetaData) {
		List<IColumn> items = new ArrayList<IColumn>();
		for(ColumnMetaData columnMetaData : tableMetaData.getColumns()) {
			IColumn column = createColumn(columnMetaData);
			if(column == null)
				Exceptions.printStackTrace(new NullPointerException("No column driver found for type [" + columnMetaData.getType() + "] !"));
			else
				items.add(column);
		}

		return items;
	}

	public IColumn createColumn(ColumnMetaData metaData) {
		metaData.setType(metaData.getType().toUpperCase());
		for(IColumn col : columns)
			if(col.match(metaData))
				return col.fromValues(metaData);
		return null;
	}

	public static void register(IColumn column) {
		columns.add(column);
	}

}
