package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

	import DTA.UserDTO;
	import DTA.StudentDTO;
public class UserDAO {
	public UserDTO authenticate(String login, String password, String role) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/registration", "root", "root");
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM LOGIN WHERE USERNAME = ? , PASSWORD = ? AND ROLE=?");
		stmt.setString(1, login);
		stmt.setString(2, password);
		stmt.setString(3, role);
		ResultSet resultSet = stmt.executeQuery();
		UserDTO dto = null;

		if (resultSet.next()) {
			if(role=="Student"){
				PreparedStatement stmt2 = conn
				.prepareStatement("SELECT * FROM STUDENT WHERE ROLLNO = ?");
				stmt.setString(1, login);
				ResultSet rs=stmt2.executeQuery();
				if(rs.next()){
					dto=new StudentDTO(rs.getString(4),password,rs.getString(1),rs.getInt(2),rs.getInt(5));
					
				}
			}
		}
		stmt.close();
		conn.close();
		return dto;
	}
	public boolean change_password(UserDTO dto,String old_pass,String new_pass ) throws Exception{
		if(!dto.getPassword().equals(old_pass)){
			return false;
		}
		else{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/registration", "root", "root");
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE LOGIN SET PASSWORD=? WHERE USERNAME=?");
			stmt.setString(1, new_pass);
			stmt.setString(2, dto.getUsername());
			stmt.close();
			conn.close();
			return true;
		}
		
	}
	public static void main(String[] args) throws Exception{
		
		
		UserDAO dao=new UserDAO();
		System.out.println("ds");
		UserDTO dto=dao.authenticate("Avan", "123", "student");
		if(dto!=null)
			System.out.println("Success");
	}
}
