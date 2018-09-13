package bkhn.att.demo.states.persistent_to_detached;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bkhn.att.entities.Employee;
import bkhn.att.hibernate.DataUtils;
import bkhn.att.hibernate.HibernateUtils;

/*
 * Một đối tượng đang có tình trạng Persistent (Được quản lý 
 * bởi Hibernate) có thể chuyển sang trạng thái Detached (Tách ra,
 * không được Hibernate quản lý)  thông qua method của Session:
 * - evict(Object): Đuổi cổ một đối tượng ra khỏi sự quản lý 
 *   của Hibernate
 * - clear(): Đuổi cổ tất các các đối tượng đang được quản lý 
 *   bởi Hibernate ra.
 *   
 * Tất nhiên khi một session gọi commit(), close() hoặc rollback()
 * lúc đó session đã kết thúc. Tất cả các đối tượng Persistence 
 * của session này sẽ là Detached đối với một session mới được mở ra. 
 */
public class EvictDemo {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();
		Employee emp = null;
		try {
			session.getTransaction().begin();

			// Đây là một đối tượng có tình trạng Persistent.
			emp = DataUtils.findEmployee(session, "E7499");

			// ==> true
			System.out.println("- emp Persistent? " + session.contains(emp));
			System.out.println("empNo = " + emp.getEmpNo());

			// Sử dụng evict(Object) để đuổi đối tượng Persistent
			// ra khỏi quản lý của Hibernate.
			session.evict(emp);

			// Lúc này 'emp' đang có trạng thái Detached.
			// ==> false
			System.out.println("- emp Persistent? " + session.contains(emp));

			// Tất cả các thay đổi trên 'emp' sẽ không được update
			// nếu không đưa 'emp' trở lại trạng thái Persistent.
			emp.setEmpNo("NEW");
			System.out.println("empNo = " + emp.getEmpNo());

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		HibernateUtils.shutdown();
	}
}
