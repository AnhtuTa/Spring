package bkhn.att.entity_manager_vs_hibernate_session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import bkhn.att.entities.Employee;

public class QueryObjectDemo {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = JPAUtils.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();

			// Tạo một câu lệnh HQL query object.
			// Tương đương với Native SQL:
			// Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
			String sql = "Select e from " + Employee.class.getName() + " e " //
					+ " order by e.empName, e.empNo";

			// Tạo đối tượng TypedQuery.
			TypedQuery<Employee> typedQuery = entityManager.createQuery(sql, Employee.class); 
			typedQuery.setFirstResult(0);
			typedQuery.setMaxResults(3);

			// Thực hiện truy vấn.
			List<Employee> employees = typedQuery.getResultList();

			for (Employee emp : employees) {
				//System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
				System.out.println(emp.getShortInfo());
			}

			// Commit dữ liệu
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			entityManager.getTransaction().rollback();
		}
		
		JPAUtils.shutdown();
	}
}
