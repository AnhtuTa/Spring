package bkhn.att.entity_manager_vs_hibernate_session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import bkhn.att.entities.Employee;

public class QuerySomeColumnDemo {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = JPAUtils.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			// Query một vài cột.
			// Việc lấy dữ liệu trong trường hợp này sẽ phức tạp hơn.
			String sql = "Select e.empId, e.empNo, e.empName from " + //
					Employee.class.getName() + " e ";
			TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class);
			query.setFirstResult(3);
			query.setMaxResults(5);
			
			// Thực hiện truy vấn.
			// Lấy ra danh sách các đối tượng Object[]
			List<Object[]> datas = query.getResultList();
			for (Object[] emp : datas) {
				System.out.println("Emp Id: " + emp[0]);
				System.out.println(" Emp No: " + emp[1]);
				System.out.println(" Emp Name: " + emp[2]);
			}
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}

		JPAUtils.shutdown();
	}
}