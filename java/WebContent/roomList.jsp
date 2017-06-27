<%@page import="webview.SelectTable"%>
<%@page import="model.Room"%>
<%@page import="controller.JpaController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RoomList</title>
<style type="text/css">
	select{text-align:center;width:40%;height:80%;}
	div{text-align:center;width:30%;height:98%;}
	iframe{position:absolute; left:30%; top:0%; height:98%; width:68%; border:0;}
</style>
</head>
<body>
		<div>
		<p>Щоб додати запис в таблицю "Клієнти" виберіть кімнату зі списку:<p>
		 <form action='GetRoomId' method="post" target=inputOrder>
		 <select name="RoomId" size='7' required autofocus>
			<%
			JpaController controller = 
			(JpaController) session.getAttribute("controller");

				Class<Room> c = Room.class;
				for (Object x : controller.getObjectList(c)) {
					Room obj = (Room) x;
			%>
			<option>
				<%=obj.toString()%></option>
			<%
				}
			%>
		</select><br>
		<input type='submit' value='Вибрати'>
		</form>
		</div>
			<iframe name="inputOrder" > </iframe>
</body>
</html>
