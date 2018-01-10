package beans;

public class Student {
	String name;
	String addr;
	String school;
	
	public Student() {}
	
	public Student(String name, String addr, String school) {
		this.name = name;
		this.addr = addr;
		this.school = school;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSchool() {
		return this.school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getInfo() {
		return this.name + " - " + this.addr + " - " + this.school;
	}
}
