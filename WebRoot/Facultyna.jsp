<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="DTO.FacultyDTO"%>
<%@page import="DAO.FacultyDAO"%>
<%@page import="DTO.StudentDTO"%>
<%@page import="DAO.StudentDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>New Applications</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style>

    .row.content {height: 1500px}
    
    
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
     
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;} 
    }
  </style>
</head>
	

  <style>
	<link href='https://fonts.googleapis.com/css?family=Neucha' rel='stylesheet' type='text/css'>

body {
  background: #9494b8; /* fallback for old browsers */
  background: -webkit-linear-gradient(right, #76b852, #8DC26F);
  background: -moz-linear-gradient(right, #76b852, #8DC26F);
  background: -o-linear-gradient(right, #76b852, #8DC26F);
  background: linear-gradient(to left, #76b852, #8DC26F);
  font-family: "Roboto", sans-serif;
  color:#666699;
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

  if(session.getAttribute("user")==null)
      response.sendRedirect("Login.jsp");

  %>
  <%
				FacultyDTO Dto = (FacultyDTO) session.getAttribute("user");//change here dto to user
				String erMsg = (String) request.getAttribute("error");
				//String s=(String)request.getAttribute("name");
				if (Dto == null) { 
					Dto = new FacultyDTO();
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
  
    
  <div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
      <h4><%="Welcome"+" "+Dto.getName() %></h4>
      <form class="login-form" action="FacultyCTL">
   	<input type="hidden" name="dto" value=<%=Dto.getUsername() %>>
        
        <a href="Faculty.jsp"><button name="option" type="button" style="width:100%; margin:5px;color:black" class="btn" value="">Home</button></a>
        <button name="option" type="submit" style="width:100%; margin:5px" class="btn " value="approved_students">Approved Students</button>
        <button name="option" type="submit" style="width:100%; margin:5px" class="btn" value="disapproved_students">Disapproved Students</button>
        <button name="option" type="submit" style="width:100%; margin:5px" class="btn btn-primary" value="new">New Applications</button>
      </form>
</div>
<div class="col-sm-9">
<div align="right">
	<a href="ChangePass.jsp?username=<%=Dto.getUsername()%>" class="btn btn-sm btn-warning" style="margin-top:5px;margin-left:2px">Change Password</a>&nbsp
   	<a href="LoginCTL?operation=Logout" class="btn btn-sm btn-warning" style="margin-top:5px">Logout</a>
   </div>
   
   <center><h1>New Applications</h1></center>
    		<form action="FacultyCTL" method="post">
     <%
    	ArrayList<String> arr=(ArrayList<String>)request.getAttribute("arr");
     	//System.out.println(arr.size());
     	if(arr.size()==0){%><%="No new Applications" %> <%}
     	else{
     	 %>
     <table class="table table-striped table-hover">
    <thead>
      <tr>
        <th>S no.</th>
        <th>Roll Number</th>
        <th>Name</th>
        <th>Not Now</th>
        <th>Approve</th>
        <th>Disapprove</th>
      </tr>
    </thead>
    <tbody>
    <%
    	int i=0; 
    	while(i<arr.size()){
    	StudentDAO dao=new StudentDAO();
    	StudentDTO dto=dao.get(arr.get(i));
    	i++;
     %>
     <tr>
     	<td><%=i+"." %></td>
     	<td><a href="StudFac?id=<%=dto.getUsername() %>"><%=dto.getUsername() %></a></td>
     	<td><input type="hidden" name="dto" value=<%=dto.getUsername() %>><%=dto.getName() %></td>
     	<td><input type="radio" name=<%=i+"_ad" %> checked="checked" value="none"></td>
     	<td><input type="radio" name=<%=i+"_ad" %> value=<%=dto.getUsername()+"_approve"%>></td>
     	<td><input type="radio" name=<%=i+"_ad" %> value=<%=dto.getUsername()+"_disapprove" %>></td>
      </tr>
       <%} %>
    </tbody>
  </table>
    
    
     <br>
     
     <input class ="btn btn-success" type="submit" name="operation" value="Save" style="float:right">
     <%} %>
     </form>
      </div>
  </body>
</html>
