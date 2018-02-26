package hello;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.config.MongoConfig;
import hello.entity.Student;
import hello.repo.StudentRepository;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = 
			new AnnotationConfigApplicationContext(MongoConfig.class);
		
		StudentRepository sr = (StudentRepository) context.getBean("studentRepository");
		System.out.println("The amount of documents = " + sr.count());
		
		List<Student> stList = sr.getByName("Anhtu");
		for(Student st :  stList) {
			st.printInfo();
		}
		
		System.out.println("======================== getBySchool");
		List<Student> hustStudents = sr.getBySchool("HUST");
		for(Student st :  hustStudents) {
			st.printInfo();
		}
		
		System.out.println("======================== findAll");
		List<Student> addStudents = sr.findAll();
		for(Student st :  addStudents) {
			st.printInfo();
		}
		
		System.out.println("======================== getByAddress");
		stList = sr.getByAddress("Hanoi");
		for(Student st : stList) {
			st.printInfo();
		}
		
		System.out.println("======================== getSomeStuffWeNeed");
		stList = sr.getSomeStuffWeNeed("Anhtu");
		if(stList.size() != 0) {
			for(Student st :  stList) {
				st.printInfo();
			}
		} else System.out.println("Empty document!");
		
//		Student st2 = new Student("Vegeta", "HUST", "Tokyo", 50);
//		sr.insertStudent(st2);
		
		((ConfigurableApplicationContext)context).close();
	}
}
