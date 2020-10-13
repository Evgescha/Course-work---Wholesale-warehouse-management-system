package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Provider;

public class ProviderTableModal extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;
	private static final int ADRES_COL = 2;
	private static final int PHONE_COL = 3;
	private static final int EMAIL_COL = 4;

	private final String[] columnNames = {"Id", "Name", "Adres", "Phone", "Email" };
	private List<Provider> list;

	public ProviderTableModal(List<Provider> theProviders) {
		list = theProviders;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Provider temp = list.get(row);

		switch (col) {
		case ID_COL:
			return temp.getId();
		case NAME_COL:
			return temp.getName();
		case ADRES_COL:
			return temp.getAdres();
		case PHONE_COL:
			return temp.getPhone();
		case EMAIL_COL:
			return temp.getEmail();
		default:
			return temp.getName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
