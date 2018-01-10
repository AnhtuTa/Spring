package beans;

public class Book {
	String name;
	float price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + " - " + this.price;
	}
}
