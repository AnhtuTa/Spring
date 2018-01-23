package validation_demo.entities;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/*
Các annotation khai báo trước các field sẽ định nghĩa ràng buộc
cho các field đó.

Mặc định các message lỗi sẽ được lấy từ file .properties, nếu 
không tìm thấy thì nó sẽ thấy theo các message khai báo bên
cạnh annotation, nếu không tìm thấy cả 2 chỗ trên thì nó 
sẽ thấy message mặc định của hibernate-validator và validation-api.
 */
public class User {
	@NotNull(message = "Id must be not null")
	private Integer id;
	
	@NotBlank
	@Length(min = 5, max = 10)
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	private Date dateOfBirth;
	
	//@Phone là do mình tự định nghĩa
	@Phone(message = "Phone Number is invalid")
	private String phoneNumber;

	// getter/setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
