package org.netbeans.modules.php.nette.generators.form;

public class SelectboxColumn extends Column {

	@Override
	public boolean match(ColumnMetaData md) {
		return md.isForeignKey() || "ENUM".equals(md.getType()) || "SET".equals(md.getType());
	}

	@Override
	public IColumn fromValues(ColumnMetaData md) {
		return (new SelectboxColumn()).setMetaData(md);
	}


	private String implode(String[] items, String deliminer, String pre, String after) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < items.length; i++) {
			sb.append(pre).append(items[i]).append(after);
			if(i != items.length - 1)
				sb.append(deliminer);
		}
		return sb.toString();
	}

	@Override
	public String render() {
		StringBuilder sb = new StringBuilder();
		String hint;

		if(md.getValues() != null)
			hint = "";
		else
			hint = ", array() /* TODO: Pairs from [" + md.getForeignKeyReferenceName() + "]*/";
		sb.append(addComponent("Select", md.getName(), md.getLabel(), hint));

		if(md.getValues() != null)
			sb.append("->setItems(array(").append(implode(md.getValues(), ", ", "'", "'")).append("), false)");

		if(md.getNullable() == false)
			sb.append("\n").append(addRule(RULE_FILLED));
		if(md.getNullable() == true) {
			sb.append("->setPrompt(\"\")");
		}



		sb.append(";");
		if(md.getNullable() == null)
			sb.append(" // Cant resolve NOT NULL value");

		sb.append("\n");
		return sb.toString();
	}

}
