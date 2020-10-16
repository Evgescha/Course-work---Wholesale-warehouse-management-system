package forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import controller.ApplicationController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * форма отображения поставщиков
 * @author admin
 *
 */
public class ProviderFrame extends JFrame {
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public ProviderFrame() {
		setTitle("Поставщик");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 592, 428);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Введите имя для поиска");
		panel.add(lblNewLabel_4);

		textField_4 = new JTextField();
		panel.add(textField_4);
		textField_4.setColumns(10);

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

		JLabel lblNewLabel = new JLabel("Имя");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Адрес");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Телефон");

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Почта");

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		JButton btnNewButton = new JButton("Добавить");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCreateButton();
			}
		});

		JButton btnNewButton_2 = new JButton("Обновить");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionUpdateButton();
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
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(4).addComponent(lblNewLabel))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(4).addComponent(lblNewLabel_1))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(4).addComponent(lblNewLabel_2))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(4).addComponent(lblNewLabel_3))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnNewButton_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 130,
										Short.MAX_VALUE))
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(20, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(16).addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(16).addComponent(lblNewLabel_2).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(16).addComponent(lblNewLabel_3).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(22).addComponent(btnNewButton).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_2).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_3).addGap(5)));
		panel_1.setLayout(gl_panel_1);

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				actionTableMouseClicked();
			}
		});
		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

	private void actionDeleteButton() {
		if(table.getRowCount()>0 && table.getSelectedRowCount()>0) {
			int row = table.getSelectedRow();
			String id=table.getModel().getValueAt(row, 0).toString();
			ApplicationController.providerController.actionDeleteButton(Long.parseLong(id));
		}
	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				textField.setText(table.getModel().getValueAt(row, 1).toString());
				textField_1.setText(table.getModel().getValueAt(row, 2).toString());
				textField_2.setText(table.getModel().getValueAt(row, 3).toString());
				textField_3.setText(table.getModel().getValueAt(row, 4).toString());
			} catch (Exception e) {
			}

	}

	private void actionUpdateButton() {
		int column = 0;
		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, column).toString());
		ApplicationController.providerController.actionUpdateButton(textField.getText().trim(),
				textField_1.getText().trim(), textField_2.getText().trim(), textField_3.getText().trim(), id);

	}

	private void actionCreateButton() {
		ApplicationController.providerController.actionCreateButton(textField.getText().trim(),
				textField_1.getText().trim(), textField_2.getText().trim(), textField_3.getText().trim());
	}

	private void actionSearchButton() {
		// textField_4
		ApplicationController.providerController.actionSearchButton(textField_4.getText().trim(), table);
	}

	public void refreshView() {
		ApplicationController.providerController.actionSearchButton("", table);
	}
}
