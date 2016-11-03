package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.FacultyDTO;
import DTO.StudentDTO;

public class FacultyDAO extends UserDAO{
	public void approve(StudentDTO dto) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE "+"f"+"_"+dto.getBranch()+"_"+Integer.toString(dto.getSemester())+" SET APPROVE=?, DISAPPROVE=? WHERE ROLLNO=?");
		stmt.setBoolean(1,true);
		stmt.setBoolean(2,false);
		stmt.setString(3, dto.getUsername());
		int i=stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	public void disapprove(StudentDTO dto) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE "+"f"+"_"+dto.getBranch()+"_"+Integer.toString(dto.getSemester())+" SET DISAPPROVE=?, APPROVE=? WHERE ROLLNO=?");
		stmt.setBoolean(1,true);
		stmt.setBoolean(2, false);
		stmt.setString(3, dto.getUsername());
		int i=stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	public FacultyDTO get(String username) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM FACULTY WHERE USERNAME=?");
		
		stmt.setString(1, username);
		ResultSet resultSet=stmt.executeQuery();
		PreparedStatement stmt2 = conn
		.prepareStatement("SELECT * FROM USER WHERE USERNAME=?");
		stmt2.setString(1, username);
		ResultSet result=stmt2.executeQuery();
		FacultyDTO dto=new FacultyDTO();
		if(resultSet.next() && result.next())
		dto=new FacultyDTO(result.getString(1),result.getString(2),result.getString(3),resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3));
		
		stmt.close();
		conn.close();
		return dto;
	}
	public FacultyDTO getbs(String branch,int sem) throws Exception{//get faculty from branch and semester
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM FACULTY WHERE BRANCH=? AND SEMESTER=?");
		
		stmt.setString(1, branch);
		stmt.setInt(2, sem);
		ResultSet resultSet=stmt.executeQuery();
		
		FacultyDTO dto=new FacultyDTO();
		if(resultSet.next()){
			dto.setName(resultSet.getString(1));
			dto.setBranch(branch);
			dto.setSemester(sem);
			dto.setUsername(resultSet.getString(4));
		}
		stmt.close();
		conn.close();
		return dto;
	}
	public ArrayList<String> approved_students(String branch, int sem) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT ROLLNO FROM "+"f"+"_"+branch+"_"+Integer.toString(sem)+" WHERE APPROVE=? ");
		stmt.setBoolean(1, true);
		ResultSet resultSet=stmt.executeQuery();
		ArrayList<String> arr=new ArrayList<String>();
		while(resultSet.next()){
			arr.add(resultSet.getString(1));
			//System.out.println(resultSet.getString(1));
		}
		stmt.close();
		conn.close();
		return arr;
	}
	public ArrayList<String> disapproved_students(String branch, int sem) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT ROLLNO FROM "+"f"+"_"+branch+"_"+Integer.toString(sem)+" WHERE DISAPPROVE=? ");
		stmt.setBoolean(1, true);
		ResultSet resultSet=stmt.executeQuery();
		ArrayList<String> arr=new ArrayList<String>();
		while(resultSet.next()){
			arr.add(resultSet.getString(1));
		}
		stmt.close();
		conn.close();
		return arr;
	}
	public ArrayList<String> noaction_students(String branch, int sem) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT ROLLNO FROM "+"f"+"_"+branch+"_"+Integer.toString(sem)+" WHERE APPROVE=? AND DISAPPROVE=? ");
		stmt.setBoolean(1, false);
		stmt.setBoolean(2, false);
		ResultSet resultSet=stmt.executeQuery();
		ArrayList<String> arr=new ArrayList<String>();
		while(resultSet.next()){
			arr.add(resultSet.getString(1));
		}
		stmt.close();
		conn.close();
		return arr;
	}
	public void save(FacultyDTO dto) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM "+"f"+"_"+dto.getBranch()+"_"+Integer.toString(dto.getSemester())+"");
		ResultSet resultSet = stmt.executeQuery();
		while(resultSet.next()){
			String roll=resultSet.getString(1);
			boolean ap=resultSet.getBoolean(2);
			boolean dis=resultSet.getBoolean(3);
			if(ap==true){
				PreparedStatement stmt1 = conn
				.prepareStatement("UPDATE STUDENT SET APPROVAL_STATUS=? WHERE ROLLNO =?");
				stmt1.setInt(1, 1);
				stmt1.setString(2, roll);
				int i=stmt1.executeUpdate();
				stmt1.close();
			}
			else if (dis==true)
			{
				PreparedStatement stmt1 = conn
				.prepareStatement("UPDATE STUDENT SET APPROVAL_STATUS=? WHERE ROLLNO =?");
				stmt1.setInt(1, -1);
				stmt1.setString(2, roll);
				int i=stmt1.executeUpdate();
				stmt1.close();
			}
			else
			{
				//Data from the faculty isn't removed because the data of students should be made available to the faculty for future reference.
			}
		}
		conn.close();
	}
//	public static void main(String[] args) throws Exception {
//		FacultyDAO dao=new FacultyDAO();
//		FacultyDTO dto=dao.get("ra");
//		System.out.println(dto.getName());
//	}
}
