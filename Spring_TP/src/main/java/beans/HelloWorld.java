package beans;

public class HelloWorld {
	private String message;
	private String message2;
	
	public void getMessage() {
		System.out.println("Your message: " + this.message);
	}
	
	public void setMessage(String msg) {
		this.message = msg;
	}
	
	public void getMessage2() {
		System.out.println("Your message2: " + this.message2);
	}
	
	public void setMessage2(String msg) {
		this.message2 = msg;
	}
	
}
