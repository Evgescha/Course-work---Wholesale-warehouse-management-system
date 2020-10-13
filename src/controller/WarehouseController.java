package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.WarehouseDAO;
import defaultOperation.StandartFrameOperation;
import entity.Product;
import entity.Warehouse;
import forms.WarehouseFrame;
import tableModal.ClientTableModal;
import tableModal.WarehouseTableModal;

public class WarehouseController extends StandartFrameOperation{

	WarehouseDAO DAO;
	
	public WarehouseController(JFrame frame) {
		super(frame);
		try {
			DAO = new WarehouseDAO();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void actionSearchButton(String productName, JTable table) {
		try {
			List<Warehouse> list = null;

			if (productName!= null && productName.trim().length() > 0)
				list = DAO.search(productName);
			else
				list = DAO.readAll();

			WarehouseTableModal model = new WarehouseTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(Product product, int count) {
		if (product!=null && count >= 0) {
			Warehouse entity = new Warehouse(product,count);
			try {
				DAO.create(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionUpdateButton(Product product, int count, Long id) {
			try {
				Warehouse  entity= new Warehouse(product,count);
				entity.setId(id);
				DAO.update(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	

	public void actionDeleteButton(long id) {
		if(id>0)
			try {
				DAO.Delete(id);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице" , "Ошибка", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		
	}

	public void refrechView() {
		((WarehouseFrame)getFrame()).refreshView();
	}
	public WarehouseDAO getDAO() {return DAO;}
}
