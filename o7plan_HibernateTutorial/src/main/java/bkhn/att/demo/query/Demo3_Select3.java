package bkhn.att.demo.query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bkhn.att.entities.Employee;
import bkhn.att.hibernate.EmployeeDAO;
import bkhn.att.hibernate.HibernateUtils;

public class Demo3_Select3 {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		System.out.println("Is session open? " + session.isOpen()); // true
		EmployeeDAO employeeDAO = new EmployeeDAO();

		try {
			session.getTransaction().begin();
			Employee emp = employeeDAO.getEmployee(session, 7941);
			if (emp != null)
				System.out.println(emp.getShortInfo());
			else
				System.out.println("No employee found!");

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		// sau khi commit hoặc rollback thì session sẽ close
		System.out.println("Is session open? " + session.isOpen()); // false

		HibernateUtils.shutdown();
	}

}
