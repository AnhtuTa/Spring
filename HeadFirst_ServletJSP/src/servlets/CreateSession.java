package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Dept;
import beans.Dog;
import beans.Manager;
import beans.Person;
import beans.Toy;

/**
 * Servlet implementation class CreateSession
 */
@WebServlet("/CreateSession")
public class CreateSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();
		Toy toy1 = new Toy("bup be babe", 3.7);
		Toy toy2 = new Toy("sieu nhan", 8.2);
		Toy toy3 = new Toy("bomb", 6.5);
		Toy [] toys = {toy1, toy2, toy3};
		
		ss.setMaxInactiveInterval(3600);	//session co hieu luc toi da trong 1h
		ss.setAttribute("person_ss", new Person("Hoang Trung Nguyen", "TP. HCM", new Dog("John Smith", toys)));
		ss.setAttribute("name_ss", "Anhtuta");
		ss.setAttribute("manager_ss", new Manager("nguyentdk1", "Tran Dang Khoi Nguyen", new Dept("IT", "Information Technology")));
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		pw.println("<h3>Da thiet lap 1 so Attribute cho session</h3>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
