package hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.entity.Student;
import hello.repository.StudentRepository;

/*
 * Xem thêm: https://stackjava.com/spring/code-vi-du-spring-data-query-creation-dat-ten-method.html
 */
@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public List<Student> getStudentsByName(String name) {
		return studentRepository.findByName(name);
	}
}
