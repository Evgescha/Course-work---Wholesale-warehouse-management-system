package entity;

public class Warehouse extends AbstractEntity{
	private Product product;
	private int count;
	public Warehouse() {
		super();
	}
	public Warehouse(Product product, int count) {
		super();
		this.product = product;
		this.count = count;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
