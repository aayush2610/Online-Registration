package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DTO.FacultyDTO;
import DTO.StaffDTO;
import DTO.StudentDTO;
import DTO.UserDTO;
public class UserDAO {
	public UserDTO authenticate(String login, String password, String role) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD=? AND ROLE=?");
		System.out.println(login);
		stmt.setString(1, login);
		stmt.setString(2, password);
		stmt.setString(3, role);
		//System.out.println("da");
		System.out.println(stmt.toString());//("query =", stmt.toString());
		ResultSet resultSet = stmt.executeQuery();
		
		UserDTO dto = null;

		if (resultSet.next()) {
			//System.out.println("ss");
			if(role.equals("student")){
				PreparedStatement stmt2 = conn
				.prepareStatement("SELECT * FROM STUDENT WHERE ROLLNO = ?");
				stmt2.setString(1, login);
				ResultSet rs=stmt2.executeQuery();
				if(rs.next()){
					dto=new StudentDTO(login,rs.getString(4),password,rs.getString(1),rs.getInt(2),rs.getInt(5));
				}
				stmt2.close();
			}
			else if(role.equals("faculty")){
				PreparedStatement stmt2 = conn
				.prepareStatement("SELECT * FROM FACULTY WHERE USERNAME = ?");
				stmt2.setString(1, login);
				ResultSet rs=stmt2.executeQuery();
				if(rs.next()){
					dto=new FacultyDTO(login,password,"faculty",rs.getString(1),rs.getString(2),rs.getInt(3));
				}
				stmt2.close();
			}
			else if(role.equals("staff")){
				PreparedStatement stmt2 = conn
				.prepareStatement("SELECT * FROM STAFF WHERE USERNAME = ?");
				stmt2.setString(1, login);
				ResultSet rs=stmt2.executeQuery();
				if(rs.next()){
					dto=new StaffDTO(login,password,rs.getString(2),rs.getString(1));
				}
				stmt2.close();
			}
		}
		stmt.close();
		conn.close();
		return dto;
	}
	public String getu(String username) throws Exception{//to get password for a given username
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT PASSWORD FROM USER WHERE USERNAME=?");
		stmt.setString(1, username);
		ResultSet resultSet=stmt.executeQuery();
		String s="";
		if(resultSet.next()){
			 s=resultSet.getString(1);
		}
		stmt.close();
		conn.close();
		return s;
	}
	public String getr(String username) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT ROLE FROM USER WHERE USERNAME=?");
		stmt.setString(1, username);
		ResultSet resultSet=stmt.executeQuery();
		String s="";
		if(resultSet.next()){
			 s=resultSet.getString(1);
		}
		stmt.close();
		conn.close();
		return s;
	}
	public boolean change_password(String username,String old_pass,String new_pass ) throws Exception{
		
		UserDAO dao=new UserDAO();
		String s=dao.getu(username);
		if(!s.equals(old_pass)){
			return false;
		}
		else{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/registration", "root", "root");
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE USER SET PASSWORD=? WHERE USERNAME=?");
			stmt.setString(1, new_pass);
			stmt.setString(2, username);
			int i=stmt.executeUpdate();
			stmt.close();
			conn.close();
			return true;
		}
		
	}
//	public static void main(String[] args) throws Exception{
//		
//		
//		UserDAO dao=new UserDAO();
//		UserDTO dto=dao.authenticate("Avan", "paliwalbkl", "student");
//		if(dto!=null)
//			System.out.println("Success1"+dto.getRole());
//		System.out.println(dao.change_password(dto, "123", "paliwalbkl"));
//		
//	}
}
