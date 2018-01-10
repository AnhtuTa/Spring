package beans;

public class Food {
	String name;
	float price;
	
	public void initFood() {
		System.out.println("This is init method of Food class");
	}
	public void destroyFood() {
		System.out.println("This is destroy method of Food class");
	}
	
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
	
	public String getInfo() {
		return this.name + " - " + this.price;
	}
}
