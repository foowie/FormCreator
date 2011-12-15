
package org.netbeans.modules.php.nette.generators.form;

public class TextareaColumn extends Column {

	@Override
	protected String[] getTypes() {
		return new String[] {COLUMN_TEXT};
	}

	@Override
	public IColumn fromValues(ColumnMetaData md) {
		return (new TextareaColumn()).setMetaData(md);
	}

	@Override
	public String render() {
		StringBuilder sb = new StringBuilder();
		sb.append(addComponent("TextArea", md.getName(), md.getLabel()));

		if(md.getNullable() == false) {
			sb.append("\n").append(addRule(RULE_FILLED));
		}

		// todo: leave ? ... o.0 ... NO !!!
		/*if(md.getSize() != null && md.getSize() > 0)
			sb.append("\n").append(addRule(RULE_MAX_LENGTH, md.getSize().toString()));*/
		
		sb.append(";");

		if(md.getNullable() == null)
			sb.append(" // Cant resolve NOT NULL value");

		sb.append("\n");
		return sb.toString();
	}


}
