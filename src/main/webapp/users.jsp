<%@ page import="java.util.List" %>
<%@ page import="Model.User" %>
<%@ page import="Controller.UserController" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h2>User List</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
        </tr>
        <%
            List<User> userList = (List<User>) request.getAttribute("userList");
            for (User user : userList) {
        %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getUsername() %></td>
            <td><%= user.getPassword() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
