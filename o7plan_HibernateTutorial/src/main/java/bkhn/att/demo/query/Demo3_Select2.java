package bkhn.att.demo.query;

import java.util.List;

import bkhn.att.entities.Employee;
import bkhn.att.hibernate.EmployeeDAO;
import bkhn.att.hibernate.HibernateUtils;

public class Demo3_Select2 {

	public static void main(String[] args) {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		@SuppressWarnings("deprecation")
		List<Employee> empList = employeeDAO.getEmployees(1, 5);
		
		for(Employee emp : empList) {
			System.out.println(emp.getShortInfo());
			
			// Session đã bị close nên không thể lấy Department được nữa!
			System.out.println(emp.getDepartment().getDeptName());
		}
		
		HibernateUtils.shutdown();
	}

}
