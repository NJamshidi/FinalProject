<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Hello</title>
</head>
<body>
<h2>
    <c:if test="${not empty user }" >
        <p>Hello ${user.firstName}. An Confirmation Email is sent to ${user.email}.
            click on it to be confirmed.</p>
    </c:if>

    <c:if test="${empty user }">
        <p>Hello guest</p>
    </c:if>

</h2>
<a href="/user/register">register new user</a>
</body>
</html>