<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<c:if test="${sessionScope.customer.fullName ne null}">
    <h1 class="display-5 text-center pt-5">Hello ${sessionScope.customer.fullName}</h1>
</c:if>
<form:form cssClass="m-5 p-5 text-center" cssStyle="width: 1200px" modelAttribute="pcDto" action="/products/search"
           method="post">
    <table class="table table-striped table-success table-hover">
        <tr>
            <td>
                <form:input path="minPrice" name="minPrice" placeHolder="Min Price"/>
            </td>
            <td>
                <form:input path="maxPrice" name="maxPrice" placeHolder="Max Price"/>
            </td>
            <td>
                <form:button name="search">Search</form:button>
            </td>
        </tr>
        <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Price</th>
        </tr>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>${product.code}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
            </tr>
        </c:forEach>
    </table>
</form:form>
</body>
</html>
