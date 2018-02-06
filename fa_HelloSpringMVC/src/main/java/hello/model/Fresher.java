package hello.model;

public class Fresher {
	private int id;
	private String username;
	private String password;
	private String location;
	private int year;
	
	public Fresher(String username, String password, String location, int year) {
		super();
		this.username = username;
		this.password = password;
		this.location = location;
		this.year = year;
	}
	
	
	public Fresher(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Fresher() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return username + " - " + location;
	}
}
