package org.netbeans.modules.php.nette.generators.form;

public class ColumnMetaData {

	private String name = null;
	private String label = null;
	private String type = null;
	private String untranslatedType = null;
	private Boolean nullable = null;
	private Integer size = null;
	private boolean primaryKey = false;
	private boolean autoIncrement = false;
	private String foreignKeyReferenceName = null;
	private String defaultValue = null;
	private boolean unsigned = false;
	private String[] values = null;

	public ColumnMetaData() {
	}

	public ColumnMetaData(String name, String type) {
		setName(name);
		setType(type);
	}

	final public void setName(String name) {
		this.name = name;
		if (this.label == null) {
			label = name.replace("_", " ").trim(); // remove _
			if (label.toLowerCase().endsWith(" id")) {
				label = label.substring(0, label.length() - 3); // xyz_id -> xyz
			}
			if (label.length() > 0) { // xyz -> Xyz
				label = label.substring(0, 1).toUpperCase() + label.substring(1);
			}
		}
	}

	final public void setType(String type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public String getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isForeignKey() {
		return foreignKeyReferenceName != null;
	}

	public String getForeignKeyReferenceName() {
		return foreignKeyReferenceName;
	}

	public void setForeignKeyReferenceName(String foreignKeyReferenceName) {
		this.foreignKeyReferenceName = foreignKeyReferenceName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isUnsigned() {
		return unsigned;
	}

	public void setUnsigned(boolean unsigned) {
		this.unsigned = unsigned;
	}

	public String getUntranslatedType() {
		return untranslatedType;
	}

	public void setUntranslatedType(String untranslatedType) {
		this.untranslatedType = untranslatedType;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "ColumnMetaData{" + "name=" + name + "label=" + label + "type=" + type + "nullable=" + nullable + "size=" + size + "primaryKey=" + primaryKey + "autoIncrement=" + autoIncrement + "foreignKeyReferenceName=" + foreignKeyReferenceName + '}';
	}
}
