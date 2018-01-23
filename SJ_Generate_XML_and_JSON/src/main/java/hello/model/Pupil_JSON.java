package hello.model;

// Class này có các thuộc tính giống hệt bên Pupil.java
// nhưng ko có config gì, để có thể return JSON data
public class Pupil_JSON {
	private int id;
	private String name;
	private String address;
	
	public Pupil_JSON() {}
	
	public Pupil_JSON(int id, String name, String address) {
		super();
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
}
