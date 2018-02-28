package hello.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.model.Person;

/*
 * MainController là một lớp Controller, nó xử lý yêu cầu của người
 * dùng và điều khiển luồng đi (flow) của ứng dụng.
 */
@Controller
public class MainController {
	private static List<Person> persons = new ArrayList<Person>();

	static {
		List<String> listFavorite = new ArrayList<String>();
		listFavorite.add("Swimming");
		listFavorite.add("Listening music");
		listFavorite.add("Walking");
		listFavorite.add("Watching movie");
		
		List<String> listFavorite2 = new ArrayList<String>();
		listFavorite2.add("Reading comic");
		listFavorite2.add("Playing COC");
		listFavorite2.add("Playing Asphalt 8");
		
		persons.add(new Person("Bill", "Gates", listFavorite));
		persons.add(new Person("Steve", "Jobs", listFavorite2));
	}

	// Được tiêm vào (inject) từ application.properties.
	@Value("${welcome.message}")
	private String message;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("message", message);
		return "index";
	}

	@RequestMapping(value = { "/person-list" }, method = RequestMethod.GET)
	public String personList(Model model) {
		model.addAttribute("persons", persons);
		return "person_list";
	}
}
