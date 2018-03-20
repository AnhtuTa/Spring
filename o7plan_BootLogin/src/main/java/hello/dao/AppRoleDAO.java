package hello.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AppRoleDAO extends JdbcDaoSupport {
	@Autowired
	public AppRoleDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public List<String> getRoleNames(Long userId) {
		String sql = "SELECT r.role_name " +
					 "FROM user_role ur, app_role r "  +
					 "WHERE ur.role_id = r.role_id " +
					 "AND ur.user_id = ?";
		return this.getJdbcTemplate()
			.queryForList(sql, new Object[] {userId}, String.class);
	}
}
