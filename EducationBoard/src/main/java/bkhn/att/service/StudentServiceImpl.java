package bkhn.att.service;

import java.util.List;

import bkhn.att.model.Student;
import bkhn.att.repository.StudentRepository;
import bkhn.att.repository.StudentRepositoryImpl;

public class StudentServiceImpl implements StudentService {
	StudentRepository studentRepository;
	
	public StudentServiceImpl() {
		super();
	}

	public StudentServiceImpl(StudentRepository studentRepository_param) {
		super();
		System.out.println("Constructor injection");
		this.studentRepository = studentRepository_param;
	}

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
