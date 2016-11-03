package CTL;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DAO.StudentDAO;
import DTO.CourseDTO;
import DTO.StudentDTO;

public class StudentCTL extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = null;
		try {
			id = request.getParameter("login");
			//System.out.println("frrf");
		} catch (NumberFormatException e1) {
		}
		StudentDAO dao=new StudentDAO();
		int sem;
		String branch;
		if(id!=null){
			try {
				StudentDTO dto = dao.get(id);
				sem=dto.getSemester();
				branch=dto.getBranch();
				if (dto != null) {
					request.setAttribute("dto", dto);
					request.setAttribute("branch", branch);
					request.setAttribute("sem", sem);
					ArrayList<CourseDTO> arr;
					arr=dao.getCourses(dto);
					request.setAttribute("arr", arr);
					ArrayList<CourseDTO> arr_elective;
					arr_elective=dao.getElective(dto);
					request.setAttribute("arr_elective",arr_elective );
				} else {
					request.setAttribute("error", "Id does not exist.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("Studentreg.jsp");
	dispatcher.forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("fesggs");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String operation = request.getParameter("operation");
		StudentDAO dao=new StudentDAO();
		String ecourse_1=null;
		String ecourse_2=null;
		try {
			ecourse_1 = request.getParameter("ecourse_1");
			ecourse_2 = request.getParameter("ecourse_2");
		} catch (NumberFormatException e1) {
		}
		if("submit".equals(operation)){
			System.out.println("dgdg");
			String username=(String)request.getParameter("dto");
			StudentDTO dto=null;
			try {
				dto = (StudentDTO)dao.get(username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				
				dao.AddElective(ecourse_1, ecourse_2,dto);
				dao.submit(dto);
				RequestDispatcher dispatcher = request
				.getRequestDispatcher("Student.jsp");
		dispatcher.forward(request, response);}
			 catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("save".equals(operation)){
			StudentDTO dto=(StudentDTO)request.getAttribute("user");
			try {
				dao.AddElective(ecourse_1, ecourse_2,dto);
				RequestDispatcher dispatcher = request
				.getRequestDispatcher("Student.jsp");
		dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
