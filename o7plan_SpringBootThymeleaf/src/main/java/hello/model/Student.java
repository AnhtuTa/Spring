package hello.model;

import java.util.List;

public class Student {
	int id;
	String name;
	String gender;
	String address;
	String faculty;
	List<Song> favoriteSongs;
	List<Integer> songIdList;
	
	
	public Student() {}
	
	public Student(int id, String name, String gender, String address, String faculty, List<Song> favoriteSongs) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.faculty = faculty;
		this.favoriteSongs = favoriteSongs;
	}
	
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public List<Song> getFavoriteSongs() {
		return favoriteSongs;
	}
	public void setFavoriteSongs(List<Song> favoriteSongs) {
		this.favoriteSongs = favoriteSongs;
	}
	public List<Integer> getSongIdList() {
		return songIdList;
	}
	public void setSongIdList(List<Integer> songIdList) {
		this.songIdList = songIdList;
	}
}
