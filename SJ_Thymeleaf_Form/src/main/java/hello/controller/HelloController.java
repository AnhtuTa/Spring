package hello.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.entity.Employee;

@Controller
public class HelloController {
	@RequestMapping("/")
	public String index(final Model model) {
		model.addAttribute("message", "hello");
		return "index";
	}

	@RequestMapping(value = "/add-employee", method = RequestMethod.GET)
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());

		// data for form
		List<String> listFavorite = new ArrayList<String>();
		listFavorite.add("Swimming");
		listFavorite.add("Listening music");
		listFavorite.add("Walking");
		listFavorite.add("Watching movie");
		listFavorite.add("Reading comic");
		listFavorite.add("Playing COC");
		listFavorite.add("Playing Asphalt 8");

		List<String> listPosition = new ArrayList<String>();
		listPosition.add("Developer");
		listPosition.add("Designer");
		listPosition.add("Tester");
		listPosition.add("QA");
		listPosition.add("None (unemployed)");

		model.addAttribute("listFavorite", listFavorite);
	    model.addAttribute("listPosition", listPosition);

		return "add_employee";
	}
	
	@RequestMapping(value = "/add-employee", method = RequestMethod.POST)
	public String addEmployee_post(@ModelAttribute("employee") Employee employee, Model model) {
		model.addAttribute("employee", employee);
		return "view_employee";
	}
}
