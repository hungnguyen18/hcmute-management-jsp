<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

	<div id="wrapper" class="flex justify-stretch ">
		<layout:block name="sidebar">
			<%@ include file="sidebar.jsp"%>
		</layout:block>



		<div id="content" class="w-screen">
			<layout:block name="header">
				<%@ include file="header.jsp"%>
			</layout:block>
			<layout:block name="contents">
			</layout:block>
		</div>
	</div>

	<%--  <%@ include file="footer.jsp" %> --%>
</body>
</html>