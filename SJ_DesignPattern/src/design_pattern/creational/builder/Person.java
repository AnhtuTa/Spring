package design_pattern.creational.builder;

import java.util.List;

/*
 * Builder Pattern là một mẫu thiết kế tạo dựng (Creation Pattern). 
 * Builder Pattern được xây dựng để khắc phục một số nhược điểm của 
 * Factory Pattern và Abstract Factory Pattern.
 * 
 * Builder pattern được sử dụng để xử lý các vấn đ�? còn tồn tại với
 * Factory Pattern và Abstract Factory Pattern khi mà Object có nhi�?u thuộc tính.
 * 
 * Có ba vấn đ�? chính với  Factory Pattern và Abstract Factory Pattern 
 * khi Object có nhi�?u thuộc tính:
 * - Quá nhi�?u tham số phải truy�?n vào từ phía client tới Factory Class
 * - Một số tham số có thể là tùy ch�?n nhưng trong Factory Pattern, chúng ta 
 *   phải gửi tất cả tham số, với tham số tùy ch�?n nếu ko nhập gì thì sẽ truy�?n là null.
 * - Nếu một Object có quá nhi�?u thuộc tính thì việc tạo sẽ phức tạp.
 * 
 * Chúng ta có thể sử lý những vấn đ�? này với một số lượng lớn các tham số bằng
 * việc cung cấp một hàm khởi tạo với những tham số bắt buộc và các method 
 * getter/setter để cài đặt các tham số tùy ch�?n. Vấn đ�? với hướng tiếp cận 
 * này là trạng thái của Object sẽ không nhất quán cho tới khi tất cả các 
 * thuộc tính được cài đặt một cách rõ ràng
 * 
 * Builder pattern xử lý các vấn đ�? này bằng việc cung cấp một cách 
 * xây dựng đối tượng từng bước một và cung cấp một method để trả 
 * v�? đối tượng cuối cùng.
 * 
 * Một số ví dụ sử dụng Builder Pattern trong JDK:
 * java.lang.StringBuilder#append() (unsynchronized)
 * java.lang.StringBuffer#append() (synchronized)
 * 
 * ======
 * 
 * Bản chất của Builder Pattern tức là Xây một cái mới từ những cái có sẵn.
 * Ví dụ khi xây dựng ứng dụng quản lý nhà hàng. Ở th�?i điểm hiện tại 
 * ngư�?i ta mới nghĩ ra chừng ấy thực đơn. Bây gi�? ngư�?i ta muốn thêm 
 * thực đơn mới thì làm thế nào. Ở đây có thể dùng builder pattern.
 */
public class Person {
	private String name;
	private int age;
	private List<String> languages;

	/*
	 * Hàm khởi tạo là private nên chỉ có một cách duy nhất để lấy một đối tượng
	 * Person là thông qua class PersonBuilder.
	 */
	private Person(PersonBuilder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.languages = builder.languages;
	}

	@Override
	public String toString() {
		String kq = "The person info: " + "name is " + name + "; " + //
				"age is " + age + "; " + "languages are: ";
		for(String language : languages) {
			kq += language + ", ";
		}
		
		kq = kq.substring(0, kq.length()-2);
		return kq;
	}

	
	
	/*
	 * Thông thư�?ng, những trư�?ng hợp đơn giản ngư�?i ta sẽ gộp luôn Builder và
	 * ConcreteBuilder thành static nested class bên trong class Person
	 * 
	 * Inner class này phải có đủ các field giống như của Person
	 */
	public static class PersonBuilder {
		private String name;
		private int age;
		private List<String> languages;

		public PersonBuilder name(String name) {
			this.name = name;
			return this;
		}

		public PersonBuilder age(int age) {
			this.age = age;
			return this;
		}

		public PersonBuilder languages(List<String> languages) {
			this.languages = languages;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}
}
