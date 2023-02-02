<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String uname = request.getParameter("uname");
String upass = request.getParameter("upass");


try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/phase3", "root","root");
	Statement stmt = con.createStatement();
	ResultSet rs=stmt.executeQuery("Select * from admin");
	while(rs.next()){
		if(uname.equalsIgnoreCase(rs.getString(1)) && upass.equalsIgnoreCase(rs.getString(2)))
		{
			System.out.println("This is working");
			response.sendRedirect("AdminLogin.html");
			
		}else{
			System.out.println("This is NOT working");
			response.sendRedirect("AdminHome.html");
		}
	}
}
catch(Exception e) {
	System.out.println(e);
}
%>
</body>
</html>