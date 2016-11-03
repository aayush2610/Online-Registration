
public class CourseDTO {
	private String branch;
	private int semester;
	private String course_id;
	private String course_name;
	private String course_instructor;
	private boolean elective;
	private int credits;
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
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_instructor() {
		return course_instructor;
	}
	public void setCourse_instructor(String course_instructor) {
		this.course_instructor = course_instructor;
	}
	public boolean isElective() {
		return elective;
	}
	public void setElective(boolean elective) {
		this.elective = elective;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}

}
