package hello.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DataDAO extends JdbcDaoSupport {
	@Autowired
	public DataDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public List<String> getAllPublisherNames() {
		String sql = "SELECT name FROM publishers";
		return this.getJdbcTemplate().queryForList(sql, String.class);
	}
	
	public List<String> getAllAdvertiserNames() {
		String sql = "SELECT name FROM advertisers";
		return this.getJdbcTemplate().queryForList(sql, String.class);
	}
	
	
}
