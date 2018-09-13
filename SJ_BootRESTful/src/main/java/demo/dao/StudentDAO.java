package demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import demo.model.Student;

@Repository
public class StudentDAO extends JdbcDaoSupport {

	@Autowired
	public StudentDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public List<Student> getAllStudents() {
		String sql = "SELECT * FROM student";
		return this.getJdbcTemplate().query(sql, new StudentDAO.StudentMapper());
	}
	
	public Student getStudent(int id) {
		String sql = "SELECT * FROM student WHERE id = " + id;
		List<Student> stList = this.getJdbcTemplate().query(sql, new StudentDAO.StudentMapper());
		if(stList.size() != 0)
			return stList.get(0);
		else return null;
	}
	
	public int containStudent(int id) {
		String sql = "SELECT EXISTS (SELECT 1 FROM Student WHERE id = ?)";
		return getJdbcTemplate().queryForObject(sql, new Object[] { id }, Integer.class);
	}
	
	public int insertStudent(Student st) {
		String sql = "INSERT INTO Student(name, school) "
				+ "VALUES (?,?)";
		String [] params = {st.getName(), st.getSchool()};
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    	            	for(int i=0; i<params.length; i++) {
    	            		pst.setNString(i+1, params[i]);
    	            	}
    	            return pst;
    	        }
    	    },
    	    keyHolder);
    	return Integer.valueOf(keyHolder.getKey() + "");
	}
	
	public int updateStudent(Student st) {
		try {
			String sql = "UPDATE student SET ";
			//Field []fields = st.getClass().getSuperclass().getDeclaredFields();
			
			if(st.getName() != null && !"".equals(st.getName())) {
				sql += "name = '" + st.getName() + "',"; 
			}
			
			if(st.getSchool() != null && !"".equals(st.getSchool())) {
				sql += "school = '" + st.getSchool() + "',";
			}
			
			sql = sql.substring(0, sql.length()-1);		//remove the last comma
			
			sql += " WHERE id = " + st.getId();
			System.out.println("sql = " + sql);
			
			return this.getJdbcTemplate().update(sql);
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deleteStudent(int id) {
		String sql = "DELETE FROM Student WHERE id = ?";
		return this.getJdbcTemplate().update(sql, new Object[] {id});
	}
	
	public static class StudentMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student s = new Student();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setSchool(rs.getString("school"));
			
			return s;
		}
		
	}
}
