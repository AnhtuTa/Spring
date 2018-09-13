package bkhn.att.demo.states.transient_to_persistent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bkhn.att.entities.Employee;
import bkhn.att.entities.Timekeeper;
import bkhn.att.hibernate.DataUtils;
import bkhn.att.hibernate.HibernateUtils;

/*
 * Transient --> Persistent : Sử dụng persist(Object)
 * hàm này trả về kiểu void
 */
public class PersistTransientDemo {
	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private static Timekeeper persist_Transient(Session session, Employee emp, char state) {
		// Hãy chú ý:
		// timekeeperId cấu hình tự động được tạo ra bởi UUID.
		// @GeneratedValue(generator = "uuid")
		// @GenericGenerator(name = "uuid", strategy = "uuid2")
		// Tạo một đối tượng, nó đang có tình trạng Transient.

		Timekeeper tk1 = new Timekeeper();

		tk1.setEmployee(emp);
		tk1.setInOut(state);
		tk1.setDateTime(new Date());

		// Now, 'tk1' is transient object
		System.out.println("- tk1 Persistent? " + session.contains(tk1));

		System.out.println("====== CALL persist(tk).... ===========");

		// Hibernate gán Id vào 'tk1', sẽ chưa có insert gì cả

		session.persist(tk1);

		// 'tk1' đã được gắn ID
		System.out.println("- tk1.getTimekeeperId() = " + tk1.getTimekeeperId());

		// Lúc này 'tk1' đã có trạng thái Persistent
		// Nó đã được quản lý trong Session.
		// Nhưng chưa có hành động gì insert xuống DB.
		// ==> true

		System.out.println("- tk1 Persistent? " + session.contains(tk1));

		System.out.println("- Call flush..");

		// Chủ động đẩy dữ liệu xuống DB, gọi flush().
		// Nếu không gọi flush() dữ liệu sẽ được đẩy xuống tại lệnh commit().
		// Lúc này mới có insert.

		session.flush();

		String timekeeperId = tk1.getTimekeeperId();
		System.out.println("- timekeeperId = " + timekeeperId);
		System.out.println("- inOut = " + tk1.getInOut());
		System.out.println("- dateTime = " + df.format(tk1.getDateTime()));
		System.out.println();
		return tk1;
	}

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();
		Employee emp = null;
		try {
			session.getTransaction().begin();

			emp = DataUtils.findEmployee(session, "E7499");

			persist_Transient(session, emp, Timekeeper.OUT);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		HibernateUtils.shutdown();
	}
}
/*
kết quả chạy:

Hibernate: select employee0_.EMP_ID as EMP_ID1_1_, employee0_.DEPT_ID as DEPT_ID8_1_, employee0_.EMP_NAME as EMP_NAME2_1_, employee0_.EMP_NO as EMP_NO3_1_, employee0_.HIRE_DATE as HIRE_DAT4_1_, employee0_.IMAGE as IMAGE5_1_, employee0_.JOB as JOB6_1_, employee0_.MNG_ID as MNG_ID9_1_, employee0_.SALARY as SALARY7_1_ from EMPLOYEE employee0_ where employee0_.EMP_NO=?
- tk1 Persistent? false
====== CALL persist(tk).... ===========
- tk1.getTimekeeperId() = 193e8d51-6ea4-47af-b8da-fd7a7938eead
- tk1 Persistent? true
- Call flush..
Hibernate: insert into TIMEKEEPER (Date_Time, EMP_ID, In_Out, Timekeeper_Id) values (?, ?, ?, ?)
- timekeeperId = 193e8d51-6ea4-47af-b8da-fd7a7938eead
- inOut = I
- dateTime = 30-07-2018 15:29:47
*/
