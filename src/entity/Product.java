package entity;

public class Product extends AbstractEntity{
	private String name;
	private int price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Product() {
		super();
	}
	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	
}
