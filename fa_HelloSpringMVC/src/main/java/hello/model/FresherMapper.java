package hello.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FresherMapper implements RowMapper<Fresher> {

	@Override
	public Fresher mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println(rs);
		return new Fresher(rs.getInt("id"), rs.getString("username"), 
			rs.getString("password"), rs.getString("location"), rs.getInt("year"));
	}

}
