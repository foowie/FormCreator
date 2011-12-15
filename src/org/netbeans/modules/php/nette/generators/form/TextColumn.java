
package org.netbeans.modules.php.nette.generators.form;

public class TextColumn extends Column {

	@Override
	protected String[] getTypes() {
		return new String[] {COLUMN_VARCHAR};
	}

	@Override
	public IColumn fromValues(ColumnMetaData md) {
		return (new TextColumn()).setMetaData(md);
	}

	@Override
	public String render() {
		StringBuilder sb = new StringBuilder();
		sb.append(addComponent("Text", md.getName(), md.getLabel()));

		if(md.getNullable() == false) {
			sb.append("\n").append(addRule(RULE_FILLED));
		}

		if(md.getSize() != null && md.getSize() > 0)
			sb.append("\n").append(addRule(RULE_MAX_LENGTH, md.getSize().toString()));

		sb.append(";");

		if(md.getNullable() == null)
			sb.append(" // Cant resolve NOT NULL value");

		sb.append("\n");
		return sb.toString();
	}


}
