package autowire.annotation.by_constructor;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {
	private String name;
	private int age;
	private Address address;
	
	// Để auto wired byConstructor ta khai báo @Autowired ở trước method Constructor:
	// Chú ý: nếu ko có @Autowired thì có thể ứng dụng này vẫn chạy đúng,
	// vì Spring nó tự detect đc!
	@Autowired(required=true)
	public Person(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void print() {
		System.out.println("Person: " + this.name + " Age: " + this.age + " Address: "
				+ (this.address == null ? "null" : this.address.toString()));
	}
}
