<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: drbah
  Date: 11.05.2020
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<%--<form action="user" method="post">--%>
<%--    <c:forEach var="user" items="${current_user}">--%>
<%--        <tr>--%>
<%--            <td>${user.getId()}</td>--%>
<%--            <td><input type="text" name="email" value="${user.email()}"></td>--%>
<%--            <td><input type="text" name="name" value="${user.name()}"></td>--%>
<%--            <input type="submit" value="OK">--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</form>--%>

<form action="/edit/update" method="post">
    <input type="text" name="id" value="${user.id}">
    <input type="text" name="email" value="${user.email}">
    <input type="text" name="name" value="${user.name}">
    <input type="submit" value="OK">
</form>
</body>
</html>
