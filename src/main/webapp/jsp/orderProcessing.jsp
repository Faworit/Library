<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.02.2019
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<form action="/ChangeLanguage" method="get">
    <input type="hidden" name="jspname" value="/jsp/orders.jsp"/>
    <p><input type="submit" name="language" value="RU"> </p>
</form>

<form action="/ChangeLanguage" method="get">
    <input type="hidden" name="jspname" value="/jsp/orders.jsp" />
    <p><input type="submit" name="language" value="ENG" ></p>
</form>

<table border="1">
    <tr>
        <td><fmt:message key="key.idOrder"/></td>
        <td><fmt:message key="key.bookTitle"/></td>
        <td><fmt:message key="key.orderDate"/></td>
        <td><fmt:message key="key.returnDate"/></td>
        <td><fmt:message key="key.actuallyReturned"/></td>
        <td><fmt:message key="key.giveAway"/></td>
        <c:if test="${role eq 'librarian'}">
            <td><fmt:message key="key.receiver"/></td>
        </c:if>
        <td><fmt:message key="key.state"/></td>
        <c:if test="${role eq 'librarian'}">
            <td><fmt:message key="key.action"/></td>
        </c:if>
    </tr>
    <form action="editBooking" method="get">
        <tr>
            <td>${booking.orderID}</td>
            <td>${booking.bookTitle}</td>
            <td><input type="date" name="orderDate" placeholder="YYYY-MM-DD" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" title="Enter a date in this format YYYY-MM-DD"/></td>
            <td><input type="date" name="returnDate" placeholder="YYYY-MM-DD" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" title="Enter a date in this format YYYY-MM-DD"/></td>
            <td><input type="date" name="actuallyReturn" placeholder="YYYY-MM-DD" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" title="Enter a date in this format YYYY-MM-DD"/></td>
            <c:if test="${role eq 'librarian'}">
                <td>
                        ${booking.librarian.name}
                        ${booking.librarian.surname}
                </td>
            </c:if>
            <td>
                ${booking.reader.name}
                ${booking.reader.surname}
            </td>
            <td>
                <select name="s1">
                    <option selected disabled><fmt:message key="key.selectStatus"/></option>
                    <c:forEach var="status" items="${list}">
                        <option>${status}</option>
                    </c:forEach>
                 </select>
            </td>
            <td>
                <p><input type="submit" value="<fmt:message key="key.apply"/>"></p>
            </td>
        </tr>
    </form>
</table>

</body>
</html>
