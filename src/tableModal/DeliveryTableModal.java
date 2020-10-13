package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Client;
import entity.Delivery;

public class DeliveryTableModal extends AbstractTableModel {

	public static final int ID_COL = 0;
	public static final int PRODUCT_COL = 1;
	public static final int PROVIDER_COL = 2;
	public static final int DATE_COL = 3;
	public static final int COUNT_COL = 4;
	public static final int ENTITY_COL = 5;

	private final String[] columnNames = { "Id", "Product", "Provider", "Date", "Count" };
	private List<Delivery> list;

	public DeliveryTableModal(List<Delivery> theProviders) {
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

		Delivery temp = list.get(row);

		switch (col) {
		case ID_COL:
			return temp.getId();
		case PRODUCT_COL:
			return temp.getProduct().getName();
		case PROVIDER_COL:
			return temp.getProvider().getName();
		case DATE_COL:
			return temp.getDate();
		case COUNT_COL:
			return temp.getCount();
		case ENTITY_COL:
			return temp;
		default:
			return temp.getProduct().getName() + " " + temp.getCount();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
