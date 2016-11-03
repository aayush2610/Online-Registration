package CTL;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FacultyDAO;
import DAO.StudentDAO;
import DTO.FacultyDTO;
import DTO.StudentDTO;

public class FacultyCTL extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id="";
		id=(String)request.getParameter("dto");
		String option="";
		option=request.getParameter("option");
		String operation="";
		operation=request.getParameter("operation");
		FacultyDAO dao=new FacultyDAO();
		FacultyDTO dto=new FacultyDTO();
		//System.out.println("cdfvghyjuikj");
		try {
			dto=(FacultyDTO)dao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<String> arr=new ArrayList<String>();
		if(option.equals("approved_students")){
			try {
				//System.out.println(dto.getBranch());
				//System.out.println(dto.getSemester());
				arr=dao.approved_students(dto.getBranch(), dto.getSemester());
				System.out.println(arr.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("aord", "a_s");
			request.setAttribute("arr", arr);
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("Facultyad.jsp");
	dispatcher.forward(request, response);
		}
		else if(option.equals("disapproved_students")){
			try {
				arr=dao.disapproved_students(dto.getBranch(), dto.getSemester());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("aord", "d_s");
			request.setAttribute("arr", arr);
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("Facultyad.jsp");
	dispatcher.forward(request, response);
		}
		else{
			try {
				arr=dao.noaction_students(dto.getBranch(), dto.getSemester());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("arr", arr);
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("Facultyna.jsp");
	dispatcher.forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String operation=request.getParameter("operation");
		ArrayList<String> ids=new ArrayList<String>();
		int i=1;
		String user=request.getParameter("dto");
		StudentDAO sdao=new StudentDAO();
		StudentDTO dto=new StudentDTO();
		try {
			dto = sdao.get(user);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String branch=dto.getBranch();
		int sem=dto.getSemester();
		FacultyDAO fdao=new FacultyDAO();
		ArrayList<String> arr=new ArrayList<String>();
		try {
			arr=fdao.noaction_students(branch, sem);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int j=arr.size();
		while(i<=j){
			String sst=Integer.toString(i);
			i++;
			if(!request.getParameter(sst+"_ad").equals("none"))
				ids.add(request.getParameter(sst+"_ad"));
		}
		i=0;
		StudentDTO sdto=new StudentDTO();
		while(i<ids.size()){
			String s=ids.get(i);
			StringTokenizer st=new StringTokenizer(s,"_");
			i++;
			String id=st.nextToken();
			String selection=st.nextToken();
		try {
			sdto=sdao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(selection.equals("approve")){
			try {
				fdao.approve(sdto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(selection.equals("disapprove")){
			try {
				fdao.disapprove(sdto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
		if(operation.equals("Save")){
			FacultyDTO fdto=new FacultyDTO();
			try {
				fdto=fdao.getbs(sdto.getBranch(), sdto.getSemester());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				fdao.save(fdto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher = request
		.getRequestDispatcher("Faculty.jsp");
dispatcher.forward(request, response);
	}

}
