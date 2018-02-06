package demo.model;

public class Customer {
	private int id;
	String name;
	String address;
	
	public Customer() {}
	
	public Customer(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return name + " - " + address;
	}
}
