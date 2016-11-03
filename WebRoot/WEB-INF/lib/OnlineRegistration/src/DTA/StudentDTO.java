package DTA;

//import UserDTO;

public class StudentDTO extends UserDTO {
	//private String password = "";
	private String name="";
	private String branch="";
	private int semester;
	private int approval_status;
	public StudentDTO()
	{
		super();
		name="";
		branch="";
		semester=0;
		approval_status=0;
	}
	public StudentDTO(String name,String password,String branch,int semester,int status)
	{
		super(name,password,"student");
		this.name=name;
		this.branch=branch;
		this.semester=semester;
		approval_status=status;
	}
//	public StudentDTO(String name,String branch,int semester)
//	{
//		super(name,,"student");
//		this.name=name;
//		this.branch=branch;
//		this.semester=semester;
//		approval_status=false;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int isApproval_status() {
		return approval_status;
	}
	public void setApproval_status(int approval_status) {
		this.approval_status = approval_status;
	}
	
}
