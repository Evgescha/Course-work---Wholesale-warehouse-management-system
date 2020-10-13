package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.ProviderDAO;
import defaultOperation.StandartFrameOperation;
import entity.Provider;
import forms.ProviderFrame;
import tableModal.ProviderTableModal;

public class ProviderController extends StandartFrameOperation {

	ProviderDAO providerDAO;

	public ProviderController(JFrame frame) {
		super(frame);
		try {
			providerDAO = new ProviderDAO();
		} catch (Exception e) {e.printStackTrace();}
	}

	public void actionSearchButton(String name, JTable table) {
		try {
			List<Provider> list = null;

			if (name != null && name.trim().length() > 0)
				list = providerDAO.search(name);
			else
				list = providerDAO.readAll();

			ProviderTableModal model = new ProviderTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(String name, String adres, String phone, String email) {
		if (name.length() > 0 && adres.length() > 0 && phone.length() > 0 && email.length() > 0) {
			Provider provider = new Provider(name, adres, phone, email);
			try {
				providerDAO.create(provider);
				((ProviderFrame) getFrame()).refreshView();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
