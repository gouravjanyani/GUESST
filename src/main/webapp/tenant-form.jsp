<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

	 <header class="p-3" style="background-color: blue;">
        <nav class="navbar navbar-expand-md navbar-dark" >
            <a href="" class="navbar-brand fw-bold">GUESST</a>
            <ul class="navbar-nav w-100 d-flex flex-row">
            <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/OwnerDashboard.jsp" class="nav-link">Home</a>
                </li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/TenantController?action=list" class="nav-link">Tenants</a>
                </li>
                 <li class="nav-item justify-self-end">
                    <a href="<%=request.getContextPath()%>/" class="nav-link">Logout</a>
                </li> 
            </ul>
        </nav>
    </header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${tenant != null}">
					<form action="TenantController?action=update" method="post">
				</c:if>
				<c:if test="${tenant == null}">
					<form action="TenantController?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${tenant != null}">
            			Edit User
            		</c:if>
						<c:if test="${tenant == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${tenant != null}">
					<input readonly type="text" name="id" value="<c:out value='${tenant.id}' />"  />
				</c:if>

				<fieldset class="form-group">
					<label>Tenant Name</label> <input type="text"
						value="<c:out value='${tenant.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Tenant Email</label> <input type="email"
						value="<c:out value='${tenant.email}' />" class="form-control"
						name="email" pattern="/^[A-Za-z0-9]+(?:[_][A-Za-z0-9]+)*$/">
				</fieldset>

				<fieldset class="form-group">
					<label>Tenant Room Number</label> <input type="number" min="1"
						value="<c:out value='${tenant.roomNumber}' />" class="form-control"
						name="roomNumber">
				</fieldset>
				<fieldset class="form-group">
					<label>Floor Number</label> <input type="number" min="1"
						value="<c:out value='${tenant.floor}' />" class="form-control"
						name="floor">
				</fieldset>
				<fieldset class="form-group">
					<label>Rent/Month</label> <input type="number" min="0" step="0.01"
						value="<c:out value='${tenant.rent}' />" class="form-control"
						name="rent">
				</fieldset>
				<fieldset class="form-group">
					<label>Rent Paid Status</label> <input type="text"
						value="<c:out value='${tenant.rentStatus}' />" class="form-control"
						name="rentStatus">
				</fieldset>
				<%-- <fieldset class="form-group">
					<label>Payment Verification</label> <input type="text"
						value="<c:out value='${tenant.payVerification}' />" class="form-control"
						name="payVerification">
				</fieldset> --%>
				
				<%
				String checked="";
				if("true".equals(request.getAttribute("payVerification"))){
					
					checked = "checked";
					}
					%>
				
				<fieldset class="form-group">
					<label>Payment Verification</label>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" id=" verification" name="payVerification" value="true" <%=checked %>>
						<label class="form-check-label" for="verification"></label>
					</div>
				</fieldset>
				<c:if test="${tenant == null}">
				<fieldset class="form-group">
					<label>Date of Joining</label> <input id="datepicker" type="date"	
						value="<c:out value='${tenant.DOJ}' />" class="form-control"
						name="DOJ">
				</fieldset> 
				</c:if>	
				<c:if test="${tenant == null}">
				<% String building = (String) session.getAttribute("building"); %>
				<fieldset class="form-group">
					<label>Building</label> <input type="text"	
						value="<%= building %>" class="form-control"
						name="building">
				</fieldset> 
				</c:if>	
				<c:if test="${tenant == null}">
				<fieldset class="form-group">
					<label>Password</label> <input type="password"	
						value="<c:out value='${tenant.password}' />" class="form-control"
						name="password">
				</fieldset> 
				</c:if>	


				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
	
	
	<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat: "dd/mm/yy" // Adjusted format to match HTML5 date input
		});
	});
	</script>
	</body>
</html>