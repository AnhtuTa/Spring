package security_demo.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import security_demo.dao.SpitterDetailsDAO;
import security_demo.model.Spitter;

@Service
@Transactional
public class SpitterDetailsDaoImpl extends JdbcDaoSupport implements SpitterDetailsDAO {

	@Autowired
	public SpitterDetailsDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	@Override
	public Spitter findSpitter(String username) {
		String sql = "SELECT username, password, enabled " +
					 "FROM spitter WHERE username=?";
		Spitter.SpitterMapper mapper = new Spitter.SpitterMapper();
		try {
			return this.getJdbcTemplate()
					   .queryForObject(sql, new Object[] {username}, mapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<String> getSpitterRoles(String username) {
		String sql = "SELECT role FROM spitter_roles " +
					 "WHERE username = ?";
		return this.getJdbcTemplate()
				   .queryForList(sql, new Object[] { username }, String.class);
	}

}
