package entity;

import java.sql.Date;

public class Deliverym extends AbstractEntity {
	private Product product;
	private Provider provider;
	private Date date;
	private int count;
	public Deliverym() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Deliverym(Product product, Provider provider, Date date, int count) {
		super();
		this.product = product;
		this.provider = provider;
		this.date = date;
		this.count = count;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
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
