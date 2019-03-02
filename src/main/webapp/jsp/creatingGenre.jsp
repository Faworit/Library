<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.02.2019
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="language"/>
<html lang="${language}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Library</title>
</head>
<body>

<form action="forward" method="get">
    <input type="hidden" name="direction" value="jsp/creatingGenre.jsp" />
    <p><input type="submit" name="language" value="RU"> </p>
</form>

<form action="forward" method="get">
    <input type="hidden" name="direction" value="jsp/creatingGenre.jsp" />
    <p><input type="submit" name="language" value="ENG" ></p>
</form>
<form action="addGenre" method="get">
    <table>
        <tr>
            <td><fmt:message key="key.idGenre"/>*</td>
            <td><input type="text" name="idGenre" required></td>
            <td>${errorID}</td>
        </tr>
        <tr>
            <td><fmt:message key="key.genreNameRU"/>*</td>
            <td><input type="text" name="genreNameRU" required></td>
            <td>${errorNameRU}</td>
        </tr>
        <tr>
            <td><fmt:message key="key.genreNameEN"/>*</td>
            <td><input type="text" name="genreNameENG" required></td>
            <td>${errorNameENG}</td>
        </tr>
    </table>
    <input type="submit" value="<fmt:message key="button.addGenre"/>">
</form>
<form action="logOut" method="get">
    <input type="submit" value="<fmt:message key="button.logOut"/>">
</form>
<form action="/showBook" method="get">
    <input type="submit" value="<fmt:message key="button.mainPage"/>">
</form>
</body>
</html>
