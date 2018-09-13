package hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.entity.School;
import hello.repository.SchoolRepository;

@Service
public class SchoolService {
	
	@Autowired
	SchoolRepository schoolRepository;
	
	public List<School> getAllSchools() {
		return schoolRepository.findAll();
	}
}
