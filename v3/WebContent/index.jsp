<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="controller.JpaController"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Практика</title>
<style>
		body{background:#4682B4;margin:0;}
		iframe{border:0;}
		p{font: Italic Bold 14pt Verdana; }
		#header {height:10%; width:100%; position: absolute; top:0%; left:0%; 
		text-align:center;  background:#a8bcda; }
		
		[name=forMenu] {width:26%; height:45%; position: absolute; top:0%; left:0%;
		 background:#778899; border: 2px solid grey;}
		 
		[name=forTable] {width:70%; height:45%; position: absolute; top:0%; left:28%; }
		
		#forTable{text-align:center;border: 2px solid grey;}
		
		[name=forOperation] {width:99%; height:9%; position: absolute; top:55%; left:0%;
		background:#accadd; border:2px solid grey}
		
		[name=forDialog] {width:96%; height:36%; position: absolute; top:64%; left:2%; 
		 }
</style>
</head>
<body>

		<iframe name="forMenu" src="menuTable.html" ></iframe>
	
	
	<div id='forTable'>
		<iframe name="forTable"> </iframe>
	</div>
	
	<div id='forOperation'>
		<iframe name="forOperation" src="menuOperation.html"> </iframe>
	 </div>
	 
	 <div id='forDialog'>
		<iframe name="forDialog" > </iframe>
	</div>
</body>
</html>
<%
				session.setAttribute("controller", new JpaController());
		%>
