package entity;

import java.sql.Date;

public class Order extends AbstractEntity{
	private Employee employee;
	private Client client;
	private Product product;
	private Date date;
	private int count;

	public Order() {
		super();
	}

	public Order(Employee employee, Client client, Product product, Date date, int count) {
		super();
		this.employee = employee;
		this.client = client;
		this.product = product;
		this.date = date;
		this.count = count;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
