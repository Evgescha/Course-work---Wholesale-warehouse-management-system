package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Product;

/**
 * 
 * @author admin
 * модель таблицы товара
 */
public class ProductTableModal extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;
	private static final int PRICE_COL = 2;

	private final String[] columnNames = {"Id", "Name", "Price"};
	private List<Product > list;

	public ProductTableModal(List<Product > theProviders) {
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

		Product temp = list.get(row);

		switch (col) {
		case ID_COL:
			return temp.getId();
		case NAME_COL:
			return temp.getName();
		case PRICE_COL:
			return temp.getPrice();
		default:
			return temp.getName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
