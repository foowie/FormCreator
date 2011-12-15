package org.netbeans.modules.php.nette.generators.form;


public class HiddenColumn extends Column {

	@Override
	public boolean match(ColumnMetaData md) {
		return md.isPrimaryKey();
	}

	@Override
	public IColumn fromValues(ColumnMetaData md) {
		return (new HiddenColumn()).setMetaData(md);
	}

	@Override
	public String render() {
		StringBuilder sb = new StringBuilder();
		sb.append(addComponent("Hidden", md.getName(), null));
		sb.append(";");
		sb.append("\n");
		return sb.toString();
	}

}