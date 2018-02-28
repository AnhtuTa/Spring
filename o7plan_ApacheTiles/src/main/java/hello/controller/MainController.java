package hello.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.model.Student;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(Model model) {
		return "homePage";
	}

	@RequestMapping(value = { "/contactus" }, method = RequestMethod.GET)
	public String contactusPage(Model model) {
		model.addAttribute("address", "Vietnam");
		model.addAttribute("phone", "0975233xxx");
		model.addAttribute("email", "taanhtuxxx@gmail.com");
		return "contactusPage";
	}
	
	@RequestMapping(value = { "/student-list" }, method = RequestMethod.GET)
	public String studentList(Model model) {
		model.addAttribute("stList", getStudentList());
		return "studentListPage";
	}
	
	public List<Student> getStudentList() {
		List<Student> stList = new ArrayList<>();
		stList.add(new Student(101, "Anhtu", "Hanoi"));
		stList.add(new Student(103, "Phanh lee", "Hanoi"));
		stList.add(new Student(107, "Goku", "Tokyo"));
		stList.add(new Student(111, "Prince Vegeta", "Saiyan"));
		
		return stList;
	}

}