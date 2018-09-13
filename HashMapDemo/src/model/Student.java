package model;

public class Student {
	int id;
	String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			if( ((Student) obj).id == this.id && ((Student) obj).name.equals(this.name) ) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
}
