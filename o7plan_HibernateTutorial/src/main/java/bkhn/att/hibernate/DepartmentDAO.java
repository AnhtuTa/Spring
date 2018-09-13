package bkhn.att.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import bkhn.att.entities.Department;

public class DepartmentDAO {
	public Department getDepartment(Session session, int deptId) {
		String sql = "Select d from " + Department.class.getName() + " d " + //
				"where d.deptId = :id";
		@SuppressWarnings("unchecked")
		Query<Department> query = session.createQuery(sql);
		query.setParameter("id", deptId);

		// nếu return như sau thì khi mà không tìm được record nào thì sẽ xảy ra
		// ngoại lệ: NoResultException: No entity found for query
		// return query.getSingleResult();

		// Cách làm sau chắc ăn hơn, không ném ra ngoại lệ!
		List<Department> deptList = query.getResultList();
		System.out.println("[DepartmentDAO] size = " + deptList.size());
		if (deptList.size() == 0)
			return null;
		else
			return deptList.get(0);
	}
}
