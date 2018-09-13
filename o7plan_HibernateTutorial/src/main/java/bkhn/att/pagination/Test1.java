package bkhn.att.pagination;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import bkhn.att.entities.Employee;
import bkhn.att.hibernate.HibernateUtils;

public class Test1 {
	public static void main(String[] args) {

		// Have sessionFactory object...
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			// Tất cả các lệnh hành động với DB thông qua Hibernate
			// đều phải nằm trong 1 giao dịch (Transaction)
			// Bắt đầu giao dịch
			session.getTransaction().begin();

			String sql = "Select e from " + Employee.class.getName() + " e " //
					+ " Where e.empId > :empId ";
			Query<Employee> query = session.createQuery(sql, Employee.class);
			query.setParameter("empId", 100L);
			/*
			 * CHÚ Ý: NẾU DÙNG query.setParameter("empId", 100);
			 * thì sẽ xảy ra ngoại lệ: ClassCastException: Integer cannot be cast to Long
			 * Bởi vì cột EMP_ID trong database có kiểu Long (BigInt(20))
			 */

			int page = 6;
			int maxResult = 1;
			int maxNavigationResult = 8;

			PaginationResult<Employee> result = new PaginationResult<Employee>(query, page, maxResult,
					maxNavigationResult);

			// Result:
			List<Employee> emps = result.getListResult();
			int totalPages = result.getTotalPages();
			int totalRecords = result.getTotalRecords();
			int currentPage = result.getCurrentPage();

			for (Employee emp : emps) {
				System.out.println(emp.getShortInfo());
			}
			System.out.println("totalPages = " + totalPages);
			System.out.println("totalRecords = " + totalRecords);
			System.out.println("currentPage = " + currentPage);

			// 1 2 3 4 5 ... 11 12 13
			List<Integer> navPages = result.getNavigationPages();
			for (int pageNum : navPages) {
				System.out.print(pageNum + " ");
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}

		HibernateUtils.shutdown();
	}
}
