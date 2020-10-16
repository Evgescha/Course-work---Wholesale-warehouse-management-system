package forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controller.ApplicationController;
import entity.Delivery;
import entity.Product;
import entity.Provider;
import tableModal.DeliveryTableModal;
/**
 * форма отображения поставок
 * @author admin
 *
 */
public class DeliveryFrame extends JFrame {
	private Long id_product, id_provider;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JDateChooser dateChooser;
	JComboBox<Product> comboBox;
	JComboBox<Provider> comboBox_1;

	public DeliveryFrame() {
		setTitle("Поставка");
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

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(3, 3, 3, 3));
		getContentPane().add(panel_1, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("Продукт");

		JLabel lblNewLabel_1 = new JLabel("Поставщик");

		JLabel lblNewLabel_2 = new JLabel("Дата");

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.addKeyListener(new KeyListener() {

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

		JLabel lblNewLabel_2_1 = new JLabel("Количество");

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		comboBox = new JComboBox();

		comboBox_1 = new JComboBox();

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_panel_1.createSequentialGroup().addGap(4).addComponent(lblNewLabel))
										.addGroup(gl_panel_1.createSequentialGroup().addGap(4)
												.addComponent(lblNewLabel_1))
										.addGroup(gl_panel_1.createSequentialGroup().addGap(4)
												.addComponent(lblNewLabel_2)))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblNewLabel_2_1)).addContainerGap(26, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(comboBox, Alignment.LEADING, 0, 130, Short.MAX_VALUE)
										.addComponent(dateChooser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 130,
												Short.MAX_VALUE)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
								.addGap(26))
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(26, Short.MAX_VALUE)))));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblNewLabel).addGap(2)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(14).addComponent(lblNewLabel_2).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNewLabel_2_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(24).addComponent(btnNewButton).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_2).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_3).addContainerGap(42, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actionTableMouseClicked();
			}
		});
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		dateChooser.setDate(new java.util.Date());
	}

	private void actionDeleteButton() {
		if (table.getRowCount() > 0 && table.getSelectedRowCount() > 0) {
			int row = table.getSelectedRow();
			String id = table.getModel().getValueAt(row, 0).toString();
			ApplicationController.deliveryController.actionDeleteButton(Long.parseLong(id));
		}
	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				Delivery delivery = (Delivery) table.getModel().getValueAt(row, DeliveryTableModal.ENTITY_COL);

				textField_1.setText(delivery.getCount() + "");
				Date date = Date.valueOf(delivery.getDate().toString());
				dateChooser.setDate(date);
				

				comboBox.getModel().setSelectedItem(delivery.getProduct());
				comboBox_1.getModel().setSelectedItem(delivery.getProvider());

			} catch (Exception e) {
			}
	}

	private void actionUpdateButton() {

		int row = table.getSelectedRow();
		Long id = Long.parseLong(table.getModel().getValueAt(row, DeliveryTableModal.ID_COL).toString());
		id_product = ((Product) (comboBox.getSelectedItem())).getId();
		id_provider = ((Provider) (comboBox_1.getSelectedItem())).getId();
		ApplicationController.deliveryController.actionUpdateButton(id_product, id_provider,
				new Date(dateChooser.getDate().getTime()), Integer.parseInt(textField_1.getText()), id);
	}

	private void actionCreateButton() {
		id_product = Long.parseLong(comboBox.getSelectedItem().toString().split(":")[0]);
		id_provider = Long.parseLong(comboBox_1.getSelectedItem().toString().split(":")[0]);
		ApplicationController.deliveryController.actionCreateButton(id_product, id_provider,
				new Date(dateChooser.getDate().getTime()), Integer.parseInt(textField_1.getText()));
	}

	private void actionSearchButton() {
		ApplicationController.deliveryController.actionSearchButton(textField.getText().trim(), table);
		refreshComboBoxes();
	}

	public void refreshView() {
		ApplicationController.deliveryController.actionSearchButton("", table);
		refreshComboBoxes();
	}

	public void refreshComboBoxes() {
		try {
			comboBox.setModel(
					new DefaultComboBoxModel(ApplicationController.productController.getDAO().readAll().toArray()));
			comboBox_1.setModel(
					new DefaultComboBoxModel(ApplicationController.providerController.getDAO().readAll().toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
