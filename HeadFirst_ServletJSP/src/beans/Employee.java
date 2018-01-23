package beans;

public class Employee extends Human {
	int id;
	String dept;
	
	public Employee() {}
	
	public Employee(String name, String address, int id, String dept) {
		super(name, address);
		this.id = id;
		this.dept = dept;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
}
