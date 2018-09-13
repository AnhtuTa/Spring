package bkhn.att.demo.states;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bkhn.att.entities.Department;
import bkhn.att.hibernate.DataUtils;
import bkhn.att.hibernate.HibernateUtils;

/*
 * Sau khi gọi lệnh session.flush(): bất kỳ những thay đổi nào trên
 * các đối tượng persistent đều sẽ đc update xuống database.
 * 
 * Sau khi gọi lệnh session.getTransaction().commit(): tương tự như trên
 */
public class PersistentDemo {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();
		Department department = null;

		try {
			session.getTransaction().begin();

			System.out.println("- Finding Department deptNo = D10...");
			// Đây là một đối tượng có trạng thái Persistent.
			department = DataUtils.findDepartment(session, "D10");

			System.out.println("- First change Location");
			// Thay đổi gì đó trên đối tượng Persistent.
			department.setLocation("Chicago " + System.currentTimeMillis());
			System.out.println("- Location = " + department.getLocation());

			System.out.println("- Calling flush...");
			// Sử dụng session.flush() để chủ động đẩy các thay đổi xuống DB.
			// Có tác dụng trên tất cả các đối tượng Persistent có thay đổi.
			session.flush();
			System.out.println("- Flush OK");

			System.out.println("- Second change Location");
			// Thay đổi gì đó trên đối tượng Persistent.
			// Hình thành câu lệnh update, sẽ được thực thi
			// sau khi session đóng lại (commit).
			department.setLocation("Chicago " + System.currentTimeMillis());

			// In ra Location.
			System.out.println("- Location = " + department.getLocation());

			System.out.println("- Calling commit...");
			// Lệnh commit sẽ làm tất cả những sự thay đổi được đẩy xuống DB.
			session.getTransaction().commit();

			System.out.println("- Commit OK");
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		// Tạo lại session sau khi nó đã bị đóng trước đó
		// (Do commit hoặc rollback)
		session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();

			System.out.println("- Finding Department deptNo = D10...");

			// Query lại Department D10.
			department = DataUtils.findDepartment(session, "D10");

			// In ra thông tin Location.
			System.out.println("- D10 Location = " + department.getLocation());

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		HibernateUtils.shutdown();
	}
}
/*
Kết quả chạy:

Hibernate: select department0_.DEPT_ID as DEPT_ID1_0_, department0_.DEPT_NAME as DEPT_NAM2_0_, department0_.DEPT_NO as DEPT_NO3_0_, department0_.LOCATION as LOCATION4_0_ from DEPARTMENT department0_ where department0_.DEPT_NO=?
- First change Location
- Location = Chicago 1532937893807
- Calling flush...
Hibernate: update DEPARTMENT set DEPT_NAME=?, DEPT_NO=?, LOCATION=? where DEPT_ID=?
- Flush OK
- Second change Location
- Location = Chicago 1532937893824
- Calling commit...
Hibernate: update DEPARTMENT set DEPT_NAME=?, DEPT_NO=?, LOCATION=? where DEPT_ID=?
- Commit OK
- Finding Department deptNo = D10...
Hibernate: select department0_.DEPT_ID as DEPT_ID1_0_, department0_.DEPT_NAME as DEPT_NAM2_0_, department0_.DEPT_NO as DEPT_NO3_0_, department0_.LOCATION as LOCATION4_0_ from DEPARTMENT department0_ where department0_.DEPT_NO=?
- D10 Location = Chicago 1532937893824

*/
