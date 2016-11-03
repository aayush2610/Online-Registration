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

public class StudFac extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String id="";
			id=request.getParameter("id");
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
						arr_elective=dao.getselected_courses(dto);
						request.setAttribute("arr_elective",arr_elective );
					} else {
						request.setAttribute("error", "Id does not exist.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("StudFac.jsp");
	dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	}

}
