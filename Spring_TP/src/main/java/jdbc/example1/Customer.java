package jdbc.example1;

public class Customer {
	int id;
	String name;
	String address;
	
	public Customer() {}
	
	public Customer(String name, String address) {
		this.id = -1;	//nếu id = -1 thì ko tạo field id của record khi insert vào CSDL
		this.name = name;
		this.address = address;
	}
	
	public Customer(int id, String name, String address) {
		this.id = id;
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
	
	public String getInfo() {
		return this.id + " - " + this.name + " - " + this.address;
	}
	
	public void printInfo() {
		System.out.println(this.getInfo());
	}
}
