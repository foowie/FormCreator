
package org.netbeans.modules.php.nette.generators.form;

public class DateColumn extends Column {

	@Override
	protected String[] getTypes() {
		return new String[] {COLUMN_DATE};
	}

	@Override
	public IColumn fromValues(ColumnMetaData md) {
		return (new DateColumn()).setMetaData(md);
	}

	@Override
	public String render() {
		StringBuilder sb = new StringBuilder();
		sb.append(addComponent("Date", md.getName(), md.getLabel()));

		if(md.getNullable() == false) {
			sb.append("\n").append(addRule(RULE_FILLED));
		}

		sb.append("\n").append(addRule(RULE_MAX_LENGTH, "10"));

		sb.append(";");

		if(md.getNullable() == null)
			sb.append(" // Cant resolve NOT NULL value");

		sb.append("\n");
		return sb.toString();
	}


}
