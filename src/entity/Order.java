package entity;

import java.sql.Date;

public class Order extends AbstractEntity{
	private Employee employee;
	private Client client;
	private Product product;
	private Date date;
	private int summ;

	public Order() {
		super();
	}

	public Order(Employee employee, Client client, Product product, Date date, int summ) {
		super();
		this.employee = employee;
		this.client = client;
		this.product = product;
		this.date = date;
		this.summ = summ;
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

	public int getSumm() {
		return summ;
	}

	public void setSumm(int summ) {
		this.summ = summ;
	}

}
