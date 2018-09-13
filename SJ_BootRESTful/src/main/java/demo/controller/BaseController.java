package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import demo.dao.StudentDAO;
import demo.model.Student;

@Controller
public class BaseController {
	@Autowired
	StudentDAO studentDAO;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/insert-student")
	public String insertStudent() {
		return "insert_student";
	}

	/* ---------------- CREATE NEW Student ------------------------ */
	@RequestMapping(value = "/student2", method = RequestMethod.POST)
	public String createStudent2(@RequestParam("st_name") String name,	//
			@RequestParam("st_school") String school) {
		System.out.println("name = " + name + ", school = " + school);
		int insertedId = studentDAO.insertStudent(new Student(name, school));
		System.out.println("insertedId = " + insertedId);
		
		
		return "index";
	}
	
}
