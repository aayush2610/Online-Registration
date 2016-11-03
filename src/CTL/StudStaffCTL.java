package CTL;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StaffDAO;
import DAO.StudentDAO;
import DTO.StudentDTO;
import DTO.UserDTO;

public class StudStaffCTL extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String operation=request.getParameter("operation");
		StaffDAO dao=new StaffDAO();
		StudentDAO doa=new StudentDAO(); 
		StudentDTO dto=new StudentDTO();
		if(operation.equals("add")){
			request.setAttribute("errhan", "Record has been added!");
			String username=request.getParameter("username");
			String name=request.getParameter("name");
			String semes=request.getParameter("sem");
			
			String branch=request.getParameter("branch");	
			try {
				if(doa.checks(username)==false){
					request.setAttribute("errha", "Entry exists !!");
				}
				else if(branch.equals("none") || semes.equals("none") || name.equals("") || username.equals("")){
					request.setAttribute("errha", "Fields Empty !!");
				}
				else{
				int sem=Integer.parseInt(request.getParameter("sem"));	
				dto.setBranch(branch);
				dto.setName(name);
				dto.setRole("student");
				dto.setUsername(username);
				dto.setSemester(sem);
				dto.setPassword(username);
				try {
					dao.AddUser((UserDTO)dto);
				} catch (Exception e) {
					e.printStackTrace();
				}}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("StudStaff.jsp");
		dispatcher.forward(request, response);
		}
		else if(operation.equals("delete")){
			request.setAttribute("errhan", "Record has been deleted!");
			String username=request.getParameter("username");
			if(username.equals(""))
				request.setAttribute("errha", "Fields Empty !!");
			dto.setUsername(username);
			try {
				dao.RemoveUser(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("StudStaff.jsp");
		dispatcher.forward(request, response);
		}
		else{
			request.setAttribute("errhan", "Record has been updated!");
			String username=request.getParameter("username");
			String name=request.getParameter("name"); 
			Integer sem=new Integer(0);
			
			try{
				
			sem=Integer.parseInt(request.getParameter("sem"));}
			catch(Exception e){
				request.setAttribute("errha", "Empty Fields!");
			}
			try {
				if(doa.checks(username)==true){
					request.setAttribute("errha", "Entry does not exists !!");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String branch=request.getParameter("branch");
			if(username==null || name.equals("") || sem==0||branch==null){
				request.setAttribute("errha", "Empty Fields!");
			}
			else{
			dto.setBranch(branch);
			dto.setName(name);
			dto.setRole("student");
			dto.setUsername(username);
			dto.setSemester(sem);
			dto.setPassword(username);
			try {
				dao.UpdateUser(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("StudStaff.jsp");
		dispatcher.forward(request, response);
		}
	}

}
