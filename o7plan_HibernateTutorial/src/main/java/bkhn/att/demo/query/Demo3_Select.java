package bkhn.att.demo.query;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import bkhn.att.entities.Department;
import bkhn.att.entities.Employee;
import bkhn.att.hibernate.HibernateUtils;

public class Demo3_Select {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		System.out.println("Is session open? " + session.isOpen());	//true

		try {
			session.getTransaction().begin();
			String sql = "Select d from " + Department.class.getName() + " d ";

			@SuppressWarnings("unchecked")
			Query<Department> query = session.createQuery(sql);
			query.setFirstResult(0);
			query.setMaxResults(3);

			List<Department> depts = query.getResultList();

			for (Department dept : depts) {
				System.out.println(dept.getDeptNo() + " - " + dept.getDeptName());
				
				// sau khi gọi hàm getEmployees() thì hibernate mới lấy các employee
				// từ trong database ra, vì các Entity dùng fetch = FetchType.LAZY
				Set<Employee> emps = dept.getEmployees();
				for(Employee emp : emps) {
					System.out.println(emp.getShortInfo());
				}
				System.out.println("========");
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		// sau khi commit hoặc rollback thì session sẽ close
		System.out.println("Is session open? " + session.isOpen());	//false
		
		HibernateUtils.shutdown();
	}

}
