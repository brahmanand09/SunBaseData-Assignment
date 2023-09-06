<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.servlet.Person" %>

<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
</head>
<body>
    <div class="container">
        <h2>Customer List</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>uuid</th>
                    <th>first_name</th>
                    <th>last_name</th>
                    <th>street</th>
                    <th>address</th>
                    <th>city</th>
                    <th>state</th>
                    <th>email</th>
                    <th>phone</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${data}" var="person">
                    <tr>
                        <td><c:out value="${person.uuid}" /></td>
                        <td><c:out value="${person.first_name}" /></td>
                        <td><c:out value="${person.last_name}" /></td>
                        <td><c:out value="${person.street}" /></td>
                        <td><c:out value="${person.address}" /></td>
                        <td><c:out value="${person.city}" /></td>
                        <td><c:out value="${person.state}" /></td>
                        <td><c:out value="${person.email}" /></td>
                        <td><c:out value="${person.phone}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="row">
            <div class="col">
                <button type="button" class="btn btn-success" onClick="redirectTo('add_cust.html')">Add Customer</button>
            </div>
            <div class="col">
                <button type="button" class="btn btn-danger" onClick="redirectTo('del_cust.html')">Delete Customer</button>
            </div>
            <div class="col">
                <button type="button" class="btn btn-primary" onClick="redirectTo('update_cust.html')">Update Customer</button>
            </div>
        </div>
    </div>
     <script>
        function redirectTo(url) {
            window.location.href = url;
        }
    </script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
