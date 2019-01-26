<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.01.2019
  Time: 21:02
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
    <p><h1>Hello ${name}</h1></p>

    <form action="/ChangeLanguage" method="get">
        <input type="hidden" name="jspname" value="jsp/user.jsp" />
        <p><input type="submit" name="language" value="RU" title="/authorization.jsp"> </p>
    </form>

    <form action="ChangeLanguage" method="get">
        <input type="hidden" name="jspname" value="jsp/user.jsp" />
        <p><input type="submit" name="language" value="ENG" title="/user.jsp" ></p>
    </form>
    <table border="1">
        <tr>
            <td><fmt:message key="key.title"/></td>
            <td><fmt:message key="key.autor"/></td>
            <td><fmt:message key="key.genre"/></td>
            <td><fmt:message key="key.ISBN"/></td>
            <td><fmt:message key="key.number"/></td>
            <td><fmt:message key="key.quantity"/></td>
        </tr>
    </table>

    <c:forEach var="i" begin="1" end="5">

    </c:forEach>

    <c:if test="${role eq 'librarian'}">
        <form action="setUser" method="get">
            <p><button formaction="/jsp/setUser.jsp"> <fmt:message key="button.setUser"/></button> </p>
        </form>
    </c:if>

</body>
</html>
