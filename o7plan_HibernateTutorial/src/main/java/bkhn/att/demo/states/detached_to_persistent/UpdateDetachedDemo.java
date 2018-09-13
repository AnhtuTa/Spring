package bkhn.att.demo.states.detached_to_persistent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bkhn.att.entities.Employee;
import bkhn.att.hibernate.DataUtils;
import bkhn.att.hibernate.HibernateUtils;

/*
 * Một đối tượng có trạng thái Detached (Bị tách ra khỏi sự 
 * quản lý của Hibernate) có thể được Attach trở lại thông 
 * qua một vài method của Session:
	update(Object)
	saveOrUpdate(Object)
	merge(Object)
	refresh(Object)
	lock(Object)
	
	Trong ví dụ sau:
	Detached --> Persistent : Sử dụng update(Object)
 */
public class UpdateDetachedDemo {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session1 = factory.getCurrentSession();
		Employee emp = null;
		try {
			session1.getTransaction().begin();

			// Đây là đối tượng có trạng thái Persistent.
			emp = DataUtils.findEmployee(session1, "E7499");

			// session1 đã bị đóng lại sau commit được gọi.
			session1.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session1.getTransaction().rollback();
		}

		// Mở một Session khác

		Session session2 = factory.getCurrentSession();

		try {
			session2.getTransaction().begin();

			// Kiểm tra trạng thái của emp:
			// ==> false
			System.out.println("- emp Persistent? " + session2.contains(emp));

			System.out.println("Emp salary: " + emp.getSalary());

			emp.setSalary(emp.getSalary() + 100);

			// update(..) chỉ áp dụng cho đối tượng Detached.
			// (Không dùng được với đối tượng Transient).
			// Sử dụng update(emp) để đưa emp trở lại trạng thái Persistent.
			session2.update(emp);

			// Chủ động đẩy dữ liệu xuống DB.
			// Câu lệnh update sẽ được gọi.
			session2.flush();

			System.out.println("Emp salary after update: " + emp.getSalary());

			// session2 đã bị đóng lại sau commit được gọi.
			session2.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session2.getTransaction().rollback();
		}

		HibernateUtils.shutdown();
	}
}
