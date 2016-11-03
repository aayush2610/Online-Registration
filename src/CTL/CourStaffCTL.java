package CTL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StaffDAO;
import DTO.CourseDTO;
import DTO.FacultyDTO;
import DTO.UserDTO;

public class CourStaffCTL extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		response.setContentType("text/html");
		String operation=request.getParameter("operation");
		StaffDAO dao=new StaffDAO();
		CourseDTO dto=new CourseDTO();
		if(operation.equals("add")){
			request.setAttribute("errhan", "Record has been added!");
			String courseid=request.getParameter("courseid");
			String iselect=request.getParameter("iselective");
			boolean t=false;
			if(iselect.equals("yes"))
				t=true;
			String coursename=request.getParameter("coursename");
			String instructor=request.getParameter("instructor");
			int sem=0;
			try{
			sem=Integer.parseInt(request.getParameter("sem"));}
			catch(Exception e){
				request.setAttribute("errha", "Empty fields !!");
			}
			int credits=0;
			try{
			 credits=Integer.parseInt(request.getParameter("credits"));}
			catch(Exception e){
				request.setAttribute("errha", "Credits should be a number");
			}
			String branch=request.getParameter("branch");
			if(branch.equals("none")||courseid.equals("") || coursename.equals("")||instructor.equals(""))
				request.setAttribute("errha", "Empty fields !!");
			dto.setName(coursename);
			dto.setInstructor(instructor);
			dto.setCredits(credits);
			dto.setId(courseid);
			dto.setIselective(t);
			try {
				dao.AddCourse(branch, sem, courseid, coursename, instructor, t, credits);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("CourStaff.jsp");
		dispatcher.forward(request, response);
		}
		else if(operation.equals("delete")){
			request.setAttribute("errhan", "Record has been deleted!");
			String courseid=request.getParameter("courseid");
			String branch=request.getParameter("branch");
			int sem=Integer.parseInt(request.getParameter("sem"));
			try {
				dao.RemoveCourse(branch, sem, courseid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("CourStaff.jsp");
		dispatcher.forward(request, response);
		}
		else{
			request.setAttribute("errhan", "Record has been updated!");
			String courseid=request.getParameter("courseid");
			String iselect=request.getParameter("iselective");
			boolean t=false;
			if(iselect.equals("yes"))
				t=true;
			String coursename=request.getParameter("coursename");
			String instructor=request.getParameter("instructor");
			int sem=0;
			try{
			sem=Integer.parseInt(request.getParameter("sem"));}
			catch(Exception e){
				request.setAttribute("errha", "Empty fields !!");
			}
			int credits=0;
			try{
			 credits=Integer.parseInt(request.getParameter("credits"));}
			catch(Exception e){
				request.setAttribute("errha", "Credits should be a number");
			}
			String branch=request.getParameter("branch");	
			if(branch.equals("none")||courseid.equals("") || coursename.equals("")||instructor.equals(""))
				request.setAttribute("errha", "Empty fields !!");
			dto.setName(coursename);
			dto.setInstructor(instructor);
			dto.setCredits(credits);
			dto.setId(courseid);
			dto.setIselective(t);
			try {
				dao.UpdateCourse(branch, sem, courseid, coursename, instructor, t, credits);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("CourStaff.jsp");
		dispatcher.forward(request, response);
		}	
	}

}
