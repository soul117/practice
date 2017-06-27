<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body{text-align:center;}
div{ height:98%; width:100%}
table{padding: 5px; 
    width: 40%; /* Ширина таблицы */
    margin: auto; /* Выравниваем таблицу по центру окна  */}
    form 
    {
    border: 1px solid green;
    width: 55%;
    height: 70%;
    position: relative;
    top: 10%;
    left: 25%;
}
</style>
<script>
		function clear_form()
		{
			var form = document.forms[0];
			var field = form.elements.orderRoom;
			field.value='';
			
			field = form.elements.orderName;
			field.value='';
			
			field = form.elements.orderDate;
			field.value='';
		}

</script>

</head>
<body>
<% 
String roomId = (String)request.getAttribute("Room");

%>
<div>
		<form action="OperateTable" target="forTable" method="POST" onreset=clear_form()>
		<p>та заповніть поля:</p>
	<table>
		<tr><td>
			Номер кімнати </td> <td><input type="text" name="orderRoom"  
			value='<%=roomId %>'>
		</td></tr>
		<tr>
			<td>Ім'я кліента</td><td><input type="text" name="orderName" required></td>
		</tr>
		<tr>
			<td>Дата поселення</td><td><input type="date"  name="orderDate" required></td>
		</tr>
		</table><br>
		<input type="submit" value="Передати">
		<input type="button" value="Очистити" onclick=clear_form()>
		<br>
	</form>
	</div>	
</body>
</html>