<%@page import="DTO.StudentDTO"%>
<%@page import="DAO.StudentDAO"%>
<html>
	<head><link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  
  </head>
	<style>
	<link href='https://fonts.googleapis.com/css?family=Neucha' rel='stylesheet' type='text/css'>
  #navigation {
position: fixed;
top: 0;
width: 100%;
color: #ffffff;
height: 35px;
text-align: left;
padding-top: 15px;
/* Adds shadow to the bottom of the bar */
-webkit-box-shadow: 0px 0px 8px 0px #000000;
-moz-box-shadow: 0px 0px 8px 0px #000000;
box-shadow: 0px 0px 8px 0px #000000;
/* Adds the transparent background */
background-color: rgba(1, 1, 1, 0.8);
color: rgba(1, 1, 1, 0.8);
}
#navigation a {
font-family: monospace;
font-size: 14px;
padding-left: 15px;
padding-right: 15px;
color: white;
text-decoration: none;
}

#navigation a:hover {
color: grey;
} 
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
  padding: 45px;
  text-align: center;
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
  background: #4CAF50;
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
  background: #43A047;
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
	<body>
	<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  if(session.getAttribute("user")==null )
      response.sendRedirect("Login.jsp");

  %>
	<%
				StudentDTO dto = (StudentDTO) session.getAttribute("user");//change here dto to user
				String erMsg = (String) request.getAttribute("error");
				//String s=(String)request.getAttribute("name");
				if (dto == null) { 
					dto = new StudentDTO();
					RequestDispatcher dispatcher = request
			.getRequestDispatcher("Login.jsp");
		dispatcher.forward(request, response);
				}
				if (erMsg != null) {
			%>
			<%=erMsg%>
			<%
				}
			%>
	<div align="right">
			<a href="ChangePass.jsp?username=<%=dto.getUsername()%>" class="btn btn-sm btn-warning" style="margin-top:5px;margin-left:2px">Change Password</a>&nbsp
			<a href="LoginCTL?operation=Logout" class="btn btn-sm btn-warning" style="margin-top:5px;margin-right:5px">Logout</a>
   			<center>
	<h1>Registration Page</h1></center>
	
   </div>
	
	
  
			<br><br>
			<center><font size="5" style="margin-bottom:20px"><%="Welcome"+" "+dto.getName() %>
			<br><%=dto.getUsername() %>
			<br><%
				StudentDAO dao=new StudentDAO();
				if(dao.is_submitted(dto)==0){
			 %><br>
			<a href="StudentCTL?login=<%=dto.getUsername() %> " style="margin-left:10px">Select Courses</a><br>
			<br><a href="#" onclick="return false;" style="margin-left:10px">Pay Fees</a><br>
			
			<%}
				else{%><%="Your response has been submitted" %><% 
					
				}
			%><br><%
				if(dao.get_approval_status(dto)==0){%><%="Your application has not yet been approved." %>
				<%}
				else if(dao.get_approval_status(dto)==1){%><font color="green"><%="Your application has been approved." %></font>
			 <%}
			 else{%><font color="red"><%="Your application has been disapproved." %></font>
			<%} %>
			</center>
				
</body>
</html>