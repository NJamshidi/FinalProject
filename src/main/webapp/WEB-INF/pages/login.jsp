<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/static/css/main.css"/>">
</head>
<body>
<div class="btn-group mt-5 mx-5">
    <a href="<c:url value="/"/>" class="btn btn-outline-primary">Register</a>
    <a href="<c:url value="/login"/>" class="btn btn-outline-primary active">Login</a>
</div>
<form:form cssClass="p-3 m-3" cssStyle="" modelAttribute="customer" action="login" method="post">
    <p class="text-danger">${error}</p>
    <table class="table table-bordered table-striped text-info">
        <tr>
            <td>
                <form:label path="userName">UserName :</form:label>
            </td>
            <td>
                <form:input path="userName" name="userName"/>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <form:errors path="userName" cssClass="text-danger"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">Password :</form:label>
            </td>
            <td>
                <form:input path="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <form:errors path="password" cssClass="text-danger"/>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <form:button name="login">Login</form:button>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
