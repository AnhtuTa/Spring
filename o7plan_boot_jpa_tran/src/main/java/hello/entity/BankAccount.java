package hello.entity;

import javax.persistence.*;

/*
Trong JPA (Hoặc Hibernate), Entity là một class đại diện (tương ứng)
cho một bảng trong cơ sở dữ liệu. Các trường (field) trong lớp này
sẽ tương ứng với các cột trong bảng.

Chúng ta sẽ tạo lớp BankAccount để đại diện cho bảng BANK_ACCOUNT
trong database. Các JPA Annotation sẽ được sử dụng để chú thích
trên các trường (field) để mô tả cách ánh xạ (mapping) giữa các
trường và các cột của bảng. Các ánh xạ này là 1-1, mỗi trường
tương ứng với 1 cột trong bảng.
 */
@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
	private Long id;

    @Column(name = "full_name", length = 128, nullable = false)
	private String fullName;

    @Column(name = "balance", nullable = false)
	private double balance;

    public BankAccount() {}

	public BankAccount(Long id, String fullName, double balance) {
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
