package javacore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.User;

public class BatchDemo {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	Connection conn;
	
	private final int BATCH_SIZE = 100;
	
	public void readProperties() {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(getClass().getClassLoader().getResource("database.properties").getFile());
			prop.load(is);
			this.driverClassName = prop.getProperty("jdbc.driverClassName");
			this.url = prop.getProperty("jdbc.url");
			this.username = prop.getProperty("jdbc.username");
			this.password = prop.getProperty("jdbc.password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() {
		if(conn != null) return;
        try {
        	this.readProperties();
            Class.forName(this.driverClassName);	//com.mysql.jdbc.Driver is deprecated
            conn = DriverManager.getConnection(this.url, this.username, this.password);
            System.out.println("Connect successfull");
        } catch (SQLException ex) {
            Logger.getLogger(BatchDemo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error connection! Lỗi kết nối nhé!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BatchDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	/*
	    - connection.setAutoCommit(false): tắt tự động commit, 
	    chúng ta sẽ thực hiện commit bằng tay.
		- statement.addBatch(sql): thêm câu SQL vào batch
		- statement.executeBatch(): xử lý batch, kết nối database và thực 
		hiện tất cả các câu SQL trong batch (Sau khi thực hiện xong, bên trong batch sẽ trống)
		- Cứ mỗi lần số lượng câu sql trong batch bằng batch size sẽ thực hiện executeBatch
	 */
	public void batchInsert() throws SQLException {
		Statement st = conn.createStatement();
		conn.setAutoCommit(false);
		
		List<User> uList = new ArrayList<>();
		for(int i=1; i <= 10000; i++) {
			uList.add(new User("anhtu" + i, "Hanoi" + i));
		}
		
		System.out.println("Begin inserting using batch...");
		long startTime = System.currentTimeMillis();
		Loading ld = new Loading("executing batch, please wait", 300, 70);
		ld.start();
		for(int i = 0; i < uList.size(); i++) {
			String sql = "INSERT INTO user(name, address) VALUES('" + uList.get(i).getName() + "', '" + uList.get(i).getAddress() + "')";
			st.addBatch(sql);
			if(i % BATCH_SIZE == 0) {
				st.executeBatch();
			}
		}
		st.executeBatch();
		conn.commit();
		
		long endTime = System.currentTimeMillis();
		ld.stopLoading();
		System.out.println("Finish inserting using batch!");
		System.out.println("Total executing time = " + (endTime - startTime) + " (ms)");
		
		if(st != null) st.close();
	}
	
	/*
	 * insert without batch
	 */
	public void normalInset() throws SQLException {
		Statement st = conn.createStatement();
		conn.setAutoCommit(true);
		
		List<User> uList = new ArrayList<>();
		for(int i=1; i <= 10000; i++) {
			uList.add(new User("anhtu" + i, "Hanoi" + i));
		}
		
		System.out.println("Begin inserting without batch...");
		long startTime = System.currentTimeMillis();
		Loading ld = new Loading("executing without batch, please wait", 330, 70);
		ld.start();
		for(int i = 0; i < uList.size(); i++) {
			String sql = "INSERT INTO user(name, address) VALUES('" + uList.get(i).getName() + "', '" + uList.get(i).getAddress() + "')";
			st.executeUpdate(sql);
		}
		
		long endTime = System.currentTimeMillis();
		ld.stopLoading();
		System.out.println("Finish inserting without batch!");
		System.out.println("Total executing time = " + (endTime - startTime) + " (ms)");
		
		if(st != null) st.close();
	}
	
	public static void main(String[] args) throws SQLException {
		BatchDemo bd = new BatchDemo();
		bd.connect();
		//bd.batchInsert();
		bd.normalInset();
	}
}
