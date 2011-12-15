
package org.netbeans.modules.php.nette.generators.form;

public class UnknownColumn extends Column {

	@Override
	public boolean match(ColumnMetaData md) {
		return true;
	}

	@Override
	public IColumn fromValues(ColumnMetaData md) {
		return (new UnknownColumn()).setMetaData(md);
	}

	@Override
	public String render() {
		return "/* Unknown column type [" + md.getType() + "] of column [" + md.getName() + "]*/\n";
	}

}
