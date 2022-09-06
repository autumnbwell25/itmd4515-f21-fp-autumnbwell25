
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a New Customer</title>
</head>
<body>
<c:if test="${not empty requestScope.violations}">
    <p>Below are the issues with your data input.  We care about data integrity and  You must fix them in order to proceed.  This is the way.</p>
    <ol>
        <c:forEach var="violation" items="${requestScope.violations}">
            <li>There is a problem with ${violation.propertyPath}.  The error message is: ${violation.message}</li>
        </c:forEach>
    </ol>
</c:if>

<form action="/abardwell-fp/cust" method="post">
    <div>
        <label for="custId">Customer ID</label>
        <input value="${requestScope.customer.id}" id="custId" name="custId" type="text" />
    </div>


    <div>
        <label for="fName">First Name</label>
        <input value="${requestScope.customer.firstName}" id="fName" name="firstName" type="text" />
    </div>

    <div>
        <label for="lName">Last Name</label>
        <input value="${requestScope.customer.lastName}" id="lName" name="lastName" type="text" />
    </div>

    <div>
        <label for="email">Email</label>
        <input value="${requestScope.customer.email}" id="email" name="email" type="text" />
    </div>

    <div>
        <label for="active">Active</label>
        <input id="active" name="active" type="checkbox" ${requestScope.customer.active ? 'checked' : ''} />
    </div>

    <div>
        <label for="storeId">Store ID</label>
        <select id="storeId" name="storeId">
            <option ${requestScope.customer.storeId eq 1 ? 'selected' : ''} value="1">One</option>
            <option ${requestScope.customer.storeId eq 2 ? 'selected' : ''} value="2">Two</option>
        </select>
    </div>

    <div>
        <label for="addrId">Address ID</label>
        <input value="${requestScope.customer.addressId}" id="addrId" name="addrId" type="text" />
    </div>

    <button type="submit">Submit the request</button>

</form>



</body>
</html>
