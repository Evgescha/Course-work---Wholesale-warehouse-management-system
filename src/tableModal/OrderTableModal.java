package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Order;

/**
 * 
 * @author admin
 * модель таблицы заказа
 */
public class OrderTableModal extends AbstractTableModel {

	public  static final int ID_COL = 0;
	public static final int EMPLOYEE_COL = 1;
	public  static final int CLIENT_COL = 2;
	public  static final int PRODUCT_COL = 3;
	public  static final int COUNT_COL = 4;
	public static final int DATES_COL = 5;
	public static final int ENTITY_COL = 6;

	private final String[] columnNames = {"Id","Employee", "Client", "Product", "Count","Date" };
	private List<Order> list;

	public OrderTableModal(List<Order> theProviders) {
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

		Order temp = list.get(row);

		switch (col) {
		case ID_COL:
			return temp.getId();
		case EMPLOYEE_COL:
			return temp.getEmployee().getFio();
		case CLIENT_COL:
			return temp.getClient().getFio();
		case PRODUCT_COL:
			return temp.getProduct().getName();
		case COUNT_COL:
			return temp.getCount();
		case DATES_COL:
			return temp.getDate();
		
		default:
			return temp;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
