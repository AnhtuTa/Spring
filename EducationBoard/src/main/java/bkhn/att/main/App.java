package bkhn.att.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bkhn.att.model.Student;
import bkhn.att.service.StudentService;
import bkhn.att.service.StudentServiceImpl;

public class App {
	public static void main(String[] args) {
//		StudentService ss = new StudentServiceImpl();
//		List<Student> stList = ss.getAllStudents();
//		for(Student st : stList) {
//			System.out.println(st);
//		}
		
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		StudentService service = context.getBean("stServiceBean", StudentService.class);
		//Co the lam cach khac nhu sau
		//StudentService service = (StudentService) context.getBean("stServiceBean");
		System.out.println(service.getAllStudents());
		
	}
}
