package forms;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Main {
	
	public static Main main;

	private JFrame frame;
	private Order order;
	private Product product;
	private Provider provider;
	private Client client;
	private Employee employee;
	
	
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main = new Main();
					main.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
		order = new Order();
		product = new Product();
		provider = new Provider();
		client = new Client();
		employee = new Employee();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Главная");
		frame.setBounds(100, 100, 480, 219);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Заказы");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.actionOrderButton();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 11, 195, 25);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Товары");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.actionProductButton();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 45, 195, 25);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Поставщики");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.actionProviderButton();
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_2.setBounds(10, 79, 195, 25);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Клиенты");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.actionClientButton();
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_3.setBounds(10, 113, 195, 25);
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Сотрудники");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.actionEmployeeButton();
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_4.setBounds(10, 147, 195, 25);
		frame.getContentPane().add(btnNewButton_4);
		
		table = new JTable();
		table.setBounds(246, 35, 208, 137);
		frame.getContentPane().add(table);
	}

	// Showing forms
	private void showOrder() {
		order.setVisible(true);
	}

	private void showProduct() {
		product.setVisible(true);
	}

	private void showProvider() {
		provider.setVisible(true);
	}

	private void showClient() {
		client.setVisible(true);
	}

	private void showEmployee() {
		employee.setVisible(true);
	}

	// Hiding forms
	private void hideOrder() {
		order.setVisible(false);
	}

	private void hideProduct() {
		product.setVisible(false);
	}

	private void hideProvider() {
		provider.setVisible(false);
	}

	private void hideClient() {
		client.setVisible(false);
	}

	private void hideEmployee() {
		employee.setVisible(false);
	}

	// Buttons actions

	private void actionOrderButton() {
		if (order.isVisible())
			hideOrder();
		else
			showOrder();
	}

	private void actionProductButton() {
		if (product.isVisible())
			hideProduct();
		else
			showProduct();
	}

	private void actionProviderButton() {
		if (provider.isVisible())
			hideProvider();
		else
			showProvider();
	}

	private void actionClientButton() {
		if (client.isVisible())
			hideClient();
		else
			showClient();
	}

	private void actionEmployeeButton() {
		if (employee.isVisible())
			hideEmployee();
		else
			showEmployee();
	}
}
