package hello.form;

// Lớp PersonForm đại diện cho dữ liệu của FORM khi bạn tạo mới 
// một Person trên trang add-person

// Class này giống hệt class Person!
public class PersonForm {
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
