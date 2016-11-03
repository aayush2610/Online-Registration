package CTL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import DTO.UserDTO;

public class LoginCTL extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Pre-Populated data
		HttpSession session = request.getSession(true);
		String operation = request.getParameter("operation");

		if ("Logout".equals(operation)) {
			session.invalidate();
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("Login.jsp");
		dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);

		String operation = request.getParameter("operation");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String role =request.getParameter("role");
		if ("Login".equals(operation)) {
			try {
				UserDAO userDAO = new UserDAO();

				UserDTO dto = userDAO.authenticate(login, password,role);

				if (dto != null) {
					session.setAttribute("abc", login);
					session.setAttribute("user", dto);// check
					request.setAttribute("dto", dto);
					//request.setAttribute("name", "fff");
					
					if(role.equals("student")){
						session.setMaxInactiveInterval(30 * (60 * 1000));
						RequestDispatcher dispatcher = request
							.getRequestDispatcher("Student.jsp");
						dispatcher.forward(request, response);
					}
					else if(role.equals("faculty")){
						session.setAttribute("user", dto);// check
						request.setAttribute("dto", dto);
						session.setMaxInactiveInterval(30 * (60 * 1000));
						RequestDispatcher dispatcher = request
						.getRequestDispatcher("Faculty.jsp");
					dispatcher.forward(request, response);
					}
					else if(role.equals("staff")){
						session.setAttribute("user", dto);// check
						request.setAttribute("dto", dto);
						session.setMaxInactiveInterval(30 * (60 * 1000));
						RequestDispatcher dispatcher = request
						.getRequestDispatcher("Staff.jsp");
					dispatcher.forward(request, response);
					}
				} else {
					request.setAttribute("error",
							"Invalid Username or Password or Role");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("Login.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}