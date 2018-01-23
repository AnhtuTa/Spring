package jdbc_template.entities;

public class Pupil {
	private int id;
	private String name;
	private String address;
	private int countryId;

	public Pupil() {
	}

	public Pupil(String name, String address, int countryId) {
		super();
		this.name = name;
		this.address = address;
		this.countryId = countryId;
	}

	public Pupil(int id, String name, String address, int countryId) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.countryId = countryId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return this.id + " - " + this.name + " - " + this.address;
	}
}
