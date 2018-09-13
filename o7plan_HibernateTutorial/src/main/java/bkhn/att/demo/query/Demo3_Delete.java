package bkhn.att.demo.query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bkhn.att.entities.Employee;
import bkhn.att.hibernate.EmployeeDAO;
import bkhn.att.hibernate.HibernateUtils;

public class Demo3_Delete {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		EmployeeDAO employeeDAO = new EmployeeDAO();

		try {
			session.getTransaction().begin();
			
			Employee emp = employeeDAO.getEmployee(session, 7941);
			
			employeeDAO.deleteEmployee(session, emp);
			System.out.println("Deleted employee with id = " + emp.getEmpId());

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		HibernateUtils.shutdown();
	}

}
