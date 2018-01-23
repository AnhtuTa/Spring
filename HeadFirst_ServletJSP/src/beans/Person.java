package beans;

public class Person {
	String name;
	String address;
	Dog dog;
	
	public Person() {}
	
	public Person(String name, String address, Dog dog) {
		super();
		this.name = name;
		this.address = address;
		this.dog = dog;
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

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}
	
}
