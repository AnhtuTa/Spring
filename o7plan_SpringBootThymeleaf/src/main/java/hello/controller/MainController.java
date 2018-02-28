package hello.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.form.PersonForm;
import hello.model.Person;

/*
 * MainController là một lớp Controller, nó xử lý yêu cầu của người
 * dùng và điều khiển luồng đi (flow) của ứng dụng.
 */
@Controller
public class MainController {
	private static List<Person> persons = new ArrayList<Person>();

	static {
		persons.add(new Person("Bill", "Gates"));
		persons.add(new Person("Steve", "Jobs"));
	}

	// Được tiêm vào (inject) từ application.properties.
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("message", message);
		return "index";
	}

	@RequestMapping(value = { "/person-list" }, method = RequestMethod.GET)
	public String personList(Model model) {
		model.addAttribute("persons", persons);
		return "person-list";
	}

	@RequestMapping(value = { "/add-person" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {

		PersonForm personForm = new PersonForm();
		model.addAttribute("personForm", personForm);

		return "add-person";
	}

	// TẠI SAO KHÔNG dùng thằng Person luôn mà phải thông qua thằng PersonForm???
	@RequestMapping(value = { "/add-person" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {

		String firstName = personForm.getFirstName();
		String lastName = personForm.getLastName();

		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			Person newPerson = new Person(firstName, lastName);
			persons.add(newPerson);

			return "redirect:/person-list";
		} else {
			model.addAttribute("errorMessage", errorMessage);
			return "add-person";
		}
	}

}
