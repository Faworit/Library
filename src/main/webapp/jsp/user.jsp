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
        <input type="hidden" name="jspname" value="jsp/User.jsp"/>
        <p><input type="submit" name="language" value="RU"> </p>
    </form>

    <form action="ChangeLanguage" method="get">
        <input type="hidden" name="jspname" value="jsp/User.jsp" />
        <p><input type="submit" name="language" value="ENG" ></p>
    </form>
    <table border="1">
        <tr>
            <td><fmt:message key="key.title"/></td>
            <td><fmt:message key="key.genre"/></td>
            <td><fmt:message key="key.autor"/></td>
            <td><fmt:message key="key.ISBN"/></td>
           <%-- <td><fmt:message key="key.number"/></td>--%>
            <td><fmt:message key="key.quantity"/></td>

            <c:if test="${role eq 'librarian'}">
            <td>
                <fmt:message key="key.format"/>
            </td>
            </c:if>


        </tr>
        <c:forEach var="books" items="${list}">
            <tr>
                <td>${books.title}</td>
                <td>
                    <c:forEach var="genre" items="${books.geners}">
                    ${genre.genreName} <br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="autor" items="${books.autors}">
                        ${autor.name}
                        ${autor.surname}
                        <br>
                    </c:forEach></td>
                <td>${books.ISBN}</td>
                <%--<td><c:if test="${not empty books.number}">
                    ${book.number}
                </c:if>
                </td>--%>
                <td>${books.quantity}</td>
                <c:if test="${role eq 'librarian'}">
                <td>
                    <form action="editBook" method="get">
                    <%--<p><input type="hidden" value="${books.ID}"></p>
                    <p><input type="hidden" name="language" value=${language} /></p>--%>
                    <p><input type="submit" value="<fmt:message key="button.edit"/>"> </p>
                    </form>
                    <form action="remove" method="get">
                        <%--<p><input type="hidden" name="jspname" value="jsp/user.jsp"/></p>--%>
                        <p><input type="hidden" name="del" value="${books.ID}"></p>
                        <%--<p><input type="hidden" name="language" value=${language} /></p>--%>
                        <p><input type="submit" value="<fmt:message key="button.delete"/>"></p>
                    </form>
                </td>
                </c:if>

            </tr>
        </c:forEach>
    </table>

    <form action="search" method="get">
        <p><input type="text" name="search" maxlength="50" value="<fmt:message key="key.search"/>"></p>
        <p><input type="submit" value="<fmt:message key="key.search"/>"></p>
    </form>

    <c:if test="${role eq 'librarian'}">
        <form action="setUser" method="get">
            <p><input type="submit" value="<fmt:message key="button.setUser"/>"></p>
        </form>
    </c:if>

</body>
</html>
