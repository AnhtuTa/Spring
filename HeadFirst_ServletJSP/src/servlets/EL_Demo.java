package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Toy;

/**
 * Servlet implementation class EL_Demo
 */
@WebServlet("/el_demo")
public class EL_Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Can chay file CreateSession.java truoc! Neu ko se xay ra loi: java.lang.NullPointerException
		request.setAttribute("comics_rq", "One Piece, Dragonball, Conan");
		request.setAttribute("toy_rq", new Toy("Xe oto", 12.8));
		request.getRequestDispatcher("/WEB-INF/jsps/el_demo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
