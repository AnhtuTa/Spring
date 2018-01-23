package hello.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hello.entities.Employee;

/*
 * Tham số model hay modelMap trong các method là các biến được dùng 
 * để truyền dữ liệu giữa view với controller, hoặc giữa các controller với nhau (giữa trang này và trang kia, vì 1 trang thường có 1 controller).
 * VD: ở method add-employee: model.addAttribute("employee", new Employee()); 
 * nó sẽ truyền một đối tượng new Employee() với name là employee tới view add-employee
 */
@Controller
public class EmployeeController {
	
	@RequestMapping(value="/add-employee", method=RequestMethod.GET)
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		List<String> favoriteList = new ArrayList<String>();
		favoriteList.add("Swimming");
		favoriteList.add("Listening music");
		favoriteList.add("Walking");
		favoriteList.add("Watching movie");
		favoriteList.add("Reading comic");
		favoriteList.add("Playing COC");
		favoriteList.add("Playing Asphalt 8");
		model.addAttribute("favoriteList", favoriteList);
		
		List<String> positionList = new ArrayList<String>();
		positionList.add("Developer");
		positionList.add("Designer");
		positionList.add("Tester");
		positionList.add("QA");
	    model.addAttribute("positionList", positionList);
		
		return "add_employee";
	}
	
	@RequestMapping(value="/view-employee", method=RequestMethod.GET)
	public String doAddEmployee(@ModelAttribute("employee") Employee employee, Model model) {
		//employee sẽ nhận từ request gửi tới. như đã nói, bên add-employee sẽ
		//truyền data sang controller này thông qua model hoặc modelMap
		//model này là biến chứa data từ bên add-employee
		model.addAttribute("employee", employee);
		System.out.println(employee.getId());
		System.out.println(employee.getName());
		System.out.println(employee.getAddress());
		System.out.println(employee.getEmail());
		System.out.println(employee.getGender());
		System.out.println(employee.getPosition());
		System.out.println(employee.getFavorites());
		
		return "view_employee";
	}

	// Làm lại ví dụ trên nhưng làm cách khác, ko dùng taglib form:form của java
	@RequestMapping(value= {"/add-employee2"}, method=RequestMethod.GET)
	public String addEmployee2(Model model) {
		List<String> favoriteList = new ArrayList<String>();
		favoriteList.add("Swimming");
		favoriteList.add("Listening music");
		favoriteList.add("Walking");
		favoriteList.add("Watching movie");
		favoriteList.add("Reading comic");
		favoriteList.add("Playing COC");
		favoriteList.add("Playing Asphalt 8");
		model.addAttribute("favoriteList", favoriteList);
		
		List<String> positionList = new ArrayList<String>();
		positionList.add("Developer");
		positionList.add("Designer");
		positionList.add("Tester");
		positionList.add("QA");
	    model.addAttribute("positionList", positionList);
		
		return "add_employee2";
	}
	
	@RequestMapping(value="/view-employee2", method=RequestMethod.POST)
	public String doAddEmployee2(
			@RequestParam("txt_id") int id,
			@RequestParam("txt_name") String name,
			@RequestParam("txt_address") String address,
			@RequestParam("txt_email") String email,
			@RequestParam("gender") String gender,
			@RequestParam("favorites") List<String> favorites,
			@RequestParam("position") String position, Model model) {
		model.addAttribute("employee", new Employee(id, name, address, email, gender, favorites, position));
		
		return "view_employee2";
	}
}
