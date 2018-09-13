package bkhn.att.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import bkhn.att.entities.Department;
import bkhn.att.entities.Employee;

public class DataUtils {
	public static Department findDepartment(Session session, String deptNo) {
		String hsql = "Select d from " + Department.class.getName() + //
				" d Where d.deptNo = :deptNo";
		@SuppressWarnings("unchecked")
		Query<Department> query = session.createQuery(hsql);
		query.setParameter("deptNo", deptNo);
		return query.getSingleResult();
	}

	public static Employee findEmployee(Session session, String empNo) {
		String sql = "Select e from " + Employee.class.getName() + " e "//
				+ " Where e.empNo = :empNo";
		@SuppressWarnings("unchecked")
		Query<Employee> query = session.createQuery(sql);
		query.setParameter("empNo", empNo);
		return query.getSingleResult();
	}
	
	public static Employee findEmployee(EntityManager entityManager, String empNo) {
		String sql = "Select e from " + Employee.class.getName() + " e "//
				+ " Where e.empNo = :empNo";
		TypedQuery<Employee> query = entityManager.createQuery(sql, Employee.class);
		query.setParameter("empNo", empNo);
		return query.getSingleResult();
	}

	public static Long getMaxEmpId(Session session) {
		String sql = "Select max(e.empId) from " + Employee.class.getName() + " e ";
		@SuppressWarnings("unchecked")
		Query<Number> query = session.createQuery(sql);
		Number value = query.getSingleResult();
		if (value == null) {
			return 0L;
		}
		return value.longValue();
	}
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.getTransaction().begin();
			Department d = DataUtils.findDepartment(session, "D20");
			System.out.println(d.getDeptName());
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		HibernateUtils.shutdown();
	}
}
