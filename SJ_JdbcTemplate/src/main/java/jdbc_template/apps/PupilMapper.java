package jdbc_template.apps;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jdbc_template.entities.Pupil;

public class PupilMapper implements RowMapper<Pupil> {

	public Pupil mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pupil p = new Pupil();
		p.setId(rs.getInt("id"));
		p.setName(rs.getString("name"));
		p.setAddress(rs.getString("addr"));
		p.setCountryId(rs.getInt("country"));
		System.out.println("[PupilMapper] rowNum = " + rowNum);
		return p;
	}

}
