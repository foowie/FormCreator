
package org.netbeans.modules.php.nette.generators.form;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import org.netbeans.api.db.explorer.DatabaseConnection;

public class DatabaseConnectionComboBoxRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			DefaultListCellRenderer component = (DefaultListCellRenderer)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if(!(value instanceof DatabaseConnection))
				return component;
			DatabaseConnection conn = (DatabaseConnection)value;
			component.setText(conn == null ? "" : conn.getDisplayName());
			return component;
		}
}
