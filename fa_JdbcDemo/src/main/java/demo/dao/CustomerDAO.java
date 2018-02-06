package demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class CustomerDAO {
	public final String DATABASE_NAME = "demo";
    public final String USERNAME = "root";
    public final String PASSWORD = "5555";
    public final String URL_MYSQL = "jdbc:mysql://localhost:3306/"+DATABASE_NAME;
	
	private Connection conn;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	public void connect() {
		if(conn != null) return;
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");	//com.mysql.jdbc.Driver is deprecated
            conn = DriverManager.getConnection(URL_MYSQL, USERNAME, PASSWORD);
            System.out.println("Connect successfull");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error connection! Lỗi kết nối nhé!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void shutdown() {
		try {
			if(pst != null) pst.close();
			if(rs != null) rs.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
