package spring;

public class BankAccount {
	int id;
	String name;
	int amount;
	
	public BankAccount() {}
	
	public BankAccount(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}

	public BankAccount(int id, String name, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return this.name + " - " + this.amount;
	}
}
