package bkhn.att.demo.query;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bkhn.att.entities.Department;
import bkhn.att.entities.Employee;
import bkhn.att.hibernate.DataUtils;
import bkhn.att.hibernate.DepartmentDAO;
import bkhn.att.hibernate.EmployeeDAO;
import bkhn.att.hibernate.HibernateUtils;

public class Demo3_Insert {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		DepartmentDAO departmentDAO = new DepartmentDAO();

		try {
			session.getTransaction().begin();
			
			long maxCurrEmpId = DataUtils.getMaxEmpId(session);
			Employee manager = employeeDAO.getEmployee(session, 7369);
			Department d = departmentDAO.getDepartment(session, 20);
			
			Employee emp = new Employee(maxCurrEmpId+1, "Yorn", "MARKSMAN", manager, new Date(), 2300f, d);
			
			long insertedId = employeeDAO.insertEmployee(session, emp);
			System.out.println("Inserted new employee with ID = " + insertedId);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		HibernateUtils.shutdown();
	}

}
