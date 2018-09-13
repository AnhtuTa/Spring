package bkhn.att.demo.query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bkhn.att.entities.Department;
import bkhn.att.entities.Employee;
import bkhn.att.hibernate.DepartmentDAO;
import bkhn.att.hibernate.EmployeeDAO;
import bkhn.att.hibernate.HibernateUtils;

public class Demo3_Update {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		DepartmentDAO departmentDAO = new DepartmentDAO();

		try {
			session.getTransaction().begin();
			
			Employee emp = employeeDAO.getEmployee(session, 7942);
			Department d = departmentDAO.getDepartment(session, 10);
			
			emp.setDepartment(d);
			emp.setEmpName(emp.getEmpName() + " - updated");
			
			employeeDAO.updateEmployee(session, emp);
			System.out.println("Updated employee with id = " + emp.getEmpId());

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		HibernateUtils.shutdown();
	}

}
