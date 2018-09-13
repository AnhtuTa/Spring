package bkhn.att.demo.states;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bkhn.att.entities.Department;
import bkhn.att.entities.Employee;
import bkhn.att.hibernate.DataUtils;
import bkhn.att.hibernate.HibernateUtils;

/*
 * Class Session của Hibernate có một số các method quan trọng.
 * Chúng được chia ra các nhóm như hình "method of session in hibernate.png"
 * 
 * Một đối tượng trong Hibernate có 1 trong 4 trạng thái:
	Transient    (Tạm thời)
	Persistent   (Bền vững)
	Removed     (Đã bị xóa - dưới DB)
	Detached    (Đã bị tách riêng ra - so với session hiện tại)
 * Chúng ta giải thích chúng bằng hình "states of object in hibernate.png":
 * 1. Trường hợp bạn tạo mới một đối tượng java từ một Entity, đối tượng đó 
 * có tình trạng là Transient. Hibernate không biết về sự tồn tại của nó. 
 * Nó nằm ngoài sự quản lý của Hibernate.
 * 
 * 2. Trường hợp bạn lấy ra đối tượng Entity bằng method get, load hoặc 
 * find, bạn có được một đối tượng nó tương ứng với 1 record dưới database. 
 * Đối tượng này có trạng thái Persistent. Nó được quản lý bởi Hibernate.
 * 
 * 3. Session gọi một trong các method save,saveOrUpdate, persist, merge 
 * sẽ đẩy đối tượng Transient vào sự quản lý của Hibernate và đối tượng 
 * này chuyển sang trạng thái Persistent. Tùy tình huống nó sẽ insert 
 * hoặc update dữ liệu vào DB.
 * 
 * 4. Session gọi evict(..) hoặc clear() để đuổi các đối tượng có trạng 
 * thái persistent (bền vững) ra khỏi sự quản lý của Hibernate, giờ các 
 * đối tượng này sẽ có trạng thái mới là Detected (Bị tách ra).  Nếu nó 
 * không được đính (Attached) trở lại, nó sẽ bị bộ gom rác của Java quét 
 * đi theo cơ chế thông thường.
 * 
 * 5. Sử dụng update(..), saveOrUpdate(..), merge(..) sẽ đính trở lại 
 * các đối tượng Detached vào lại. Tùy tình huống nó sẽ tạo ra dưới DB 
 * câu lệnh update hoặc insert. Các đối tượng sẽ trở về trạng thái 
 * Persistent (bền vững).
 * 
 * 6. Session gọi method remove(..), delete(..) để xóa một bản ghi, 
 * đối tượng persistent giờ chuyển sang trạng thái Removed (Đã bị xóa).
 * 
 * Trên trang StackJava thì viết như sau:
 * Trong 1 Session persistence context (chú ý: trong Hibernate, persistence 
 * context được thể hiện bới  org.hibernate.Session. Với JPA, nó là 
 * javax.persistence.EntityManager.), các entity có 4 trạng thái:
	1. transient: đối tượng chưa bao giờ bị quản lí bởi session và nó không 
	tương ứng với bản ghi nào trong database; thông thường đây là 1 đối 
	tượng mới được tạo để save vào database
	
	2. persistent: đối tượng bị quản lý bởi session và là unique (trong 1 
	session không thể tồn tại 2 object có cùng id), sau khi flush bởi 
	session sẽ tồn tại 1 bản ghi tương ứng đối tượng này trong database.
	
	3. detached: đối tượng này đã từng bị quản lý bởi session nhưng hiện 
	tại thì không. (bị evict(), clear(), close())
	
	4. removed:cũng giống như detached nhưng bản ghi tương ứng với đối 
	tượng này trước đó đã bị xóa khỏi database. (bị remove())

	Đọc thêm: https://stackjava.com/hibernate/phan-biet-save-persist-update-merge-saveorupdate-trong-hibernate.html
	
	Chú ý:
	Các method persist, save, update, merge, saveOrUpdate không lập tức 
	đưa ra kết quả tương ứng SQL UPDATE hoặc INSERT. Thực tế thì việc 
	cập nhật dữ liệu vào database xảy ra khi transaction được commit 
	hoặc flushing session
	
	Sau khi session commit hoặc rollback thì nó sẽ tự động đóng lại, do
	đó nếu sau đó muốn dùng session thì phải getCurrentSession() 1 lần nữa
	hoặc dùng openSession()
	
 */
//Trên trang o7planning, class này tên là PersistentDemo
public class Transitent_Persistent_Detached_Demo {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();
		Department department = null;
		Employee emp = null;
		try {
			session.getTransaction().begin();

			Long maxEmpId = DataUtils.getMaxEmpId(session);
			Long empId = maxEmpId + 1;

			// Phòng ban với mã số D10.
			// Nó là đối tượng chịu sự quản lý của session
			// Và được gọi là đối tượng persistent.
			department = DataUtils.findDepartment(session, "D10");

			// Tạo mới đối tượng Employee
			// Đối tượng này chưa chịu sự quản lý của session.
			// Nó được coi là đối tượng Transient.
			emp = new Employee();
			emp.setEmpId(empId);
			emp.setEmpNo("E" + empId);
			emp.setEmpName("Name " + empId);
			emp.setJob("Coder");
			emp.setSalary(1000f);
			emp.setManager(null);
			emp.setHideDate(new Date());
			emp.setDepartment(department);

			// Sử dụng persist(..)
			// Lúc này 'emp' đã chịu sự quản lý của session.
			// nó có trạng thái persistent.
			// Chưa có hành động gì với DB tại đây.
			session.persist(emp);

			// Tại bước này dữ liệu mới được đẩy xuống DB.
			// Câu lệnh Insert được tạo ra.
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		// Sau khi session bị đóng lại (commit, rollback, close)
		// Đối tượng 'emp', 'dept' trở thành đối tượng Detached.
		// Nó không còn trong sự quản lý của session nữa.
		System.out.println("Emp No: " + emp.getEmpNo());

		HibernateUtils.shutdown();
	}
}
