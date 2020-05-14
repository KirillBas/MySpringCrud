<%--
  Created by IntelliJ IDEA.
  User: drbah
  Date: 10.05.2020
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<table border="1px">

    <h1>Add user</h1>
    <form action="/admin/users" method="post">
        <input type="text" name="name">
        <input type="text" name="email">
        <input type="text" name="password">
        <input type="submit" value="OK">
    </form>

    <h1>Users list</h1>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Delete</th>
        <th>Update</th>

    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getEmail()}</td>
            <c:forEach var="role" items="${user.roleSet}">
                <td>${role.role}</td>
            </c:forEach>
            <td><a href="<c:url value='/admin/delete/${user.id}'/>">Delete</a></td>
            <td><a href="<c:url value='/admin/edit/${user.id}'/>">Update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
