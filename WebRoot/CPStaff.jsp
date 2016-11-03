 <%@page import="DTO.UserDTO"%>
<%@page import="DAO.UserDAO"%>
 <html>
	<head>
	<title>
	Change Password
	</title><link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  
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
font-family: "Roboto", sans-serif;
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
body{
	background: #E9EAED; /* fallback for old browsers */
  	-webkit-font-smoothing: antialiased;
  	-moz-osx-font-smoothing: grayscale;      
	}
 </style>
  
 
  <script type="text/javascript">
		function vali(frm){
	
		var login = frm.old.value;
		var password = frm.new.value;
		var flag = true;
		
		if(login == null || login == ''){
			document.getElementById("old").innerHTML="Old Password cannot be null.";
			flag = false;
		}else{
			document.getElementById("old").innerHTML="";
		}
		if(password == null || password == ''){
			document.getElementById("new").innerHTML="New Password cannot be null.";
			flag = false;
		}else{
			document.getElementById("new").innerHTML="";
		}
		return flag;
		}
		</script>
		<%
  

  if(session.getAttribute("user")==null){
  	response.setHeader("Cache-Control","no-cache");
  	response.setHeader("Cache-Control","no-store");
  	response.setHeader("Pragma","no-cache");
  	response.setDateHeader ("Expires", 0);
  	response.sendRedirect("Login.jsp");
  }

  %>
	<!-- 	<div align="right">
			<a href="LoginCTL?operation=Logout" class="btn btn-sm btn-warning" style="margin-top:5px;margin-right:5px">Logout</a>
   			</div> -->
		<center>
			<header class="w3-container w3-teal">
  			<h1 style="margin-top:50px;color:286090">
				Change Password
				
			</h1>
			
		</header>
		</center>
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
  
		
		<%		
				UserDTO dto = (UserDTO) session.getAttribute("user");
				String good=(String) request.getAttribute("good");
				String erMsg = (String) request.getAttribute("error");
				if (erMsg != null) {
			%>
			<center><font color="red"><%=erMsg%></font></center>
				<%
				}
			%>
			<% if (good != null) {
			%>
			<center><font color="green"><%=good%></font></center>
			<%
				}
			%>
			<font align="center"><center>Logged in as:<%=dto.getUsername() %></center>
			<div class="login-page">
  			<div class="form" style="margin-top:auto">  
  			
  			 <form class="login-form" action="ChangePass" method="post" onsubmit="return vali(this)">
				<input type="password" name="oldpass" placeholder="Old Password">
				 <div id="old"></div>
				<input type="password" name="newpass" placeholder="New Password">
				 <div id="new"></div>
				<button value="update" name="operation">Change</button>
			 </form>
			</div>
			</div>
		</body>
		</html>