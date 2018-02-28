package hello.model;

/*
Trong khi một lớp Entity đại diện cho dữ liệu của một bản ghi (record)
của một bảng, thì một lớp Model đại diện cho dữ liệu của 1 bản ghi
của một câu lệnh truy vấn (Join từ một hoặc nhiều bảng). Bạn sử dụng
lớp Model khi bạn quan tâm tới một vài cột của một hoặc nhiều bảng.
 */
public class BankAccountInfo {
    private Long id;
    private String fullName;
    private double balance;

    public BankAccountInfo() {

    }

    // Sử dụng trong câu truy vấn của JPA
    public BankAccountInfo(Long id, String fullName, double balance) {
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
