package hello.model;

/*
 * Trong Spring, một lớp đại diện cho dữ liệu của 1 bản ghi, 
 * có được từ một câu lệnh truy vấn (Query statement) 
 * được gọi là lớp model. 
 * Lớp BankAccountInfo là một lớp model.
 */
public class BankAccountInfo {
	private Long id;
	private String fullName;
	private double balance;

	public BankAccountInfo(Long id, String fullName, double balance) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
