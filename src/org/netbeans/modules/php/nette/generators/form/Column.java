package org.netbeans.modules.php.nette.generators.form;

abstract public class Column implements IColumn {

	public static final String RULE_FILLED = "Form::FILLED";
	public static final String RULE_INTEGER = "Form::INTEGER";
	public static final String RULE_FLOAT = "Form::FLOAT";
	public static final String RULE_MAX_LENGTH = "Form::MAX_LENGTH";
	public static final String RULE_RANGE = "Form::RANGE";


	protected ColumnMetaData md;

	protected String[] getTypes() {
		return new String[] {};
	}

	@Override
	public boolean match(ColumnMetaData md) {
		String[] types = getTypes();
		for(String typ : types)
			if(typ.equals(md.getType()))
				return true;
		return false;
	}

	public Column setMetaData(ColumnMetaData md) {
		this.md = md;
		return this;
	}

	protected String addRule(String type) {
		return addRule(type, null, null);
	}

	protected String addRule(String type, String args) {
		return addRule(type, args, null);
	}

	protected String addRule(String type, String args, String message) {
		String rule =  "->addRule(" + type;
		if(message == null) {
			if(args != null)
				rule += ", null";
		} else
			rule += ", '" + message + "'";
		if(args != null)
			rule += ", " + args;
		rule += ")";
		return rule;
	}

	protected String addCondition(String type) {
		return "->addCondition(" + type + ")";
	}


	protected String addComponent(String type, String name, String label) {
		return addComponent(type, name, label, null);
	}

	protected String addComponent(String type, String name, String label, String args) {
		String comp = "$form->add" + type + "('" + name + "'";
		if(label != null)
			 comp += ", '" + label + "'";
		if(args != null)
			comp += args;
		comp += ")";
		return comp;
	}

}
