package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.CourseDTO;
import DTO.StudentDTO;

public class StudentDAO extends UserDAO{
	public void AddElective(String course1,String course2,StudentDTO st) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE STUDENT SET ECOURSE_1=? ,ECOURSE_2=? WHERE ROLLNO=?");
		stmt.setString(1, course1);
		stmt.setString(2,course2);
		stmt.setString(3, st.getUsername());
		int i = stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	public ArrayList<CourseDTO> getselected_courses(StudentDTO st) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
		.prepareStatement("SELECT ECOURSE_1, ECOURSE_2 FROM STUDENT WHERE ROLLNO=?");
		stmt.setString(1,st.getUsername());
		ResultSet resultSet=stmt.executeQuery();
		ArrayList<CourseDTO> arr=new ArrayList<CourseDTO>();
		if(resultSet.next()){
			PreparedStatement stmt1 = conn
			.prepareStatement("SELECT * FROM "+st.getBranch()+"_"+Integer.toString(st.getSemester())+" WHERE COURSE_ID=?");
			stmt1.setString(1, resultSet.getString(1));
			ResultSet rs=stmt1.executeQuery();
			
			if(rs.next()){
				CourseDTO dto=new CourseDTO();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setInstructor(rs.getString(3));
				dto.setCredits(rs.getInt(4));
				dto.setIselective(rs.getBoolean(5));
				arr.add(dto);
			}
			stmt1.setString(1, resultSet.getString(2));
			rs=stmt1.executeQuery();
			if(rs.next()){
				CourseDTO dto=new CourseDTO();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setInstructor(rs.getString(3));
				dto.setCredits(rs.getInt(4));
				dto.setIselective(rs.getBoolean(5));
				arr.add(dto);
			}
		}
		return arr;
	}
	public ArrayList<CourseDTO> getCourses(StudentDTO st) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
		.prepareStatement("SELECT * FROM "+st.getBranch()+"_"+Integer.toString(st.getSemester())+" WHERE ISELECTIVE=?");
		stmt.setBoolean(1, false);
		ResultSet resultSet = stmt.executeQuery();
		ArrayList<CourseDTO> arr=new ArrayList<CourseDTO>();
		while(resultSet.next()){
			CourseDTO dto=new CourseDTO();
			dto.setId(resultSet.getString(1));
			dto.setName(resultSet.getString(2));
			dto.setInstructor(resultSet.getString(3));
			dto.setCredits(resultSet.getInt(4));
			dto.setIselective(resultSet.getBoolean(5));
			arr.add(dto);
		}
		stmt.close();
		conn.close();
		return arr;
	}
	public ArrayList<CourseDTO> getElective(StudentDTO st) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
		.prepareStatement("SELECT * FROM "+st.getBranch()+"_"+Integer.toString(st.getSemester())+" WHERE ISELECTIVE=?");
		stmt.setBoolean(1, true);
		ResultSet resultSet = stmt.executeQuery();
		ArrayList<CourseDTO> arr=new ArrayList<CourseDTO>();
		while(resultSet.next()){
			CourseDTO dto=new CourseDTO();
			dto.setId(resultSet.getString(1));
			dto.setName(resultSet.getString(2));
			dto.setInstructor(resultSet.getString(3));
			dto.setCredits(resultSet.getInt(4));
			dto.setIselective(resultSet.getBoolean(5));
			arr.add(dto);
		}
		stmt.close();
		conn.close();
		return arr;
	}
	public boolean checks(String username) throws Exception{//to check if student is already present
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt= conn
		.prepareStatement("SELECT * FROM STUDENT WHERE ROLLNO=?");
		stmt.setString(1, username);
		ResultSet resultSet=stmt.executeQuery();
		if(resultSet.next()){
			return false;
		}
		else
			return true;
	}
	public void submit(StudentDTO dto) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt3= conn
		.prepareStatement("SELECT IS_SUBMITTED FROM STUDENT WHERE ROLLNO=?");
		stmt3.setString(1,dto.getUsername());
		ResultSet resultSet=stmt3.executeQuery();
		if(resultSet.next()){
			boolean a;
			a=resultSet.getBoolean(1);
			if(a==false){
				PreparedStatement stmt2 = conn
				.prepareStatement("INSERT INTO "+"f"+"_"+dto.getBranch()+"_"+Integer.toString(dto.getSemester())+" (ROLLNO,APPROVE,DISAPPROVE) VALUES (?,?,?)");
				stmt2.setString(1,dto.getUsername());
				stmt2.setBoolean(2, false);
				stmt2.setBoolean(3, false);
				int j = stmt2.executeUpdate();
				stmt2.close();
			}
		}
		stmt3.close();
		PreparedStatement stmt = conn
		.prepareStatement("UPDATE STUDENT SET IS_SUBMITTED=? WHERE ROLLNO=?");
		stmt.setBoolean(1, true);
		stmt.setString(2,dto.getUsername() );
		int i = stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	public StudentDTO get(String id) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt3= conn
		.prepareStatement("SELECT * FROM STUDENT WHERE ROLLNO=?");
		stmt3.setString(1,id);
		ResultSet resultSet=stmt3.executeQuery();
		StudentDTO dto=null;
		if(resultSet.next()){
			dto=new StudentDTO();
			dto.setBranch(resultSet.getString(1));
			dto.setSemester(resultSet.getInt(2));
			dto.setUsername(resultSet.getString(3));
			dto.setName(resultSet.getString(4));
			dto.setApproval_status(resultSet.getInt(5));
		}
		stmt3.close();
		conn.close();
		return dto;
	}
	public int is_submitted(StudentDTO dto) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt=conn.prepareStatement("SELECT IS_SUBMITTED FROM STUDENT WHERE ROLLNO=?");
		stmt.setString(1, dto.getUsername());
		ResultSet resultSet=stmt.executeQuery();
		int x=0;
		if(resultSet.next()){
			x=resultSet.getInt(1);
		}
		stmt.close();
		conn.close();
		return x;
	}
	public int get_approval_status(StudentDTO dto) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt=conn.prepareStatement("SELECT APPROVAL_STATUS FROM STUDENT WHERE ROLLNO=?");
		stmt.setString(1, dto.getUsername());
		ResultSet resultSet=stmt.executeQuery();
		int x=0;
		if(resultSet.next()){
			x=resultSet.getInt(1);
		}
		stmt.close();
		conn.close();
		return x;
	}
//	public static void main(String[] args) throws Exception {
//		StudentDAO dao=new StudentDAO();
//		dao.AddElective("DS","ds", new StudentDTO("Avan","","cs",1,0));
//		dao.submit( new StudentDTO("d","","cs",1,0));
//		System.out.println(dao.getCourses(new StudentDTO(null,null,"cs",1,0)));
//		
//	}
	
}
