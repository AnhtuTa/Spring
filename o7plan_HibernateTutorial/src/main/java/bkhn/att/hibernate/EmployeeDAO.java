package bkhn.att.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import bkhn.att.entities.Employee;

/*
 * Class này tự viết, không có trong tutorial
 */
public class EmployeeDAO {
	/*
	 * Sẽ xảy ra lỗi khi lấy department từ employee, vì session đã close rồi! (Xemo
	 * Demo3_Select2.java)
	 */
	@Deprecated
	public List<Employee> getEmployees(int start, int size) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<Employee> emps = null;

		try {
			session.getTransaction().begin();
			String sql = "Select e from " + Employee.class.getName() + " e";

			@SuppressWarnings("unchecked")
			Query<Employee> query = session.createQuery(sql);
			query.setFirstResult(start);
			query.setMaxResults(size);

			emps = query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return emps;
	}

	public List<Employee> getEmployees(Session session, int start, int size) {
		String sql = "Select e from " + Employee.class.getName() + " e";
		@SuppressWarnings("unchecked")
		Query<Employee> query = session.createQuery(sql);
		query.setFirstResult(start);
		query.setMaxResults(size);
		return query.getResultList();
	}

	public Employee getEmployee(Session session, long empId) {
		String sql = "Select e from " + Employee.class.getName() + " e " + //
				"where e.empId = :id";
		@SuppressWarnings("unchecked")
		Query<Employee> query = session.createQuery(sql);
		query.setParameter("id", empId);

		// nếu return như sau thì khi mà không tìm được record nào thì sẽ xảy ra
		// ngoại lệ: NoResultException: No entity found for query
		// return query.getSingleResult();

		// Cách làm sau chắc ăn hơn, không ném ra ngoại lệ!
		List<Employee> empList = query.getResultList();
		System.out.println("[EmployeeDAO] size = " + empList.size());
		if (empList.size() == 0)
			return null;
		else
			return empList.get(0);
	}

	public long insertEmployee(Session session, Employee emp) {
		session.persist(emp);
		return emp.getEmpId();
	}

	public void updateEmployee(Session session, Employee emp) {
		session.update(emp);
	}

	public void deleteEmployee(Session session, Employee emp) {
		session.delete(emp);
	}
}
