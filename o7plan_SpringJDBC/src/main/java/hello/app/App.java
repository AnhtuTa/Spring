package hello.app;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.config.ApplicationContextConfig;
import hello.dao.DepartmentDAO;

/*
 * Phần này chỉ làm vài ví dụ để demo phần JdbcDaoSupport
 * (Do làm khá nhiều cái này rồi!)
 * có thể xem thêm tại:
 * https://o7planning.org/vi/10301/huong-dan-su-dung-spring-jdbc#a114610
 * 
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
		//DepartmentDAO dd = (DepartmentDAO) context.getBean(DepartmentDAO.class);
		DepartmentDAO dd = (DepartmentDAO) context.getBean("dept_dao_wtf");
		List<String> nameList = dd.getDeptNames();
		for(String s : nameList) {
			System.out.println(s);
		}
		
		nameList = dd.getDeptNames("Production");
		for(String s : nameList) {
			System.out.println(s);
		}
		
		System.out.println("========================");
		List<Map<String, Object>> mapList = dd.queryForList_ListMap();
		for(Map<String, Object> m : mapList) {
			for(String key : m.keySet()) {
				System.out.println(key + ": " + m.get(key));
			}
			System.out.println("-----");
		}
		
		((ConfigurableApplicationContext)context).close();
	}
}
