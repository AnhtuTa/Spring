package hello.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="student")
public class Student {
	@Id
	private String id;
	
	private String name;
	private String school;
	private String address;
	private int salary;
	private List<Song> songs;	// list of favorite songs

	public Student() {}

	public Student(String id, String name, String school, String address, int salary) {
		this.id = id;
		this.name = name;
		this.school = school;
		this.address = address;
		this.salary = salary;
	}

	public Student(String name, String school, String address, int salary) {
		this.name = name;
		this.school = school;
		this.address = address;
		this.salary = salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public void printInfo() {
		System.out.println(this.name + " -  " + this.school + " - " + this.address + " - " + this.salary);
		if(this.songs != null) {
			System.out.println("\tFavorite songs:");
			for(Song s : this.songs) {
				System.out.println("\t" + s.getInfo());
			}
		}
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
