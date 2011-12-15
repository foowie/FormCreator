package org.netbeans.modules.php.nette.generators.form;

public class CheckboxColumn extends Column {
	
	/** Match also integer with length = 1 ? */
	public boolean matchInt1 = true;

	@Override
	public boolean match(ColumnMetaData md) {
		if("BOOLEAN".equals(md.getType()))
			return true;
		if(matchInt1 && super.match(md) && md.getSize() == 1)
			return true;
		return false;
	}

	@Override
	protected String[] getTypes() {
		return new String[] {"INT", "BIGINT", "SMALLINT", "TINYINT", "DECIMAL", "NUMERIC", "BIT"};
	}



	@Override
	public IColumn fromValues(ColumnMetaData md) {
		return (new CheckboxColumn()).setMetaData(md);
	}

	@Override
	public String render() {
		StringBuilder sb = new StringBuilder();
		sb.append(addComponent("Checkbox", md.getName(), md.getLabel()));
		sb.append(";");
		sb.append("\n");
		return sb.toString();
	}

	public boolean isMatchInt1() {
		return matchInt1;
	}

	public void setMatchInt1(boolean matchInt1) {
		this.matchInt1 = matchInt1;
	}

}
