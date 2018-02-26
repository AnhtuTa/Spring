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
 * Nó tự đc Spring bean tạo ra do có 1 interface UserInfoDAO,
 * Mà interface đó cũng tự động tạo ra, khi tạo ra 1 instance
 * của 1 interface thì Spring sẽ tìm 1 thằng mà implement thằng
 * interface đó để tạo bean
 */
@Service
@Transactional
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO {

	@Autowired
	public UserInfoDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	// Username trong DB có tính duy nhất, nên luôn chỉ get đc 1 record
	@Override
	public UserInfo findUserInfo(String userName) {
		System.out.println("This is UserInfoDAOImpl.findUserInfo()");
		String sql = "Select u.Username,u.Password "
				+ " from Users u where u.Username = ? ";
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
		System.out.println("This is UserInfoDAOImpl.getUserRoles()");
		String sql = "Select r.User_Role "
				+ " from User_Roles r where r.Username = ? ";
		List<String> roles = this.getJdbcTemplate()
				.queryForList(sql, new Object[] { userName }, String.class);

		return roles;
	}

}