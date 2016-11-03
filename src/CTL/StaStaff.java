package CTL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StaffDAO;
import DTO.FacultyDTO;
import DTO.StaffDTO;
import DTO.UserDTO;

public class StaStaff extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String operation=request.getParameter("operation");
		StaffDAO dao=new StaffDAO();
		StaffDTO dto=new StaffDTO();
		String username="";
		username=request.getParameter("username");
		String name="";
		name=request.getParameter("name");
		String id="";
		id=request.getParameter("id");
		if(username.equals("") || name.equals("")||id.equals(""))
			request.setAttribute("errha", "Empty Fields !!");
		else{dto.setId(id);
		dto.setName(name);
		request.setAttribute("errhan", "Record has been added!");
		dto.setRole("staff");
		dto.setUsername(username);
		dto.setPassword(username);
		try {
			dao.AddUser((UserDTO)dto);
		} catch (Exception e) {
			e.printStackTrace();
		}}
		RequestDispatcher dispatcher = request
		.getRequestDispatcher("StaStaff.jsp");
	dispatcher.forward(request, response);
	}

}
