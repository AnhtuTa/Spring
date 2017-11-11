package bkhn.att.repository;

import java.util.ArrayList;
import java.util.List;

import bkhn.att.model.Student;

public class StudentRepositoryImpl implements StudentRepository {
	@Override
	public List<Student> getAllStudents() {
		List<Student> stList = new ArrayList<>();
		stList.add(new Student("Anhtu", "Hanoi"));
		stList.add(new Student("Nguyen Bka", "HCM"));
		
		return stList;
	}
}
