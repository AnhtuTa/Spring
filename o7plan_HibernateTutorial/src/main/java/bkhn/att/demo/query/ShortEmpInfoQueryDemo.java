package bkhn.att.demo.query;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import bkhn.att.hibernate.HibernateUtils;
import bkhn.att.beans.ShortEmpInfo;
import bkhn.att.entities.Employee;

public class ShortEmpInfoQueryDemo {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.getTransaction().begin();
			String sql = "Select new " + ShortEmpInfo.class.getName() +
						 "(e.empId, e.empNo, e.empName) from " +
						 Employee.class.getName() + " e";
			@SuppressWarnings("unchecked")
			Query<ShortEmpInfo> query = session.createQuery(sql);
			
			List<ShortEmpInfo> employees  = query.getResultList();
			for(ShortEmpInfo emp : employees) {
				System.out.println("Emp: " + emp.getEmpNo() + ": " + emp.getEmpName());
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
}
