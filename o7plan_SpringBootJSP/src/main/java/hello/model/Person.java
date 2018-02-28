package hello.model;

import java.util.List;

public class Person {
	private String firstName;
	private String lastName;
	List<String> favorites;

	public Person() {

	}

	public Person(String firstName, String lastName, List<String> favorites) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.favorites = favorites;
	}

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

	public List<String> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<String> favorites) {
		this.favorites = favorites;
	}

}
