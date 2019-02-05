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
    <form action="LogIn" method="post">
        <p><input type="hidden" name="language" value=${language} /></p>
        <p><input type="text" name="login" maxlength="20" required placeholder="<fmt:message key="key.username"/>"></p>
        <p><input type="password" name="password" required placeholder="<fmt:message key="key.password"/>"></p>
        <p><input type="submit" value="<fmt:message key="button.submit"/>"> ${notCorrect}</p>
    </form>
    <form action="ChangeLanguage" method="get">
        <input type="hidden" name="jspname" value="authorization.jsp" />
        <p><input type="submit" name="language" value="RU" title="/authorization.jsp"> </p>
    </form>

    <form action="ChangeLanguage" method="get">
        <input type="hidden" name="jspname" value="authorization.jsp" />
        <p><input type="submit" name="language" value="ENG" title="/user.jsp" ></p>
    </form>
</body>
</html>
