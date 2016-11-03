package DTO;

public class FacultyDTO extends UserDTO{
	private String name;
	private String branch;
	private int  semester;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FacultyDTO() {
		super();
		name="";
		branch="";
		semester=0;
		// TODO Auto-generated constructor stub
	}
	public FacultyDTO(String username, String password, String role,String name,String branch,int semester) {
		super(username, password, role);
		// TODO Auto-generated constructor stub
		this.name=name;
		this.branch=branch;
		this.semester=semester;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
}
