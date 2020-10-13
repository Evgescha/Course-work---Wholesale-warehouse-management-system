package entity;

public class Warehousem extends AbstractEntity {
	private Product product;
	private int count;

	public Warehousem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Warehousem(Product product, int count) {
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
