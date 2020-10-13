package controller;

import java.sql.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.OrderDAO;
import defaultOperation.StandartFrameOperation;
import entity.Client;
import entity.Employee;
import entity.Order;
import entity.Product;
import forms.OrderFrame;
import tableModal.OrderTableModal;

public class OrderController extends StandartFrameOperation {

	OrderDAO DAO;

	public OrderController(JFrame frame) {
		super(frame);
		try {
			DAO = new OrderDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionSearchButton(String productName, JTable table) {
		try {
			List<Order> list = null;

			if (productName != null && productName.trim().length() > 0)
				list = DAO.search(productName);
			else
				list = DAO.readAll();

			OrderTableModal model = new OrderTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(Long id_employee, Long id_client, Long id_product, Date date, int count) {
		if (id_product > 0 && id_employee > 0 && id_client > 0 && count > 0) {
			Product product = null;
			Employee employee = null;
			Client client = null;
			try {
				employee = ApplicationController.employeeController.getDAO().read(id_employee).get(0);
				client = ApplicationController.clientController.getDAO().read(id_client).get(0);
				product = ApplicationController.productController.getDAO().read(id_product).get(0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Order entity = new Order(employee, client, product, date, count);
			try {
				DAO.create(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	public void actionUpdateButton(Long id_employee, Long id_client, Long id_product, Date date, int count, Long id) {
		if (id_product > 0 && id_employee > 0 && id_client > 0 && count > 0 && id > 0) {
			Product product = null;
			Employee employee = null;
			Client client = null;
			try {
				employee = ApplicationController.employeeController.getDAO().read(id_employee).get(0);
				client = ApplicationController.clientController.getDAO().read(id_client).get(0);
				product = ApplicationController.productController.getDAO().read(id_product).get(0);

				Order entity = new Order(employee, client, product, date, count);
				entity.setId(id);
				DAO.update(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionDeleteButton(long id) {
		if (id > 0)
			try {
				DAO.Delete(id);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице",
						"Ошибка", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}

	}

	public void refrechView() {
		((OrderFrame) getFrame()).refreshView();
	}

	public OrderDAO getDAO() {
		return DAO;
	}
}
