<%@page import="DTO.StudentDTO"%>
<%@page import="DTO.CourseDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<html><head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  
<style>
	<link href='https://fonts.googleapis.com/css?family=Neucha' rel='stylesheet' type='text/css'>
.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}
.form {
  position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 0px;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #286090;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form select  {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  padding: 15px;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form button:hover,.form button:active,.form button:focus {
  background: #1a8cff;
}
.form .message {
  margin: 15px 0 0;
  color: #b3b3b3;
  font-size: 12px;
}
.form .message a {
  color: #4CAF50;
  text-decoration: none;
}
.form .register-form {
  display: none;
}
.container {
  position: relative;
  z-index: 1;
  max-width: 300px;
  margin: 0 auto;
}
.container:before, .container:after {
  content: "";
  display: block;
  clear: both;
}
.container .info {
  margin: 50px auto;
  text-align: center;
}
.container .info h1 {
  margin: 0 0 15px;
  padding: 0;
  font-size: 36px;
  font-weight: 300;
  color: #1a1a1a;
}
.container .info span {
  color: #666699;
  font-size: 12px;
}
.container .info span a {
  color: #666699;
  text-decoration: none;
}
.container .info span .fa {
  color: #EF3B3A;
}
body {
  background: #E9EAED; /* fallback for old browsers */
 
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}
	</style>
  </head>
  <script>
	function validateForm() {
    var x = document.forms["aaa"]["ecourse_1"].value;
     var y = document.forms["aaa"]["ecourse_2"].value;
    if (x == y) {
         document.getElementById("demo").innerHTML = "Please select different Electives";
        return false;
    }
}
</script>
  <body>
   <%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  if(session.getAttribute("user")==null)
      response.sendRedirect("Login.jsp");

  %>
  <%
				StudentDTO Dto = (StudentDTO) session.getAttribute("user");//change here dto to user
				String rMsg = (String) request.getAttribute("error");
				//String s=(String)request.getAttribute("name");
				if (Dto == null) { 
					Dto = new StudentDTO();
					RequestDispatcher dispatcher = request
			.getRequestDispatcher("Login.jsp");
		dispatcher.forward(request, response);
				}
				if (rMsg != null) {
			%>
			<%=rMsg%>
			<%
				}
			%>
  <div align="right">
   	<a href="LoginCTL?operation=Logout" class="btn btn-sm btn-warning" style="margin-top:5px">Logout</a>
   </div>
   
  <%
				StudentDTO dto = (StudentDTO) session.getAttribute("user");//change here dto to user
				String erMsg = (String) request.getAttribute("error");
				String branch=dto.getBranch();
				if(branch.equals("cs"))
					branch="Computer Science";
					else if(branch.equals("me"))
						branch="Mechanical Engineering";
					else if(branch.equals("ee"))
						branch="Electrical Engineering";	
				
				
				if (dto == null) { 
					dto = new StudentDTO();
				}
				if (erMsg != null) {
			%>
			<%=erMsg%>
			<%
				}
			%><br>
			<div align="center">
			<font size="4"><%="Name: "+dto.getName() %><br>
			<%="Roll Number: "+dto.getUsername() %><br>
			<%="Discipline: "+branch%><br>
			<%="Semester: "+dto.getSemester() %></font>
			<br>
  			
			<form action="StudentCTL" method="post" onsubmit="return validateForm()" name="aaa">
			<h3><div align="center">Compulsory Courses</div></h3>
				<table class="table table-striped table-hover">
    <thead>
      <tr>
        <th>S No.</th>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>Instructor</th>
        <th>Credits</th>
      </tr>
    </thead>
				<tbody>
				<%
				ArrayList compulsory_courses=(ArrayList)request.getAttribute("arr");
				Iterator itcc= compulsory_courses.iterator();
				int i=1;
				while (itcc.hasNext()) {
					CourseDTO comp_course = (CourseDTO) itcc.next();
				%>
     		<tr>
     				<td><%=i+"." %></td>
					<td><%=comp_course.getId() %></td>
					<td><%=comp_course.getName() %></td>
					<td><%=comp_course.getInstructor() %></td>
					<td><%=comp_course.getCredits() %></td>
			</tr>
			<%
				i++;
				} %>
    </tbody>
				
				
				</table>
				<h3><div align="center">Elective Courses</div></h3><br>
				<div class="form" align="left" style="margin-bottom: 10px;">
				<input type="hidden" name="dto" value=<%=dto.getUsername() %>>
				<select name="ecourse_1" >
				<option value="none">None</option>
				<%
				ArrayList elective_courses=(ArrayList)request.getAttribute("arr_elective");
				Iterator itec=elective_courses.iterator();
				while(itec.hasNext()){
					CourseDTO elec_course = (CourseDTO) itec.next();
				 %>
				<option value=<%=elec_course.getId()%>><%=elec_course.getId()%>&nbsp&nbsp&nbsp<%=elec_course.getName() %></option>
				<%} %>
				</select><br>
				<select name="ecourse_2" >
				<option value="none">None</option>
				<%
				ArrayList elective_courses1=(ArrayList)request.getAttribute("arr_elective");
				Iterator itec1=elective_courses.iterator();
				while(itec1.hasNext()){
					CourseDTO elec_course = (CourseDTO) itec1.next();
				 %>
				<option value=<%=elec_course.getId()%>><%=elec_course.getId()%>&nbsp&nbsp&nbsp<%=elec_course.getName() %></option>
				<%} %>
				</select>
				
				
				</div>
				<p style="color:red" id="demo"></p>
				<div class="form">
				<button class="btn btn-success" value="submit" name="operation" >Submit</button>
				</div>
			</form>
			
</body>    
</html>
