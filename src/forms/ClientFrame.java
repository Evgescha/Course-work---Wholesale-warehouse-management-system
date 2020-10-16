package forms;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ApplicationController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * форма отображения клиентов
 * @author admin
 *
 */
public class ClientFrame extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	public ClientFrame() {
		setTitle("Клиент");
		setBounds(100, 100, 592, 428);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_4 = new JLabel("Введите ФИО для поиска");
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(3, 3, 3, 3));
		getContentPane().add(panel_1, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("ФИО");
		
		JLabel lblNewLabel_1 = new JLabel("Телефон");
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Обновить");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionUpdateButton();
			}
		});
		
		JButton btnNewButton = new JButton("Добавить");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCreateButton();
			}
		});
		
		JButton btnNewButton_3 = new JButton("Удалить");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDeleteButton();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(4)
								.addComponent(lblNewLabel))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(4)
								.addComponent(lblNewLabel_1))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(4)
								.addComponent(lblNewLabel_2))
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
							.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
							.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_3)
					.addGap(233))
		);
		panel_1.setLayout(gl_panel_1);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actionTableMouseClicked();
			}
		});
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		
	}
	
	

	private void actionDeleteButton() {
		if(table.getRowCount()>0 && table.getSelectedRowCount()>0) {
			int row = table.getSelectedRow();
			String id=table.getModel().getValueAt(row, 0).toString();
			ApplicationController.clientController.actionDeleteButton(Long.parseLong(id));
		}
	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				textField_1.setText(table.getModel().getValueAt(row, 1).toString());
				textField_2.setText(table.getModel().getValueAt(row, 2).toString());
				textField_3.setText(table.getModel().getValueAt(row, 3).toString());
			} catch (Exception e) {			}
	}

	private void actionUpdateButton() {
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		ApplicationController.clientController.actionUpdateButton(textField_1.getText().trim(), textField_2.getText().trim(), textField_3.getText().trim(), id);
	}

	private void actionCreateButton() {
		ApplicationController.clientController.actionCreateButton(textField_1.getText().trim(), textField_2.getText().trim(), textField_3.getText().trim());
	}

	private void actionSearchButton() {
		ApplicationController.clientController.actionSearchButton(textField.getText().trim(), table);
	}

	public void refreshView() {
		ApplicationController.clientController.actionSearchButton("", table);
	}
	
}
