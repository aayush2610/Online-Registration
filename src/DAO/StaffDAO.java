package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.*;

public class StaffDAO extends UserDAO {
	public StaffDTO get(String username) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM STAFF WHERE USERNAME=?");
		
		stmt.setString(1, username);
		ResultSet resultSet=stmt.executeQuery();
		PreparedStatement stmt2 = conn
		.prepareStatement("SELECT * FROM USER WHERE USERNAME=?");
		stmt2.setString(1, username);
		ResultSet result=stmt2.executeQuery();
		StaffDTO dto=new StaffDTO();
		if(resultSet.next() && result.next())
		dto=new StaffDTO(username,result.getString(2),resultSet.getString(2),resultSet.getString(1));
		stmt.close();
		conn.close();
		return dto;
	}
	public void AddUser(UserDTO dto) throws Exception
	{
		String uName=dto.getUsername();
		String role=dto.getRole();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO USER(USERNAME,PASSWORD,ROLE) VALUES(?,?,?)");
		stmt.setString(1, uName);
		stmt.setString(2, uName);
		stmt.setString(3, role);
		int i = stmt.executeUpdate();
		stmt.close();
		conn.close();
		if(role.equals("student")){
			AddUser((StudentDTO)dto);
		}
		else if(role.equals("staff")){
			AddUser((StaffDTO)dto);
		}
		else if(role.equals("faculty")){
			AddUser((FacultyDTO)dto);
		}
	}
	public void UpdateUser(StudentDTO dto) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE STUDENT SET BRANCH=? , SEMESTER=? , NAME=? WHERE ROLLNO=?");
		stmt.setString(1, dto.getBranch());
		stmt.setInt(2, dto.getSemester());
		stmt.setString(3, dto.getName());
		stmt.setString(4, dto.getUsername());
		int i = stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	public void UpdateUser(FacultyDTO dto) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE FACULTY SET BRANCH=? , SEMESTER=? , NAME=? WHERE USERNAME=?");
		stmt.setString(1, dto.getBranch());
		stmt.setInt(2, dto.getSemester());
		stmt.setString(3, dto.getName());
		stmt.setString(4, dto.getUsername());
		int i = stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	public void AddUser(StudentDTO st)throws Exception
	{
		String Branch=st.getBranch();
		int semester=st.getSemester();
		String rollNo=st.getUsername();
		String name=st.getName();
		Class.forName("com.mysql.jdbc.Driver");
		String s="STUDENT";
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO "+s+"(BRANCH,SEMESTER,ROLLNO,NAME,APPROVAL_STATUS) VALUES(?,?,?,?,?)");
		stmt.setString(1, Branch);
		stmt.setInt(2, semester);
		stmt.setString(3, rollNo);
		stmt.setString(4, name);
		stmt.setInt(5, 0);
		int i = stmt.executeUpdate();
		//System.out.println("Record Add" + i);
		stmt.close();
		conn.close();
	}
	public void AddUser(StaffDTO st)throws Exception
	{
		
		String name=st.getName();
		String id=st.getId();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO STAFF(ID,NAME,USERNAME) VALUES(?,?,?)");
		stmt.setString(1,id);
		stmt.setString(2, name);
		stmt.setString(3, st.getUsername());
		int i = stmt.executeUpdate();
		//System.out.println("Record Add" + i);
		stmt.close();
		conn.close();
	}
	
	public void AddUser(FacultyDTO st)throws Exception
	{
		String name=st.getName();
		String branch=st.getBranch();
		int semester=st.getSemester();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO FACULTY(NAME,BRANCH,SEMESTER,USERNAME) VALUES(?,?,?,?)");
		stmt.setString(1,name);
		stmt.setString(2, branch);
		stmt.setInt(3, semester);
		stmt.setString(4, st.getUsername());
		int i = stmt.executeUpdate();
		//System.out.println("Record Add" + i);
		stmt.close();
		conn.close();
	}
	public void AddCourse(String branch,int semester,String course_id, String course_name,String course_instructor,boolean elective,int credits)throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
		.prepareStatement("INSERT INTO "+branch+"_"+Integer.toString(semester)+"(COURSE_ID,COURSE_NAME,COURSE_INSTRUCTOR,CREDITS,ISELECTIVE) VALUES(?,?,?,?,?)");
			stmt.setString(1, course_id);
			stmt.setString(2, course_name);
			stmt.setString(3, course_instructor);
			stmt.setBoolean(5, elective);
			stmt.setInt(4,credits);
			int i = stmt.executeUpdate();
			stmt.close();
			conn.close();
	}
	public void UpdateCourse(String branch,int semester,String course_id, String course_name,String course_instructor,boolean elective,int credits)throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
		.prepareStatement("UPDATE "+branch+"_"+Integer.toString(semester)+" SET COURSE_NAME=? , COURSE_INSTRUCTOR=? , CREDITS=? , ISELECTIVE=? WHERE COURSE_ID=? ");
			stmt.setString(5, course_id);
			stmt.setString(1, course_name);
			stmt.setString(2, course_instructor);
			stmt.setBoolean(4, elective);
			stmt.setInt(3,credits);
			int i = stmt.executeUpdate();
			stmt.close();
			conn.close();
	}
	public void RemoveCourse(String branch,int semester,String course_id)throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
		.prepareStatement("DELETE FROM "+branch+"_"+Integer.toString(semester)+" WHERE COURSE_ID=? ");
			stmt.setString(1, course_id);
			int i = stmt.executeUpdate();
			stmt.close();
			conn.close();
	}
