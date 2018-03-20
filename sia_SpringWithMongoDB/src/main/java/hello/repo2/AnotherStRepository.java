package hello.repo2;

import org.springframework.data.mongodb.repository.MongoRepository;

import hello.entity.Student;

/* after the configuration, we need to create a repository – 
 * extending the existing MongoRepository interface
 */
public interface AnotherStRepository extends MongoRepository<Student, String> {
	//Now we can auto-wire this UserRepository and use operations 
	//from MongoRepository or add custom operations
	
	// StudentRepository đã có 1 vài custom operations
	// ở interface này sẽ ko thêm custom operations nào nữa
	
}
