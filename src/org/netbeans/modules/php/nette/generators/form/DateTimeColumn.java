
package org.netbeans.modules.php.nette.generators.form;

public class DateTimeColumn extends Column {

	@Override
	protected String[] getTypes() {
		return new String[] {COLUMN_DATETIME};
	}

	@Override
	public IColumn fromValues(ColumnMetaData md) {
		return (new DateTimeColumn()).setMetaData(md);
	}

	@Override
	public String render() {
		StringBuilder sb = new StringBuilder();
		sb.append(addComponent("DateTime", md.getName(), md.getLabel()));

		if(md.getNullable() == false) {
			sb.append("\n").append(addRule(RULE_FILLED));
		}

		sb.append("\n").append(addRule(RULE_MAX_LENGTH, "19"));

		sb.append(";");

		if(md.getNullable() == null)
			sb.append(" // Cant resolve NOT NULL value");

		sb.append("\n");
		return sb.toString();
	}


}
