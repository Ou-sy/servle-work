<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import=" java.sql.DriverManager"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	 Connection conn;
	Statement statement = null;
	ResultSet rs = null;
	
    String urlstr = "jdbc:sqlserver://localhost:1433;databaseName=Servlet_HW;user=sa;password=passw0rd;";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	
		
	%>
	<h2 align="center">
		<font><strong>Retrieve data from database in jsp</strong></font>
	</h2>
	<table align="center" cellpadding="5" cellspacing="5" border="1">
		<tr>

		</tr>
		<tr bgcolor="#A52A2A">
			<td><b>userId</b></td>
			<td><b>userName</b></td>
			<td><b>userAddress</b></td>
			<td><b>userPhone</b></td>

		</tr>
		<%
			try {
				conn = DriverManager.getConnection(urlstr);
				statement = conn.createStatement();
				String sql = "SELECT * FROM Member";

				rs = statement.executeQuery(sql);
				while (rs.next()) {
		%>
		<tr bgcolor="#DEB887">

			<td><%=rs.getString("Book_ISBN")%></td>
			<td><%=rs.getString("product_name")%></td>
			<td><%=rs.getString("Book_name_zh")%></td>
			<td><%=rs.getString("Book_name_ori")%></td>

		</tr>

		<%
			}

			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</table>

</body>
</html>