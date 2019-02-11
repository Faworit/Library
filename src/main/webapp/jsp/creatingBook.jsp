<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.02.2019
  Time: 12:43
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

<form action="/ChangeLanguage" method="get">
    <input type="hidden" name="jspname" value="jsp/creatingBook.jsp"/>
    <p><input type="submit" name="language" value="RU"> </p>
</form>

<form action="/ChangeLanguage" method="get">
    <input type="hidden" name="jspname" value="jsp/creatingBook.jsp" />
    <p><input type="submit" name="language" value="ENG" ></p>
</form>

<form action="createBook" method="get">
    <table>
        <tr>
            <td><fmt:message key="key.idBook"/>*</td>
            <td><input type="text" name="IDBook" required></td>
        </tr>
        <tr>
            <td><fmt:message key="key.titleRU"/>*</td>
            <td><input type="text" name="titleRU" maxlength="200" required></td>
        </tr>
        <tr>
            <td><fmt:message key="key.titleENG"/>*</td>
            <td><input type="text" name="titleENG" maxlength="200" required></td>
        </tr>
        <tr>
            <td><fmt:message key="key.ISBN"/></td>
            <td><input type="text" name="ISBN" maxlength="17"></td>
        </tr>
        <tr>
            <td><fmt:message key="key.number"/></td>
            <td><input type="text" name="number"></td>
        </tr>
        <tr>
            <td><fmt:message key="key.quantity"/>*</td>
            <td><input type="text" name="quantity" required></td>
        </tr>
        </tr>

        <tr>
            <td><fmt:message key="key.genre"/>*</td>
            <td>
                <select name="s1">
                    <option><fmt:message key="key.selectGenre"/></option>
                    <c:forEach var="genres" items="${list}">
                        <option>${genres.genreName}</option>
                    </c:forEach>
                </select>
                <input type="text" multiple id="textfield" name="genre" value="">

                <script type="text/javascript">
                    for (var i = 0; i < 5; i++){
                        document.querySelector('select[name=s1]').onchange = function() {
                            document.getElementById('textfield').value += document.querySelector('select[name=s1]').value + ", ";
                        }
                    }
                </script>
            </td>
        </tr>
    </table>
    <input type="submit" value="<fmt:message key="button.addBook"/>">
</form>
<form action="Forward" method="get">
    <input type="hidden" name="direction" value="jsp/creatingGenre.jsp">
    <input type="submit" value="<fmt:message key="button.addGenre"/>">
</form>
</body>
</html>

        <%--<tr>
            <td><fmt:message key="key.numberOfGenres"/>*</td>
            <td><input type="text" name="numberOfGenres" required></td>
        </tr>
        <tr>
            <td><fmt:message key="key.numberOfAuthors"/>*</td>
            <td><input type="text" name="numberOfAuthors" required></td>
        </tr>
--%>

        <%--<tr>
            <td><fmt:message key="key.idAuthor"/>*</td>
            <td><input type="text" name="idAuthor" required></td>
        </tr>
        <tr>
            <td><fmt:message key="key.authorName"/>*</td>
            <td><input type="text" name="authorName" required></td>
        </tr>
        <tr>
            <td><fmt:message key="key.authorSurname"/>*</td>
            <td><input type="text" name="authorSurname" required></td>
        </tr>

            <td><fmt:message key="key.isBlock"/>*</td>
            <td><select required name="block">
                <option></option>
                <option value="n"><fmt:message key="key.nblock"/></option>
                <option value="y"><fmt:message key="key.block"/></option>
            </select></td>
        </tr>
        <tr>
            <td><fmt:message key="key.role"/>*</td>
            <td><select name="role" required>
                <option></option>
                <option disabled><fmt:message key="key.selectRole"/></option>
                <option value="reader" ><fmt:message key="key.reader"/></option>
                <option value="librarian"><fmt:message key="key.librarian"/></option>
            </select></td>
        </tr>--%>



