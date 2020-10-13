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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductFrame extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	public ProductFrame() {
		setTitle("Товар");
		setBounds(100, 100, 592, 428);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Введите название для поиска");
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

		JLabel lblNewLabel = new JLabel("Название");

		JLabel lblNewLabel_1 = new JLabel("Цена");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent keyEvent) {
				char c = keyEvent.getKeyChar();
				if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
					keyEvent.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent keyEvent) {
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});

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
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addComponent(
												btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addGroup(
												gl_panel_1.createSequentialGroup().addGap(4).addComponent(lblNewLabel))
										.addGroup(gl_panel_1.createSequentialGroup().addGap(4)
												.addComponent(lblNewLabel_1))
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
						.addContainerGap(26, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(16).addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(16).addComponent(btnNewButton).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_2).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_3).addGap(233)));
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
		if (table.getRowCount() > 0 && table.getSelectedRowCount() > 0) {
			int row = table.getSelectedRow();
			String id = table.getModel().getValueAt(row, 0).toString();
			ApplicationController.productController.actionDeleteButton(Long.parseLong(id));
		}
	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				textField_1.setText(table.getModel().getValueAt(row, 1).toString());
				textField_2.setText(table.getModel().getValueAt(row, 2).toString());
			} catch (Exception e) {
			}
	}

	private void actionUpdateButton() {
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		ApplicationController.productController.actionUpdateButton(textField_1.getText().trim(),
				Integer.parseInt(textField_2.getText().trim()), id);
	}

	private void actionCreateButton() {
		ApplicationController.productController.actionCreateButton(textField_1.getText().trim(),
				Integer.parseInt(textField_2.getText().trim()));
	}

	private void actionSearchButton() {
		ApplicationController.productController.actionSearchButton(textField.getText().trim(), table);
	}

	public void refreshView() {
		ApplicationController.productController.actionSearchButton("", table);
	}

}
