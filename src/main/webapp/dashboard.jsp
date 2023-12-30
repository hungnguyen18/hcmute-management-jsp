<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Dao.*"%>
<%@ page import="Bean.UserBean"%>
<%@ page import="Util.UserSessionUtil"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%UserBean user = UserSessionUtil.getUser(request);%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<layout:extends name="base">
		<layout:put block="contents">
			<h5>Dashboard</h5>

			<h1>User Profile</h1>


			<c:if test="${not empty user}">
				<p>
					<strong>User ID:</strong> ${user.user_ID}
				</p>
				<p>
					<strong>Username:</strong> ${user.username}
				</p>
				<p>
					<strong>Email:</strong> ${user.email}
				</p>
				<p>
					<strong>Phone Number:</strong> ${user.phone_number}
				</p>
				<p>
					<strong>Role:</strong> ${user.role}
				</p>
			</c:if>


			<c:if test="${empty user}">
				<p>Please log in to view your profile.</p>
			</c:if>

		</layout:put>
	</layout:extends>
</body>
</html>