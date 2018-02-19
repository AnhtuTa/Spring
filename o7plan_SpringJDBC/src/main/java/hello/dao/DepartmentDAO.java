package hello.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository(value = "dept_dao_wtf")
public class DepartmentDAO extends JdbcDaoSupport {
	@Autowired
	public DepartmentDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<String> getDeptNames() {
		String sql = "Select d.dept_name from department d ";
		// queryForList(String sql, Class<T> elementType)
		List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
		return list;
	}

	public List<String> getDeptNames(String searchName) {
		String sql = "Select d.dept_name from department d " + " Where d.dept_name like ? ";
		// queryForList(String sql, Class<T> elementType, Object... args)
		List<String> list = this.getJdbcTemplate().queryForList(sql, String.class, "%" + searchName + "%");
		return list;
	}

	// Map<String columnName, Object value>
	public List<Map<String, Object>> queryForList_ListMap() {
		String sql = "Select d.dept_no, d.dept_name from department d ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		return list;
	}
}
