package bkhn.att.entity_manager_vs_hibernate_session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import bkhn.att.entities.Employee;
import bkhn.att.entities.Timekeeper;
import bkhn.att.hibernate.DataUtils;

/*
 * Transient --> Persistent : Sử dụng persist(Object)
 * hàm này trả về kiểu void
 */
public class PersistEntityDemo {
	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private static Timekeeper persist_Transient(EntityManager entityManager, Employee emp, char state) {
		Timekeeper tk1 = new Timekeeper();

		tk1.setEmployee(emp);
		tk1.setInOut(state);
		tk1.setDateTime(new Date());

		// Now, 'tk1' is transient object
		System.out.println("- tk1 Persistent? " + entityManager.contains(tk1));

		System.out.println("====== CALL persist(tk).... ===========");

		// Hibernate gán Id vào 'tk1', sẽ chưa có insert gì cả
		entityManager.persist(tk1);

		// 'tk1' đã được gắn ID
		System.out.println("- tk1.getTimekeeperId() = " + tk1.getTimekeeperId());

		// Lúc này 'tk1' đã có trạng thái Persistent
		// Nó đã được quản lý trong Session.
		// Nhưng chưa có hành động gì insert xuống DB.
		// ==> true
		System.out.println("- tk1 Persistent? " + entityManager.contains(tk1));

		System.out.println("- Call flush..");

		// Chủ động đẩy dữ liệu xuống DB, gọi flush().
		// Nếu không gọi flush() dữ liệu sẽ được đẩy xuống tại lệnh commit().
		// Lúc này mới có insert.
		entityManager.flush();

		String timekeeperId = tk1.getTimekeeperId();
		System.out.println("- timekeeperId = " + timekeeperId);
		System.out.println("- inOut = " + tk1.getInOut());
		System.out.println("- dateTime = " + df.format(tk1.getDateTime()));
		System.out.println();
		return tk1;
	}

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = JPAUtils.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Employee emp = null;
		try {
			entityManager.getTransaction().begin();

			emp = DataUtils.findEmployee(entityManager, "E7499");

			persist_Transient(entityManager, emp, Timekeeper.OUT);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		
		JPAUtils.shutdown();
	}
}
/*
kết quả chạy:

Hibernate: select employee0_.EMP_ID as EMP_ID1_1_, employee0_.DEPT_ID as DEPT_ID8_1_, employee0_.EMP_NAME as EMP_NAME2_1_, employee0_.EMP_NO as EMP_NO3_1_, employee0_.HIRE_DATE as HIRE_DAT4_1_, employee0_.IMAGE as IMAGE5_1_, employee0_.JOB as JOB6_1_, employee0_.MNG_ID as MNG_ID9_1_, employee0_.SALARY as SALARY7_1_ from EMPLOYEE employee0_ where employee0_.EMP_NO=?
- tk1 Persistent? false
====== CALL persist(tk).... ===========
Timekeeper pre persist!
- tk1.getTimekeeperId() = c8ca4a51-3927-4ca0-91cc-3687fffdb998
- tk1 Persistent? true
- Call flush..
Hibernate: insert into TIMEKEEPER (Date_Time, EMP_ID, In_Out, Timekeeper_Id) values (?, ?, ?, ?)
Timekeeper post persist!
- timekeeperId = c8ca4a51-3927-4ca0-91cc-3687fffdb998
- inOut = O
- dateTime = 01-08-2018 11:46:53
*/
