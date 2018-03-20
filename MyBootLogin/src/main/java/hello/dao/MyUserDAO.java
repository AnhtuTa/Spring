package hello.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hello.model.MyUser;
import hello.model.MyUserMapper;

/*
 * Các lớp DAO (Data Access Object) là các lớp sử dụng để truy cập 
 * vào cơ sở dữ liệu, chẳng hạn Query, Insert, Update, Delete. Các
 * lớp DAO thường được chú thích bởi @Repository để nói với Spring
 * rằng hãy quản lý chúng như các Spring BEAN.
 * 
 * Lớp AppUserDAO sử dụng để thao tác với bảng APP_USER. Nó có một 
 * phương thức để tìm kiếm một người dùng trong Database ứng với 
 * tên đăng nhập nào đó.
 */
@Repository
@Transactional
public class MyUserDAO extends JdbcDaoSupport {
	@Autowired
	public MyUserDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public MyUser findUserAccount(String userName) {
		try {
			String sql = "SELECT u.USER_ID, u.USER_NAME, u.FULL_NAME, u.ENCRYPTED_PASSWORD"
					+ " FROM app_user u WHERE u.USER_NAME = ?";
			MyUser mu = this.getJdbcTemplate().queryForObject(sql, new Object[] { userName }, 
					new MyUserMapper());
			return mu;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
