package bkhn.att.demo.states.transient_to_persistent;

import java.io.Serializable;
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
 * Transient --> Persistent : Sử dụng save(Object)
 * save() rất giống với persist()
 * save() trả về ID (kiểu Serializable) còn persist() là void.
 */
public class SaveTransientDemo {
	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private static Timekeeper save_Transient(Session session, Employee emp) {
		// Hãy chú ý:
		// timekeeperId cấu hình tự động được tạo ra bởi UUID.
		// @GeneratedValue(generator = "uuid")
		// @GenericGenerator(name = "uuid", strategy = "uuid2")
		// Tạo một đối tượng, nó đang có tình trạng Transient.
		Timekeeper tk2 = new Timekeeper();

		tk2.setEmployee(emp);
		tk2.setInOut(Timekeeper.IN);
		tk2.setDateTime(new Date());

		// Lúc này 'tk2' đang có tình trạng Transient.
		System.out.println("- tk2 Persistent? " + session.contains(tk2));

		System.out.println("====== CALL save(tk).... ===========");
		// save() rất giống với persist()
		// save() trả về ID còn persist() là void.
		// Hibernate gán Id vào 'tk2', sẽ chưa có insert gì cả
		// Nó trả về ID của 'tk2'.
		Serializable id = session.save(tk2);

		System.out.println("- id = " + id);

		// 'tk2' đã được gắn ID
		System.out.println("- tk2.getTimekeeperId() = " + tk2.getTimekeeperId());

		// Lúc này 'tk2' đã có trạng thái Persistent
		// Nó đã được quản lý trong Session.
		// Nhưng chưa có hành động gì insert xuống DB.
		// ==> true
		System.out.println("- tk2 Persistent? " + session.contains(tk2));

		System.out.println("- Call flush..");
		// Chủ động đẩy dữ liệu xuống DB, gọi flush().
		// Nếu không gọi flush() dữ liệu sẽ được đẩy xuống tại lệnh commit().
		// Lúc này mới có insert.
		session.flush();

		String timekeeperId = tk2.getTimekeeperId();
		System.out.println("- timekeeperId = " + timekeeperId);
		System.out.println("- inOut = " + tk2.getInOut());
		System.out.println("- dateTime = " + df.format(tk2.getDateTime()));
		System.out.println();
		return tk2;
	}

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();
		Employee emp = null;
		try {
			session.getTransaction().begin();

			emp = DataUtils.findEmployee(session, "E7499");

			save_Transient(session, emp);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		HibernateUtils.shutdown();
	}
}
/*
 kq chạy:
Hibernate: 
    select
        employee0_.EMP_ID as EMP_ID1_1_,
        employee0_.DEPT_ID as DEPT_ID8_1_,
        employee0_.EMP_NAME as EMP_NAME2_1_,
        employee0_.EMP_NO as EMP_NO3_1_,
        employee0_.HIRE_DATE as HIRE_DAT4_1_,
        employee0_.IMAGE as IMAGE5_1_,
        employee0_.JOB as JOB6_1_,
        employee0_.MNG_ID as MNG_ID9_1_,
        employee0_.SALARY as SALARY7_1_ 
    from
        EMPLOYEE employee0_ 
    where
        employee0_.EMP_NO=?
- tk2 Persistent? false
====== CALL save(tk).... ===========
- id = e3529054-1b79-4228-b815-b1aec172acf0
- tk2.getTimekeeperId() = e3529054-1b79-4228-b815-b1aec172acf0
- tk2 Persistent? true
- Call flush..
Hibernate: 
    insert 
    into
        TIMEKEEPER
        (Date_Time, EMP_ID, In_Out, Timekeeper_Id) 
    values
        (?, ?, ?, ?)
- timekeeperId = e3529054-1b79-4228-b815-b1aec172acf0
- inOut = I
- dateTime = 06-08-2018 10:21:47
*/
