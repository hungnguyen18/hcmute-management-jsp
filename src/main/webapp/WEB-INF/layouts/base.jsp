<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Bean.UserBean"%>
<%@ page import="Util.UserSessionUtil"%>


<%
UserBean user = UserSessionUtil.getUser(request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>

</head>
<body>

	<div id="wrapper" class="flex justify-stretch ">
		<layout:block name="sidebar">
			<aside class="w-64 bg-[#152259] h-screen text-white divide-y">
				<!-- Logo Section -->
				<div class="h-24 py-4 px-6 flex items-center justify-center">
					<!-- Your Logo Goes Here -->
					<h2>LOGO</h2>
				</div>
				<!-- Navigation Section -->
				<nav>
					<ul class="mt-4">
						<li class="py-2 px-2"><a href="dashboard.jsp"
							class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
								Dashboard </a></li>
						<c:choose>
							<c:when test="${user.role == 'student'}">
								<!-- Sidebar menu for student -->
								<li class="py-2 px-2"><a href="services.jsp"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Services </a></li>
								<!-- Add more services for student as needed -->
							</c:when>
							<c:when test="${user.role == 'ctsv_staff'}">
								<!-- Sidebar menu for CTSV staff -->
								<li class="py-2 px-2"><a href="Requests.jsp"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Request </a></li>
								<!-- Add more services for CTSV staff as needed -->
							</c:when>
							<c:when test="${user.role == 'system_admin'}">
								<!-- Sidebar menu for system admin -->
								<li class="py-2 px-2"><a href="student.jsp"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Student </a></li>

								<li class="py-2 px-2"><a href="ctsv.jsp"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										CTSV staff </a></li>
								<li class="py-2 px-2"><a href="majors.jsp"
									class="block rounded-lg py-2 px-4 text-sm hover:bg-[#509CDB] focus:outline-none focus:bg-[#509CDB]">
										Majors </a></li>
								<!-- Add more management options for system admin as needed -->
							</c:when>
						</c:choose>
					</ul>
				</nav>
			</aside>
		</layout:block>

		<div id="content" class="w-screen ">
			<layout:block name="header">
				<%@ include file="header.jsp"%>
			</layout:block>
			<div class="container mx-auto px-4">
				<layout:block name="contents">
				</layout:block>
			</div>
		</div>
	</div>

	<%--  <%@ include file="footer.jsp" %> --%>
</body>
</html>