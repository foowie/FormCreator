package org.netbeans.modules.php.nette.generators.form;

import java.util.List;

public class FormRenderer {

	private String componentName = "";
	
	private List<IColumn> columns;

	public List<IColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<IColumn> columns) {
		this.columns = columns;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String render() {
		if(columns == null)
			throw new NullPointerException("No columns defined !");

		if(componentName.length() > 0)
			componentName = componentName.substring(0, 1).toUpperCase() + componentName.substring(1);

		StringBuilder sb = new StringBuilder();
		sb.append("protected function createComponent").append(componentName).append("($name) {\n");
		sb.append("\t$form = new Form($this, $name);\n\n");
		for(IColumn col : columns) {
			sb.append(col.render());
		}
		sb.append("\n\t$form->addSubmit('submitButton', 'Submit')->onClick[] = callback($this, 'submit'.$name);\n");
		sb.append("\t$form->addSubmit('cancelButton', 'Cancel')->setValidationScope(false)->onClick[] = callback($this, 'cancel'.$name);\n");

		sb.append("\n\treturn $form;\n}\n\n");

		sb.append("public function submit").append(componentName).append("($button) {\n$values = $button->getForm()->getValues();\n	/* TODO: handle submit */\n$this->redirect(\"this\");\n}\n\n");
		sb.append("public function cancel").append(componentName).append("($button) {\n	/* TODO: handle submit */\n$this->redirect(\"this\");\n}\n");
		return sb.toString();
	}

}
