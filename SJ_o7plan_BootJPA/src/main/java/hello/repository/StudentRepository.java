package hello.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findAll();
	
	// Khai báo các method truy vấn, ở đây mình đặt tên theo chuẩn nên 
	// không cần khai báo rõ câu sql truy vấn:
	List<Student> findByName(String name);
	
	List<Student> findByNameLike(String name);
	
	List<Student> findAllByOrderByNameDesc();
    
	//Nếu có field address sẽ dùng đc hàm sau
    //List<Student> findByNameAndAddress(String name, String address);
    
    List<Student> findByIdIn(List<Integer> ids);
    
    List<Student> findByIdLessThan(int index);
}
