package bkhn.att.demo.states.persistent_to_detached;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bkhn.att.entities.Department;
import bkhn.att.entities.Employee;
import bkhn.att.hibernate.DataUtils;
import bkhn.att.hibernate.HibernateUtils;

/*
 * Xem comment ở EvictDemo
 */
public class ClearDemo {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();
		Employee emp = null;
		Department dept = null;
		try {
			session.getTransaction().begin();

			// Đây là các đối tượng có tình trạng Persistent.
			emp = DataUtils.findEmployee(session, "E7499");
			dept = DataUtils.findDepartment(session, "D10");
			System.out.println("- emp Persistent? " + session.contains(emp));
			System.out.println("- dept Persistent? " + session.contains(dept));

			// Sử dụng clear để đuổi hết tất cả các đối tượng có
			// trạng thái Persistent ra khỏi sự quản lý của Hibernate.
			session.clear();

			// Lúc này 'emp' & 'dept' đang có trạng thái Detached.
			// ==> false
			System.out.println("- emp Persistent? " + session.contains(emp));
			System.out.println("- dept Persistent? " + session.contains(dept));

			// Tất cả các thay đổi trên 'emp' sẽ không được update
			// nếu không đưa 'emp' trở lại trạng thái Persistent.
			emp.setEmpNo("NEW");
			dept.setDeptName("This update will have no effect!");

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		HibernateUtils.shutdown();
	}
}
