package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Dog;
import beans.Employee;
import beans.Human;
import beans.Person;
import beans.Toy;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> foodList = new ArrayList<>();
		foodList.add("banana");
		foodList.add("grape");
		foodList.add("meat");
		foodList.add("fish");
		
		Toy toy1 = new Toy("car", 3.7);
		Toy toy2 = new Toy("superman", 8.2);
		Toy toy3 = new Toy("doll", 6.5);
		Toy [] toys = {toy1, toy2, toy3};
		Dog d = new Dog("John", toys);
		Person p = new Person("Anhtu", "Hanoi", d);
		
		Human h = new Employee("Phanh Lee", "Thanh Oai", 123, "Training");

		Map<String, Person> personMap = new HashMap<>();
		personMap.put("mem1", new Person("Att", "HN", d));
		personMap.put("mem2", new Person("Linh", "Lao cai", d));
		personMap.put("mem3", new Person("Nguyen", "Hai phong", d));
		personMap.put("mem4", new Person("Huy", "Beijing", d));
		
		request.setAttribute("foodList", foodList);
		request.setAttribute("person1", p);
		request.setAttribute("human1", h);
		request.setAttribute("personMap", personMap);
		
		
		request.getRequestDispatcher("/WEB-INF/jsps/hello.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
