package entity;

public class Provider extends AbstractEntity{
	private String name;
	private String adres;
	private String phone;
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Provider() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Provider(String name, String adres, String phone, String email) {
		super();
		this.name = name;
		this.adres = adres;
		this.phone = phone;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Provider [id="+id+", name=" + name + ", adres=" + adres + ", phone=" + phone + ", email=" + email + "]";
	}
	
	
}
