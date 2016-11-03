package DTO;

//import UserDTO;


public class StaffDTO extends UserDTO {
	private String name;
	private String id;
	public StaffDTO()
	{
		name="";
		id="";
	}
	public StaffDTO(String username,String password,String name,String id)
	{
		super(username,password,"staff");
		this.name=name;
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
