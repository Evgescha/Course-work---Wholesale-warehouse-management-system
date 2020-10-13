package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.ClientDAO;
import dao.EmployeeDAO;
import defaultOperation.StandartFrameOperation;
import entity.Employee;
import forms.EmployeeFrame;
import tableModal.EmployeeTableModal;

public class EmployeeController extends StandartFrameOperation {
	EmployeeDAO DAO;

	public EmployeeController(JFrame frame) {
		super(frame);
		try {
			DAO = new EmployeeDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionSearchButton(String fio, JTable table) {
		try {
			List<Employee> list = null;

			if (fio != null && fio.trim().length() > 0)
				list = DAO.search(fio);
			else
				list = DAO.readAll();

			EmployeeTableModal model = new EmployeeTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(String fio, String phone, String email) {
		if (fio.length() > 0 && phone.length() > 0 && email.length() > 0) {
			Employee entity = new Employee(fio, phone, email);
			try {
				DAO.create(entity);
				refrechView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actionUpdateButton(String fio, String phone, String email, Long id) {
		if (fio.length() > 0 && phone.length() > 0 && email.length() > 0 && id > 0) {
			try {
				Employee entity = new Employee(fio, phone, email);
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
		((EmployeeFrame) getFrame()).refreshView();
	}
	public EmployeeDAO getDAO() {return DAO;}
}
