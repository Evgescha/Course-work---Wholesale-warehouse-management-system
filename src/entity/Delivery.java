package entity;

import java.sql.Date;

public class Delivery extends AbstractEntity{
	private Provider provider;
	private Product product;
	private Date date;
	private int count;
	public Delivery(Provider provider, Product product, Date date, int count) {
		super();
		this.provider = provider;
		this.product = product;
		this.date = date;
		this.count = count;
	}
	public Delivery() {
		super();
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
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
