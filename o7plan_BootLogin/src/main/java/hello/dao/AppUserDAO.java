package hello.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hello.model.AppUser;
import hello.model.AppUserMapper;

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
public class AppUserDAO extends JdbcDaoSupport {
	@Autowired
	public AppUserDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public AppUser findUserAccount(String userName) {
		try {
			String sql = "SELECT u.user_id, u.user_name, u.encrypted_password"
					+ " FROM app_user u WHERE u.user_name = ?";
			return this.getJdbcTemplate().queryForObject(sql, new Object[] { userName }, 
					new AppUserMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
