 <%@page import="DTO.StaffDTO"%>
<%@page import="DAO.StaffDAO"%>
  <html>
	<head><title>Add Staff</title><link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>  
  <style>
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
  font-family: "Roboto", sans-serif;
  color:#666699;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}
  </style>
  <center>
  <%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  if(session.getAttribute("user")==null)
      response.sendRedirect("Login.jsp");

  %>
  	<%
				StaffDTO dto = (StaffDTO) session.getAttribute("user");//change here dto to user
				String erMsg = (String) request.getAttribute("error");
				//String s=(String)request.getAttribute("name");
				if (dto == null) { 
					dto = new StaffDTO();
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
			
  			<h1 style="margin-top:50px;color:286090">
				Staff Management
			</h1>
	
  
  <script type="text/javascript">
		function vali(frm){
	
		var login = frm.username.value;
		var password = frm.name.value;
		var flag = true;
		
		if(login == null || login == ''){
			document.getElementById("userId").innerHTML="Username cannot be null.";
			flag = false;
		}else{
			document.getElementById("userId").innerHTML="";
		}
		if(password == null || password == ''){
			document.getElementById("nameId").innerHTML="Name cannot be null.";
			flag = false;
		}else{
			document.getElementById("nameId").innerHTML="";
		}
		return flag;
		}
		</script>
  <body>
  <div id="navigation">
  	<a href="Staff.jsp">Home</a>
  	<a href="StudStaff.jsp">Student</a>
  	<a href="FacStaff.jsp">Faculty</a>
  	<a href="CourStaff.jsp">Courses</a>
  	<a href="StaStaff.jsp">Staff</a>
  	<a href="CPStaff.jsp">Change Password</a>
  	<a href="LoginCTL?operation=Logout">Logout</a>
  </div>
	
	<center><%String errhan=(String)request.getAttribute("errhan");
				String errha=(String)request.getAttribute("errha");
				if (errha != null) {
			%>
			<font color="red"><%=errha%></font>
			<%
				}else if(errhan!=null){%>
			<font color="red"><%=errhan%></font>
			<%}
			%>
				
				 </center>
	<div class="login-page">
  		<div class="form" style="margin-top:auto">  
  			 <form class="login-form" action="StaStaff" method="post" name="frm" method="post">
  			 	  <input type="text" placeholder="Username" name="username"/>
  			 	  <div id="userId"></div>
  			 	  <input type="text" placeholder="Name" name="name"/>
  			 	  <div id="nameId"></div>
  			 	  <input type="text" placeholder="ID" name="id"/>
  			 	  <div id="Id"></div>
  			 	   
				   <br><br>
				   <table>
				   <tr>
				   <td> <button value="add" name="operation" onclick="return vali(frm)" class="btn btn-block">Add</button></td>
				   </tr>
				   </table>
				   </form>
				   </div>
				   </div>
  </body>
</html>
