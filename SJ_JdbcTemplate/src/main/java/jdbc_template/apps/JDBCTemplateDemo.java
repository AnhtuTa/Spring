package jdbc_template.apps;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import jdbc_template.entities.Pupil;

/*
Method update của JdbcTemplate dùng để thực hiện các câu SQL thao 
 tác dữ liệu như INSERT, UPDATE, DELTE
Nếu method update của bạn không có tham số truyền vào câu SQL thì
 nó sẽ thực hiện thông qua Statement
Nếu method update của bạn truyền tham số vào câu SQL thì nó thực
 hiện thông qua PreparedStatement

 */
public class JDBCTemplateDemo {
	JdbcTemplate jdbcTemplate;
	
	public void getJDBC() {
		if(jdbcTemplate != null) return;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("app_context.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		((ClassPathXmlApplicationContext) context).close();
	}
	
	@SuppressWarnings("rawtypes")
	public void getPupilsByName(String name) {
		getJDBC();
		String sql = "SELECT * FROM pupil WHERE name LIKE '%" + name + "%'";
		List<Map<String, Object>> pList = jdbcTemplate.queryForList(sql);
		System.out.println(pList);
		/* kq in:
		 * [
		 * 		{id=1, name=anhtu, addr=Hanoi, country=1},
		 * 		{id=17, name=anhtu2, addr=Hanoi2, country=3},
		 * 		{id=18, name=anhtu3, addr=Hanoi3, country=3},
		 * 		{id=19, name=anhtu4, addr=Hanoi4, country=3},
		 * 		{id=20, name=anhtu5, addr=Hanoi5, country=3},
		 * 		{id=23, name=anhtu2, addr=Hanoi2, country=3}
		 * ]
		 * 
		 * Từ kq trên có thể thấy:
		 * pList là 1 danh sách các map (Mỗi row tương ứng với 1 map)
		 * mỗi map có size = số cột mà câu truy vẫn trả về
		 * mỗi bản ghi của mỗi map có format: key=value, trong đó key là tên của cột
		 * và value là giá trị của nó trong CSDL
		 */
		
		for(Map<String, Object> pMap : pList) {
			//System.out.println(pMap.size());
			for(Entry entry : pMap.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
			}
			System.out.println();
		}
		
		//cách display khác:
		for (Map<String, Object> pMap : pList) {
			System.out.println(pMap.get("id") + " - " + pMap.get("name") + " - " + pMap.get("addr"));
		}
	}

	public List<Pupil> getPupilListByName(String name) {
		getJDBC();
		String sql = "SELECT * FROM pupil WHERE name = ?";
		return jdbcTemplate.query(sql, new PupilMapper(), name);
	}
	
	public List<Pupil> getPupilListLikeName(String name) {
		getJDBC();
		String sql = "SELECT * FROM pupil WHERE name LIKE '%" + name + "%'";
		return jdbcTemplate.query(sql, new PupilMapper());
	}
	 
	public void insertPupil(Pupil p) {
		getJDBC();
		String sql = "INSERT INTO `pupil` (`name`, `addr`, `country`) VALUES (?,?,?);";
		jdbcTemplate.update(sql, p.getName(), p.getAddress(), p.getCountryId());
		
		System.out.println("a pupil was inserted");
	}
	
	public void insertPupils(List<Pupil> pList) {
		getJDBC();
		String sql = "INSERT INTO `pupil` (`name`, `addr`, `country`) VALUES (?,?,?);";
		for(Pupil p : pList) {
			jdbcTemplate.update(sql, p.getName(), p.getAddress(), p.getCountryId());
		}
		
		System.out.println("a list of pupil was inserted");
	}
	
	public void updatePupils(String field, String oldValue, String newValue) {
		getJDBC();
		String sql = "UPDATE pupil SET " + field + " = '" + newValue + "' WHERE " + field + " = '" +  oldValue + "';";
		jdbcTemplate.update(sql);
		System.out.println("All pupils have `" + field + "` = '" + oldValue + "' have been updated!");
	}
	
	public void deletePupilsByName(String name) {
		getJDBC();
		String sql = "DELETE FROM pupil WHERE name = '" + name + "';";
		jdbcTemplate.update(sql);
		System.out.println("All pupils have `name` = '" + name + "' have been deleted!");
	}
	
	public static void main(String[] args) {
		JDBCTemplateDemo jtd = new JDBCTemplateDemo();
		
//		jtd.insertPupil(new Pupil("Lionel Messi", "Argentina", 5));
//		
//		List<Pupil> pList = new ArrayList<Pupil>();
//		for(int i = 1; i <= 5; i++) {
//			pList.add(new Pupil("anhtu"+i, "Hanoi"+i, 3));
//		}
//		jtd.insertPupils(pList);
		
		jtd.getPupilsByName("anhtu");
		
		List<Pupil> pList = jtd.getPupilListByName("anhtu");
		for(Pupil p : pList) {
			System.out.println(p.toString());
		}
		
		pList = null;
		pList = jtd.getPupilListLikeName("anhtu");
		for(Pupil p : pList) {
			System.out.println(p.toString());
		}
		
		//jtd.updatePupils("name", "anhtu1", "anhtu1_updated");
		
		//jtd.deletePupilsByName("anhtu1_updated");
	}
}
