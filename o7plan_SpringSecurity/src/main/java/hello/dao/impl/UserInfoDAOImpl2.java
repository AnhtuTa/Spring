package hello.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.dao.UserInfoDAO;
import hello.mapper.UserInfoMapper;
import hello.model.UserInfo;

/*
 * Thằng này được dùng ở đâu???
 */
@Service
@Transactional
public class UserInfoDAOImpl2 extends JdbcDaoSupport implements UserInfoDAO {

	@Autowired
	public UserInfoDAOImpl2(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	// Username trong DB có tính duy nhất, nên luôn chỉ get đc 1 record
	@Override
	public UserInfo findUserInfo(String userName) {
		System.out.println("This is UserInfoDAOImpl2.findUserInfo()");
		String sql = "Select username, password "
				+ " from spitter where username = ? ";
		Object[] params = new Object[] { userName };
		UserInfoMapper mapper = new UserInfoMapper();
		try {
			UserInfo userInfo = this.getJdbcTemplate()
					.queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	// Mỗi username có thể có nhiều role, do đó có thể có nhiểu record
	@Override
	public List<String> getUserRoles(String userName) {
		System.out.println("This is UserInfoDAOImpl2.getUserRoles()");
		String sql = "Select role "
				+ " from spitter_roles where username = ? ";
		List<String> roles = this.getJdbcTemplate()
				.queryForList(sql, new Object[] { userName }, String.class);

		return roles;
	}

}