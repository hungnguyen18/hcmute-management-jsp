<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Dao.*"%>
<%@ page import="Bean.UserBean"%>
<%@ page import="Util.UserSessionUtil"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance"
	prefix="layout"%>
<%UserBean user = UserSessionUtil.getUser(request);%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <!-- Include Tailwind CSS CDN -->
</head>
<body class="bg-gray-100">
    <layout:extends name="base">
        <layout:put block="contents">
            <div class="container mx-auto mt-8">
                <h1 class="text-2xl font-bold mb-4">User Profile</h1>

                <div class="bg-white p-6 rounded-md shadow-md">
                    <c:if test="${not empty user}">
                        <p class="mb-2">
                            <strong>User ID:</strong> ${user.user_ID}
                        </p>
                        <p class="mb-2">
                            <strong>Username:</strong> ${user.username}
                        </p>
                        <p class="mb-2">
                            <strong>Email:</strong> ${user.email}
                        </p>
                        <p class="mb-2">
                            <strong>Phone Number:</strong> ${user.phone_number}
                        </p>
                        <p class="mb-2">
                            <strong>Role:</strong> ${user.role}
                        </p>
                    </c:if>

                    <c:if test="${empty user}">
                        <p>Please log in to view your profile.</p>
                    </c:if>
                </div>
            </div>
        </layout:put>
    </layout:extends>
</body>
</html>
