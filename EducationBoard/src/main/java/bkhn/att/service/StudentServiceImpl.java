package bkhn.att.service;

import java.util.List;

import bkhn.att.model.Student;
import bkhn.att.repository.StudentRepository;
import bkhn.att.repository.StudentRepositoryImpl;

public class StudentServiceImpl implements StudentService {
	StudentRepository studentRepository;
	
	public StudentRepository getStudentRepository() {
		return studentRepository;
	}

	public void setStudentRepository(StudentRepository studentRepository) {
		System.out.println("Setter injection");
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.getAllStudents();
	}
}
