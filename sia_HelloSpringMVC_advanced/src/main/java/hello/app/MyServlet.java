package hello.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/profile")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 5804543006019651462L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", "Today is 12/2/2018, Tet is few days later!!!");
		req.getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
	}
}
