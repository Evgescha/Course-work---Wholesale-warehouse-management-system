package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import controller.ApplicationController;

public class WarehouseFrame extends JFrame {

	private JTextField textField;
	private JTable table;

	public WarehouseFrame() {
		setTitle("Клиент");
		setBounds(100, 100, 592, 428);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_4 = new JLabel("Введите название продукта для поиска");
		panel.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel.add(textField);
		
		JButton btnNewButton_1 = new JButton("Поиск");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSearchButton();
			}
		});
		panel.add(btnNewButton_1);
		
		
		table = new JTable();
				getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
			}

	
	private void actionSearchButton() {
		ApplicationController.warehouseController.actionSearchButton(textField.getText().trim(), table);
	}

	public void refreshView() {
		ApplicationController.warehouseController.actionSearchButton("", table);
	}
	
	
	
}
