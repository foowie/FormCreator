package org.netbeans.modules.php.nette.generators.form;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import org.netbeans.api.db.explorer.ConnectionManager;
import org.netbeans.api.db.explorer.DatabaseConnection;
import org.openide.util.Exceptions;

public class DatabaseConnectionComboBoxModel extends DefaultComboBoxModel implements ComboBoxModel {

	private boolean openSelectedConnection = false; // !
	private boolean closeUnselectedConnections = false; // !
	private ConnectionManager cm = new ConnectionManager();

	public DatabaseConnectionComboBoxModel() {
		super();
		DatabaseConnection[] connections = this.cm.getConnections();
		for(DatabaseConnection conn : connections)
			this.addElement(conn);
		this.setSelectedItem(null);
		this.openSelectedConnection = true;
		this.closeUnselectedConnections = true;
	}

	@Override
	final public void setSelectedItem(Object databaseConnection) {
		if(databaseConnection != null && !(databaseConnection instanceof DatabaseConnection))
			throw new IllegalArgumentException("Item must be instance of DatabaseConnection ![" + databaseConnection.getClass() + "] given !");
		// disconnect old conn
		if(this.closeUnselectedConnections) {
			DatabaseConnection conn = this.getSelectedItem();
			if(conn != null)
				cm.disconnect(conn);
		}
		super.setSelectedItem(databaseConnection);
		// connect to selected conn
		if(this.openSelectedConnection && databaseConnection != null) {
			DatabaseConnection conn = (DatabaseConnection) databaseConnection;
			this.cm.showConnectionDialog(conn);
			if(conn.getJDBCConnection() == null) {
				this.setSelectedItem(null);
				Exceptions.printStackTrace(new NullPointerException("Something is wrong with given connection !"));
			}
		}
	}

	@Override
	final public DatabaseConnection getSelectedItem() {
		return (DatabaseConnection)super.getSelectedItem();
	}

	@Override
	final public DatabaseConnection getElementAt(int index) {
		return (DatabaseConnection)super.getElementAt(index);
	}

	@Override
    final public void addElement(Object databaseConnection) {
		if(!(databaseConnection instanceof DatabaseConnection))
			throw new IllegalArgumentException("Item must be instance of DatabaseConnection ![" + databaseConnection.getClass() + "] given !");
		super.addElement(databaseConnection);
    }

	@Override
    final public void insertElementAt(Object databaseConnection, int index) {
		if(!(databaseConnection instanceof DatabaseConnection))
			throw new IllegalArgumentException("Item must be instance of DatabaseConnection ![" + databaseConnection.getClass() + "] given !");
		super.insertElementAt(databaseConnection, index);
    }

	final public boolean isCloseUnselectedConnections() {
		return closeUnselectedConnections;
	}

	final public void setCloseUnselectedConnections(boolean closeUnselectedConnections) {
		this.closeUnselectedConnections = closeUnselectedConnections;
	}

	final public boolean isOpenSelectedConnection() {
		return openSelectedConnection;
	}

	final public void setOpenSelectedConnection(boolean openSelectedConnection) {
		this.openSelectedConnection = openSelectedConnection;
	}

}
