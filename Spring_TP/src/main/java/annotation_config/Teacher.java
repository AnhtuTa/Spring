package annotation_config;

import org.springframework.beans.factory.annotation.Required;

public class Teacher {
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	@Required
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	@Required
	public void setAge(int age) {
		this.age = age;
	}
	public String getInfo() {
		return this.name + " - " + this.age;
	}
}
