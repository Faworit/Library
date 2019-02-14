<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.02.2019
  Time: 12:40
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
${information}
<form action="Forward" method="get">
    <input type="hidden" name="direction" value="jsp/user.jsp" />
    <p><input type="submit" value="<fmt:message key="button.forwardToUser"/>"></p>
</form>
</body>
</html>
