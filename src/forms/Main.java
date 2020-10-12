package forms;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

import controller.ApplicationController;

public class Main extends JFrame{	
	
	private JTable table;

	public Main() {
		initialize();	
	}

	private void initialize() {
		
		setTitle("Главная");
		setBounds(100, 100, 480, 219);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Заказы");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		actionOrderButton();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 11, 195, 25);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Товары");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionProductButton();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 45, 195, 25);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Поставщики");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionProviderButton();
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_2.setBounds(10, 79, 195, 25);
		getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Клиенты");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionClientButton();
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_3.setBounds(10, 113, 195, 25);
		getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Сотрудники");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionEmployeeButton();
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_4.setBounds(10, 147, 195, 25);
		getContentPane().add(btnNewButton_4);
		
		table = new JTable();
		table.setBounds(246, 35, 208, 137);
		getContentPane().add(table);
	}

	
	// Buttons actions
	private void actionOrderButton() {
		ApplicationController.mainController.actionOrderButton();
	}

	private void actionProductButton() {
		ApplicationController.mainController.actionProductButton();
	}

	private void actionProviderButton() {
		ApplicationController.mainController.actionProviderButton();
	}

	private void actionClientButton() {
		ApplicationController.mainController.actionClientButton();
	}

	private void actionEmployeeButton() {
		ApplicationController.mainController.actionEmployeeButton();
	}
}
