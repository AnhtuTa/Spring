package hello.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import hello.entity.Student;

public class StudentRepositoryImpl implements StudentRepository {
	@Autowired
	MongoOperations mongo;	// thằng này đc tạo ra ở class MongoConfig
	
//	@Autowired
//	MongoTemplate mongoTemplate;
	
	@Override
	public <S extends Student> List<S> save(Iterable<S> entites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findAll() {
		return mongo.find(new Query(), Student.class);
		// Do thằng Student.class đc annotate là: @Document(collection="student")
		// Nên mongo tự biết nó cần tìm trong Collection `student`
	}

	@Override
	public List<Student> findAll(Sort sort) {
		return mongo.find(new Query(), Student.class);
	}

	@Override
	public <S extends Student> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Student> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Student> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		return mongo.getCollection("student").count();
	}

	@Override
	public void delete(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Student arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Student> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Student> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Student> S save(S obj) {
		System.out.println("this is save method");
		mongo.save(obj);
		return obj;
	}

	@Override
	public List<Student> getByName(String studentName) {
		Query query = new Query(Criteria.where("name").is(studentName)); 
		return mongo.find(query, Student.class);
	}

	@Override
	public List<Student> getBySchool(String school) {
		Query query = new Query(Criteria.where("school").is(school)); 
		return mongo.find(query, Student.class);
	}

	@Override
	public List<Student> getByAddress(String address) {
		Query query = new Query(Criteria.where("address").is(address)); 
		return mongo.find(query, Student.class);
	}

	@Override
	public List<Student> getSomeStuffWeNeed(String studentName) {
		return mongo.find(Query.query(Criteria.where("school")
				.is("HUST").and("name").is(studentName)), Student.class);
	}

	@Override
	public List<Student> getHUSTStudents() {
		Query query = new Query(Criteria.where("school").is("HUST")); 
		return mongo.find(query, Student.class);
	}

	@Override
	public void insertStudent(Student st) {
		mongo.insert(st);
	}

}
