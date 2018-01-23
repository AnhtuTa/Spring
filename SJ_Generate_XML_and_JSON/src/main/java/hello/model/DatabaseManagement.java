package hello.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManagement {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	protected Connection conn;
	protected PreparedStatement pst = null;
	protected ResultSet rs = null;
	
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
	
	public Connection connect() {
		if(conn != null) return conn;
        try {
        	this.readProperties();
            Class.forName(this.driverClassName);	//com.mysql.jdbc.Driver is deprecated
            conn = DriverManager.getConnection(this.url, this.username, this.password);
            System.out.println("Connect successfull");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagement.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error connection! Lỗi kết nối nhé!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
	
	public void disconnect() {
		try {
			if(pst != null) pst.close();
			if(rs != null) rs.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DatabaseManagement dm = new DatabaseManagement();
		dm.readProperties();
		System.out.println(dm.username);
		dm.connect();
	}
}
