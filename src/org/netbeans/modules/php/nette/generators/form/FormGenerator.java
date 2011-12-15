/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.modules.php.nette.generators.form;

import java.util.Collections;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplate;
import org.netbeans.lib.editor.codetemplates.api.CodeTemplateManager;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.netbeans.spi.editor.codegen.CodeGeneratorContextProvider;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

public class FormGenerator implements CodeGenerator {

	JTextComponent textComp;

	/**
	 *
	 * @param context containing JTextComponent and possibly other items registered by {@link CodeGeneratorContextProvider}
	 */
	private FormGenerator(Lookup context) { // Good practice is not to save Lookup outside ctor
		textComp = context.lookup(JTextComponent.class);
	}

	public static class Factory implements CodeGenerator.Factory {

		@Override
		public List<? extends CodeGenerator> create(Lookup context) {
			return Collections.singletonList(new FormGenerator(context));
		}
	}

	/**
	 * The name which will be inserted inside Insert Code dialog
	 */
	@Override
	public String getDisplayName() {
		return "Form from DB ...";
	}

	/**
	 * This will be invoked when user chooses this Generator from Insert Code
	 * dialog
	 */
	@Override
	public void invoke() {
		TableSelectorPanel panel = new TableSelectorPanel();
		DialogDescriptor dd = new DialogDescriptor(panel, "Create Form ...");
		Object result = DialogDisplayer.getDefault().notify(dd);
		if (result == NotifyDescriptor.OK_OPTION) {
			TableInfo selectedTable = panel.getSelectedTable();
			if(selectedTable == null)
				return;

			TableMetaDataFactory tableMetaDataFactory = new TableMetaDataFactory();
			TableMetaData tableMetaData = tableMetaDataFactory.createTableMetaData(selectedTable);
			ColumnFactory columnFactory = new ColumnFactory();
			List<IColumn> cols = columnFactory.createColumns(tableMetaData);
			FormRenderer fr = new FormRenderer();
			fr.setColumns(cols);
			fr.setComponentName(panel.getComponentName());
			String form = fr.render();

			try {
				CodeTemplateManager manager = CodeTemplateManager.get(textComp.getDocument());
				CodeTemplate template = manager.createTemporary(form);
				template.insert(textComp);
			} catch (Exception ex) {
				Exceptions.printStackTrace(ex);
			}
		}
	}
}
