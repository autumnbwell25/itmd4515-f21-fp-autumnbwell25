
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a New Customer</title>
</head>
<body>
<form>
    <div>
        <label for="custId">Customer ID</label>
        <input id="custId" name="custId" type="text" />
    </div>

    <div>
        <label for="fName">First Name</label>
        <input id="fName" name="fName" type="text" />
    </div>

    <div>
        <label for="lName">Customer ID</label>
        <input id="lName" name="lName" type="text" />
    </div>

    <div>
        <label for="email">Email</label>
        <input id="email" name="email" type="text" />
    </div>

    <div>
        <label for="active">Active</label>
        <input id="active" name="active" type="checkbox" />
    </div>

    <div>
        <label for="storeId">Store Id</label>
        <select id="storeId" name="storeId">
            <option value="1">One</option>
            <option value="2">Two</option>
        </select>
    </div>

    <div>
        <label for="addrId">Address Id</label>
        <input id="addrId" name="addrID" type="text" />
    </div>

    <button type="submit">Submit the Request</button>

</form>



</body>
</html>