//	public static void main(String[] args) throws Exception {
//		StaffDAO dao=new StaffDAO();
//		UserDTO dto=new StudentDTO("name","password","branch",1,0);
//		UserDTO do2=new StaffDTO("nam1","p1","name","ada123");
//		//dao.AddUser(dto);
//		//dao.AddUser(do2);
//		dao.AddCourse("cs", 1, "D", "d", "d", true, 3);
//	}
//}

public void RemoveUser(StudentDTO dto)throws Exception
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/registration", "root", "root");
	PreparedStatement stmt = conn
			.prepareStatement("DELETE FROM STUDENT WHERE ROLLNO=?");
	stmt.setString(1, dto.getUsername());
	int i = stmt.executeUpdate();
	PreparedStatement stmt2 = conn
	.prepareStatement("DELETE FROM USER WHERE USERNAME=?");
		stmt2.setString(1, dto.getUsername());
		i = stmt2.executeUpdate();
		stmt2.close();
	stmt.close();
	conn.close();
}
public void RemoveUser(FacultyDTO st)throws Exception
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/registration", "root", "root");
	PreparedStatement stmt = conn
			.prepareStatement("DELETE FROM FACULTY WHERE USERNAME=?");
	stmt.setString(1, st.getUsername());
	int i = stmt.executeUpdate();
	PreparedStatement stmt2 = conn
	.prepareStatement("DELETE FROM USER WHERE USERNAME=?");
		stmt2.setString(1, st.getUsername());
		i = stmt2.executeUpdate();
		stmt2.close();
	stmt.close();
	conn.close();
}
public void RemoveUser(StaffDTO st)throws Exception  //Removal is done through the staff id
{
	String id=st.getId();
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/registration", "root", "root");
	PreparedStatement stmt = conn
			.prepareStatement("DELETE FROM STAFF WHERE ID=?");
	stmt.setString(1, id);
	int i = stmt.executeUpdate();
	
	stmt.close();
	conn.close();
}
//public static void main(String[] args) throws Exception {
//	StaffDAO dao=new StaffDAO();
//	UserDTO d=new StudentDTO("UG201310008","Arnav","arnav","cs",1,0);
//	dao.AddUser(d);
//}
}

//update everything
//all functionalities except insertion of courses remaining




