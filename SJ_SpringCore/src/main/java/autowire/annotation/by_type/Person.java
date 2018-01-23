package autowire.annotation.by_type;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {
	private String name;
	private int age;
	
	// để auto wired byType ta khai báo @Autowired ở trước phần
	// khai báo thuộc tính hoặc trước method setter
	// required = true, nếu spring container không tìm thấy bean address để inject vào thì nó sẽ báo lỗi
	// required = false, nếu Spring container không tìm thấy bean address để inject vào thì nó sẽ inject null
	@Autowired(required = false)
	private Address address;

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
