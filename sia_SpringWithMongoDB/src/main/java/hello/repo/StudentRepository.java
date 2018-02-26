package hello.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import hello.entity.Student;

/*
 * Do nó extends MongoRepository nên Spring tự động tạo 1 bean có
 * tên là studentRepository, vì bên config đã ghi là:
 * @EnableMongoRepositories(basePackages = "hello.repo",...)
 * (Xem MongoConfig.java)
 */
public interface StudentRepository extends MongoRepository<Student, String> {
	List<Student> getByName(String studentName);
	List<Student> getBySchool(String school);
	//@org.springframework.data.mongodb.repository.Query("{'address' : ?0}")
	List<Student> getByAddress(String address);
	List<Student> getSomeStuffWeNeed(String studentName);
	List<Student> getHUSTStudents();
	
	void insertStudent(Student st);
}
