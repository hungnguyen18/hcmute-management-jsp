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

			<div class="m-auto w-full h-[80vh] flex justify-center items-center">
				<span class="text-5xl font-semibold">Welcome,
					${user.username} </span>
			</div>
		</layout:put>
	</layout:extends>
</body>
</html>