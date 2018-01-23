package beans;

public class Dog {
	String name;
	Toy [] toys;
	
	public Dog() {}
	
	public Dog(String name, Toy[] toys) {
		super();
		this.name = name;
		this.toys = toys;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Toy[] getToys() {
		return toys;
	}

	public void setToys(Toy[] toys) {
		this.toys = toys;
	}
	
}
