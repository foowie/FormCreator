
package org.netbeans.modules.php.nette.generators.form;

public class IntColumn extends Column {


	@Override
	protected String[] getTypes() {
		return new String[] {COLUMN_INT};
	}

	@Override
	public IColumn fromValues(ColumnMetaData md) {
		return (new IntColumn()).setMetaData(md);
	}

	@Override
	public String render() {
		StringBuilder sb = new StringBuilder();
		sb.append(addComponent("Text", md.getName(), md.getLabel()));

		if(md.getNullable() == false) {
			sb.append("\n").append(addRule(RULE_FILLED));
			sb.append("\n").append(addRule(RULE_INTEGER));
			if(md.isUnsigned())
				sb.append("\n").append(addRule(RULE_RANGE, "array(0, null)"));
		}

		if(md.getSize() != null && md.getSize() > 0)
			sb.append("\n").append(addRule(RULE_MAX_LENGTH, md.getSize().toString()));


		if(md.getNullable() == true) {
			sb.append("\n").append(addCondition(RULE_FILLED)).append("\n\t").append(addRule(RULE_INTEGER));
			if(md.isUnsigned())
				sb.append("\n\t").append(addRule(RULE_RANGE, "array(0, null)"));
		}


		sb.append(";");

		if(md.getNullable() == null)
			sb.append(" // Cant resolve NOT NULL value");

		sb.append("\n");
		return sb.toString();
	}


}
