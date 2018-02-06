package hello.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import hello.model.Fresher;
import hello.model.FresherMapper;

@Component
public class FresherDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Fresher getFresherByUserAndPass(String user, String pass) {
		// Chú ý: thiết lập cột `username` trong database có kiểu Unique, do đó
		// luôn chỉ query đc 1 record
		String sql = "select * from fresher where username = ? and password = ?";
		List<Fresher> fList = jdbcTemplate.query(sql, new FresherMapper(), user, pass);
		if(fList.size() == 0) return null;
		return fList.get(0);
	}
	
	public boolean insertFresher(Fresher f) {
		try {
			String sql = "insert into fresher(username, password, location, year) values(?, ?, ?, ?)";
			int row = jdbcTemplate.update(sql, f.getUsername(), f.getPassword(), f.getLocation(), f.getYear());
			System.out.println("[FresherDAO] row = " + row);
			return row > 0;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static void main(String[] args) {
		
	}
}
