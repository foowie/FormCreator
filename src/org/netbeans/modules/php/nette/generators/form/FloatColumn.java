
package org.netbeans.modules.php.nette.generators.form;

public class FloatColumn extends Column {


	@Override
	protected String[] getTypes() {
		return new String[] {COLUMN_FLOAT};
	}

	@Override
	public IColumn fromValues(ColumnMetaData md) {
		return (new FloatColumn()).setMetaData(md);
	}

	@Override
	public String render() {
		StringBuilder sb = new StringBuilder();
		sb.append(addComponent("Text", md.getName(), md.getLabel()));

		if(md.getNullable() == false) {
			sb.append("\n").append(addRule(RULE_FILLED));
			sb.append("\n").append(addRule(RULE_FLOAT));
		}

		if(md.getSize() != null && md.getSize() > 0)
			sb.append("\n").append(addRule(RULE_MAX_LENGTH, md.getSize().toString()));


		if(md.getNullable() == true)
			sb.append("\n").append(addCondition(RULE_FILLED)).append("\n\t").append(addRule(RULE_FLOAT));


		sb.append(";");

		if(md.getNullable() == null)
			sb.append(" // Cant resolve NOT NULL value");

		sb.append("\n");
		return sb.toString();
	}


}
