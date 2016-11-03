package DTO;

public class CourseDTO {
///private String branch;
//private int semester;
private String id;
private String name;
private String instructor;
private int credits;
private boolean iselective;
public CourseDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public CourseDTO(String branch, int semester, String id, String name,
		String instructor, int credits, boolean iselective) {
	super();
	//this.branch = branch;
	//this.semester = semester;
	this.id = id;
	this.name = name;
	this.instructor = instructor;
	this.credits = credits;
	this.iselective = iselective;
}
//public String getBranch() {
//	//return branch;
//}
//public void setBranch(String branch) {
//	this.branch = branch;
//}
//public int getSemester() {
//	return semester;
//}
//public void setSemester(int semester) {
//	this.semester = semester;
//}
public String toString()
{
	return id+" "+name+" "+instructor;
	
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getInstructor() {
	return instructor;
}
public void setInstructor(String instructor) {
	this.instructor = instructor;
}
public int getCredits() {
	return credits;
}
public void setCredits(int credits) {
	this.credits = credits;
}
public boolean isIselective() {
	return iselective;
}
public void setIselective(boolean iselective) {
	this.iselective = iselective;
}
}
