package bkhn.att.demo.query;

import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import bkhn.att.hibernate.HibernateUtils;
import bkhn.att.entities.Department;
import bkhn.att.entities.Employee;

public class UniqueResultDemo {
	public static Department getDepartment(Session session, String deptNo) {
		String sql = "select d from " + Department.class.getName() + " d" +
					 " where d.deptNo = :deptNo";
		@SuppressWarnings("unchecked")
		Query<Department> query = session.createQuery(sql);
		query.setParameter("deptNo", deptNo);
		return query.getSingleResult();
	}
	
	public static Employee getEmployee(Session session, Long empId) {
		String sql = "select e from " + Employee.class.getName() + " e" +
					 " where e.empId = :empId";
		@SuppressWarnings("unchecked")
		Query<Employee> query = session.createQuery(sql);
		query.setParameter("empId", empId);
		return query.getSingleResult();
	}
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.getTransaction().begin();
			Department dept = getDepartment(session, "D10");
			Set<Employee> empSet = dept.getEmployees();
			
			System.out.println("Dept name: " + dept.getDeptName());
			
			for(Employee emp : empSet) {
				System.out.println("Emp name: " + emp.getEmpName());
			}
			
			Employee emp = getEmployee(session, 7839L);
			System.out.println("Emp name: " + emp.getEmpName());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		HibernateUtils.shutdown();
	}
}
