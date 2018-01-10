package annotation_config;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {
	String name;
	@Autowired
	private Country country;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public String getInfo() {
		if(this.country.getName() != null) { 
			return this.name + " - " + this.country.getName();
		} else {
			return this.name + " - " + "unknown country!!!";
		}
	}
}
