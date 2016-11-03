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
import DTO.StudentDTO;
import DTO.UserDTO;

public class FacStaffCTL extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String operation=request.getParameter("operation");
		StaffDAO dao=new StaffDAO();
		FacultyDTO dto=new FacultyDTO();
		if(operation.equals("add")){
			request.setAttribute("errhan", "Record has been added!");
			String username=request.getParameter("username");
			String name=request.getParameter("name");
			int sem=Integer.parseInt(request.getParameter("sem"));
			String branch=request.getParameter("branch");	
			dto.setBranch(branch);
			dto.setName(name);
			dto.setRole("faculty");
			dto.setUsername(username);
			dto.setSemester(sem);
			dto.setPassword(username);
			try {
				dao.AddUser((UserDTO)dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("FacStaff.jsp");
		dispatcher.forward(request, response);
		}
		else if(operation.equals("delete")){
			request.setAttribute("errhan", "Record has been deleted!");
			String username=request.getParameter("username");
			dto.setUsername(username);
			try {
				dao.RemoveUser(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("FacStaff.jsp");
		dispatcher.forward(request, response);
		}
		else{
			request.setAttribute("errhan", "Record has been updated!");
			String username=request.getParameter("username");
			String name=request.getParameter("name");
			int sem=Integer.parseInt(request.getParameter("sem"));
			String branch=request.getParameter("branch");	
			dto.setBranch(branch);
			dto.setName(name);
			dto.setRole("faculty");
			dto.setUsername(username);
			dto.setSemester(sem);
			dto.setPassword(username);
			try {
				dao.UpdateUser(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("FacStaff.jsp");
		dispatcher.forward(request, response);
		}	
	}
}
