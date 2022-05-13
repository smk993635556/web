<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sevlets.login"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mysql.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <br><br><br><br><br><br><br><br>
	<table border="1" align="center">
		<tr>
			<th  width="87" align="center">姓名</th>
			<th  width="87" align="center">密码</th>
			<th  width="87" align="center">年龄</th>
			<th  width="87" align="center">性别</th>
			<th  width="87" align="center">班级</th>
		</tr>
		<%
			ArrayList loginlist = (ArrayList)session.getAttribute("loginlist");
			if(loginlist!=null){
			for (int i = 0; i < loginlist.size(); i++) {
				login login =  (login)loginlist.get(i);	
		%>
		<tr>
			<td align="center"><%=login.getName()%></td>
			<td align="center"><%=login.getPassword()%></td>
				<td align="center"><%=login.getSex()%></td>
				<td align="center"><%=login.getAge()%></td>
				<td align="center"><%=login.getNclass()%></td>
				
		</tr>
		<%
			}
			}
		%>
	</table>
  </body>
</html>
