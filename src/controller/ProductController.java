package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.ProductDAO;
import defaultOperation.StandartFrameOperation;
import entity.Product;
import forms.ProductFrame;
import tableModal.ProductTableModal;

public class ProductController extends StandartFrameOperation{

	ProductDAO DAO;
	
	public ProductController(JFrame frame) {
		super(frame);
		try {
			DAO = new ProductDAO();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void actionSearchButton(String name, JTable table) {
		try {
			List<Product > list = null;

			if (name!= null && name.trim().length() > 0)
				list = DAO.search(name);
			else
				list = DAO.readAll();

			ProductTableModal model = new ProductTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(String name, int price) {
		if (name!=null && name.length() > 0 && price>0) {
			Product entity = new Product (name,price);
			try {
				DAO.create(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionUpdateButton(String name, int price, Long id) {
		if (name!= null && name.length() > 0 && price> 0 && id>0) {
			try {
				Product entity= new Product(name, price);
				entity.setId(id);
				DAO.update(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
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
		((ProductFrame) getFrame()).refreshView();
	}
}
