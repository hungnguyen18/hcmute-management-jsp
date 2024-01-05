<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Bean.MajorBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Majors</title>
</head>
<body>
	<h2>List of Majors</h2>
	<table border="1">
		<thead>
			<tr>
				<th>Major Code</th>
				<th>Major Name</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="major" items="${majors}">
				<tr>
					<td>${major.majorCode}</td>
					<td>${major.majorName}</td>
					<td><a
						href="MajorController?action=edit&majorID=${major.majorID}">Edit</a>
						<a href="MajorController?action=delete&majorID=${major.majorID}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<a href="add_major.jsp">Add Major</a>
</body>
</html>
