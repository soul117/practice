<%@page import="javax.swing.table.TableModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>showTable</title>
<style type="text/css"> 
		table{
			 border-collapse:collapse; border:3px solid black;
			 background:#00ffa3}
			 td{padding:5px;spacing:40px;border:2px solid black}
			 th{border:2px solid black;}
	 	p{font: bold 14pt Verdana;}
</style>
</head>
<body>
	<%
		String className = (String)( session.getAttribute("className"));
		TableModel model = (TableModel) (session.getAttribute("tableModel"));
	%>
	
	<div align='center'>
		<p>Таблиця <%=className%></p>
		<table >
			<tr>
				<%
					int nCol = model.getColumnCount();
					for (int i = 0; i < nCol; i++) {
						String h = model.getColumnName(i);
				%>
				<th><%=h%></th>
				<% } %>
			</tr>
			<%
				int nRow = model.getRowCount();
				for (int r = 0; r < nRow; r++) {
			%>
			<tr>
				<%
					for (int j = 0; j < nCol; j++) {
							String str = model.getValueAt(r, j).toString();
				%>
				<td align="center"> <%=str%></td>
				 <% } %>
			</tr>
			<% } %>
		</table>
		</div>
</body>
</html>
