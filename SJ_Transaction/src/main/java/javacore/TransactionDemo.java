package javacore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDemo {
	Connection conn;
	// Thực hiện chuyển 50.000.000đ từ A sang B mà có sử dụng transaction:
	// A bị trừ 50.000.000đ còn B được cộng thêm 50.000.000đ nên sẽ 
	// update amount của A thành 40.000.000đ còn B là 140.000.000đ
	// Chú ý rằng cột 'amout' trong database có giá trị tối đa < 100 triệu
	// nên update như vậy sẽ xảy ra lỗi, nếu dùng transaction thì sẽ rollback
	// nếu ko thì tiền của người gửi sẽ bị trừ còn tiền của người nhận ko đc update
	
	public void connect() {
		if(conn != null) return;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "5555");
		    System.out.println("Connect success to database: `demo`");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * conn.setAutoCommit(false): dữ liệu sẽ chỉ update vào database khi gọi lệnh commit()
	 * conn.rollback(): lấy lại dữ liệu ban đầu.
	 */
	public void updateWithTran() throws SQLException {
		Statement st = null;
		try {
			st = conn.createStatement();
			conn.setAutoCommit(false);
			
			// anhtu chuyển cho huy 50 củ
			st.executeUpdate("UPDATE bank_account SET amount = 40000000 WHERE name = 'anhtu'");
			st.executeUpdate("UPDATE bank_account SET amount = 140000000 WHERE name = 'huy'");
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error occur! Transaction rollback!");
			conn.rollback();
		}
		st.close();
	}
	
	public void updateWithoutTran() throws SQLException {
		Statement st = null;
		st = conn.createStatement();
		conn.setAutoCommit(true);	//commit ngay khi thực hiện 1 lệnh SQL thành công
		
		// anhtu2 chuyển cho huy2 50 củ
		st.executeUpdate("UPDATE bank_account SET amount = 40000000 WHERE name = 'anhtu2'");	//thành công
		st.executeUpdate("UPDATE bank_account SET amount = 140000000 WHERE name = 'huy2'");		//lỗi!
		st.close();
	}
	
	public void disconnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		TransactionDemo td = new TransactionDemo();
		td.connect();
		//================ code demo in here =============
		td.updateWithTran();
		td.updateWithoutTran();
		//================================================
		td.disconnect();
	}
}
/*
Giá trị trong CSDL:
id	name	amount
--------------------
3	anhtu2	40000000
4	huy2	90000000
 */

