package bkhn.att.demo.query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import bkhn.att.entities.Employee;
import bkhn.att.hibernate.HibernateUtils;

public class QueryObjectDemo {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		/*
		 * Trong Hibernate, khi bạn lấy một session từ đối tượng SessionFactory, 
		 * bạn có thể sử dụng openSession hoặc getCurrentSession. Nếu bạn sử dụng 
		 * openSession, nó sẽ mở một session mới. Nếu bạn sử dụng getCurrentSession, 
		 * nó sẽ lấy session hiện tại từ thread context đang tồn tại.
		 * 
		 * getCurrentSession() thì session sẽ tự động đẩy dữ liệu (flush()) và 
		 * đóng (close()) session
		 * 
		 * Đối với phương thức openSession(), sau khi truy vấn dữ liệu (thêm, 
		 * xóa, sửa) thì session vẫn còn giữ và không tự động đẩy (flush()) hay 
		 * close mà bạn phải tự làm việc này
		 */
		Session session = factory.getCurrentSession();

		try {
			// Tất cả các lệnh hành động với DB thông qua Hibernate
			// đều phải nằm trong 1 giao dịch (Transaction)
			// Bắt đầu giao dịch
			session.getTransaction().begin();

			// Tạo một câu lệnh HQL query object.
			// Tương đương với Native SQL:
			// Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO

			String sql = "Select e from " + Employee.class.getName() + " e " //
					+ " order by e.empName, e.empNo";
			// Nếu bên class Employee có annotation: @Entity(name="employee_haha")
			// Thì có thể viết câu query như sau:
			// String sql = "Select e from employee_haha e " + " order by e.empName, e.empNo ";
			
			// Tạo đối tượng Query.
			Query<Employee> query = session.createQuery(sql);
			query.setFirstResult(0);
			query.setMaxResults(3);

			// Thực hiện truy vấn.
			List<Employee> employees = query.getResultList();

			for (Employee emp : employees) {
				//System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
				System.out.println(emp.getShortInfo());
				System.out.println("\tDEPT: " + emp.getDepartment().getDeptName());
			}

			// Commit dữ liệu
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		// không cần làm việc này nữa (nếu dùng openSession() thì mới cần)
//		finally {
//			session.flush();
//			session.close();
//		}
//		
		HibernateUtils.shutdown();	//nếu dùng lệnh này thì stop chương trình sau khi chạy file này.
		// Sau đó ko thể dùng đc session nữa!
		// session = factory.getCurrentSession();	//IllegalStateException: EntityManagerFactory is closed
	}

}