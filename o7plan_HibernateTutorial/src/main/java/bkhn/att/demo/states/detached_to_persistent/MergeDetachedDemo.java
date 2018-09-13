package bkhn.att.demo.states.detached_to_persistent;

import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import bkhn.att.entities.Employee;
import bkhn.att.hibernate.DataUtils;
import bkhn.att.hibernate.HibernateUtils;

/*
 * Detached --> Persistent : Sử dụng merge(Object)
 * 
 * Hibernate phiên bản 2 có method saveOrUpdateCopy(Object), từ phiên 
 * bản 3 trở lên nó đổi tên thành merge(Object). Chính vì vậy merge 
 * có một chút tương đồng và một chút khác biệt so với saveOrUpdate.
 * 
 * merge(Object) không đưa đối tượng vào sự quản lý của Hibernate 
 * nhưng nó tạo ra một bản copy của đối tượng đó và quản lý đối tượng copy. 
 * 
 * Nếu bạn gọi saveOrUpdate(aObject) trong khi đã có một đối 
 * tượng bObject đang được quản lý bởi Hibernate và có cùng ID
 * với aObject một ngoại lệ sẽ được ném ra. Trong khi sử dụng 
 * merge(aObject) sẽ không bị ngoại lệ này.
 */
public class MergeDetachedDemo {
	public static void main(String[] args) {

		// Một đối tượng có trạng thái Detached.
		Employee emp = getEmployee_Detached();

		System.out.println(" - GET EMP " + emp.getEmpId());

		// Ngẫu nhiên xóa hoặc không xóa Employee ứng với ID.
		boolean delete = deleteOrNotDelete(emp.getEmpId());

		System.out.println(" - DELETE? " + delete);

		// saveOrUpdate đối tượng Detached.
		saveOrUpdate_test(emp);

		// Sau khi gọi saveOrUpdate().
		// Có thể ID của Entity sẽ khác đi trong trường hợp
		// entity có ID tự tăng và saveOrUpdate tạo ra câu Insert.
		System.out.println(" - EMP ID " + emp.getEmpId());
	}

	// Hàm trả về một đối tượng Employee đã
	// nằm ngoài sự quản lý của Hibernate (Detached).
	private static Employee getEmployee_Detached() {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session1 = factory.getCurrentSession();
		Employee emp = null;
		try {
			session1.getTransaction().begin();

			Long maxEmpId = DataUtils.getMaxEmpId(session1);
			System.out.println(" - Max Emp ID " + maxEmpId);

			// Thằng emp2 lấy từ database ra để set data cho thằng emp thôi
			Employee emp2 = DataUtils.findEmployee(session1, "E7839");

			Long empId = maxEmpId + 1;
			emp = new Employee();
			emp.setEmpId(empId);
			emp.setEmpNo("E" + empId);

			emp.setDepartment(emp2.getDepartment());
			emp.setEmpName(emp2.getEmpName());

			emp.setHideDate(emp2.getHideDate());
			emp.setJob("Test");
			emp.setSalary(1000F);

			// emp đã được quản lý bởi Hibernate
			session1.persist(emp);

			// session1 đã bị đóng lại sau commit được gọi.
			// Một bản ghi Employee đã được insert vào DB.
			session1.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session1.getTransaction().rollback();
		}
		// session1 đã bị đóng 'emp' đã trở về trạng thái Detached.
		return emp;
	}

	// Xóa Employee theo ID cho bởi tham số.
	// Ngẫu nhiên xóa hoặc không xóa.
	private static boolean deleteOrNotDelete(Long empId) {
		// Một số ngẫu nhiên từ 0-9
		int random = new Random().nextInt(10);
		if (random < 5) {
			return false;
		}
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session2 = factory.getCurrentSession();
		try {
			session2.getTransaction().begin();
			String sql = "Delete " + Employee.class.getName() + " e " + " where e.empId =:empId ";
			@SuppressWarnings("unchecked")
			Query<Employee> query = session2.createQuery(sql);
			query.setParameter("empId", empId);

			query.executeUpdate();

			session2.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session2.getTransaction().rollback();
			return false;
		}
	}

	private static void saveOrUpdate_test(Employee emp) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		// Mở một Session khác

		Session session3 = factory.getCurrentSession();

		try {
			session3.getTransaction().begin();

			// Thực tế emp đang có trạng thái Detached
			// Nó không được quản lý bởi Hibernate.
			// Kiểm tra trạng thái của emp:
			// ==> false
			System.out.println(" - emp Persistent? " + session3.contains(emp));

			System.out.println(" - Emp salary before update: " + emp.getSalary());

			// Set salary mới cho đối tượng Detached emp.
			// Bạn cũng có thể sét ID mới nếu muốn.
			emp.setSalary(emp.getSalary() + 100);

			// merge(emp) trả về empMerge, một bản copy của emp,
			// empMerge được quản lý bởi Hibernate.
			// Còn emp vẫn trong tình trạng Detached
			//
			// Lúc này vẫn chưa có sử lý gì liên quan DB.
			Employee empMerge = (Employee) session3.merge(emp);

			// ==> false
			System.out.println(" - emp Persistent? " + session3.contains(emp));
			// ==> true
			System.out.println(" - empMerge Persistent? " + session3.contains(empMerge));

			// Chủ động đẩy dữ liệu xuống DB.
			// Tại đây có thể có thể tạo ra câu Insert hoặc Update vào DB.
			// Nếu bản ghi tương ứng đã bị xóa bởi ai đó, câu lệnh Insert sẽ
			// được tạo ra.
			// Ngược lại sẽ là một câu lệnh Update.
			session3.flush();

			System.out.println(" - Emp salary after update: " + emp.getSalary());

			// session3 đã bị đóng lại sau commit được gọi.
			session3.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session3.getTransaction().rollback();
		}

		HibernateUtils.shutdown();
	}
}
