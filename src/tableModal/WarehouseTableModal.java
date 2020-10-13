package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Client;
import entity.Warehouse;

public class WarehouseTableModal extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int PRODUCT_COL = 1;
	private static final int COUNT_COL = 2;

	private final String[] columnNames = {"Id", "Product", "Count" };
	private List<Warehouse> list;

	public WarehouseTableModal(List<Warehouse> theProviders) {
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

		Warehouse temp = list.get(row);

		switch (col) {
		case ID_COL:
			return temp.getId();
		case PRODUCT_COL:
			return temp.getProduct();
		case COUNT_COL:
			return temp.getCount();
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
