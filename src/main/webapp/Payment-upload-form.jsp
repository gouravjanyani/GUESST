<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>


<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			<%-- <% HttpSession session = request.getSession();%> --%>
				
					<form action="PaymentVerification" method="post" enctype = "multipart/form-data">
				

				<caption>
					<h2>
            			Make a Payment
					</h2>
				</caption>

				

				<fieldset class="form-group">
					<label>Tenant Name</label> <input type="text"
						value="<c:out value='${tenant.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Tenant Email</label> <input type="text"
						value="<c:out value='${tenant.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>Tenant Room Number</label> <input type="text"
						value="<c:out value='${tenant.roomNumber}' />" class="form-control"
						name="roomNumber">
				</fieldset>
				<fieldset class="form-group">
					<label>Floor Number</label> <input type="text"
						value="<c:out value='${tenant.floor}' />" class="form-control"
						name="floor">
				</fieldset>
				<fieldset class="form-group">
					<label>Rent/Month</label> <input type="text"
						value="<c:out value='${tenant.rent}' />" class="form-control"
						name="rent">
				</fieldset>
				<fieldset class="form-group">
					<label>Upload payment screenshot:</label> <input type="file"
						 class="form-control"
						name="proof">
				</fieldset>



				<button type="submit" class="btn btn-success">Save</button>
				</form>
				
			</div>
		</div>
	</div>

</body>
</html>