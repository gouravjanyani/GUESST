<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GUESST Owner Registration</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<header class="p-3" style="background-color: blue;">
        <nav class="navbar navbar-expand-md navbar-dark" >
            <a href="" class="navbar-brand fw-bold">GUESST</a>
            <%-- <ul class="navbar-nav w-100 d-flex flex-row">
            <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/OwnerDashboard.jsp" class="nav-link">Home</a>
                </li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/TenantController?action=list" class="nav-link">Tenants</a>
                </li>
                 <li class="nav-item justify-self-end">
                    <a href="<%=request.getContextPath()%>/" class="nav-link">Logout</a>
                </li> 
            </ul> --%>
        </nav>
    </header>

<div class="container col-md-5 mt-5">
		<div class="card">
			<div class="card-body">
			
			<form action="TenantController?action=register" method="post">
				

				<caption>
					<h2>	
            			Building Owner Registration
            
					</h2>
				</caption>

			<fieldset class="form-group">
					<label> Name</label> <input type="text"
						 class="form-control"
						name="name" required="required">
				</fieldset> 
				
<!-- 				<fieldset class="mb-3">
            <legend class="col-form-label">Choose the user role:</legend>
            <div class="mb-3">
                <select id="role" name="role" class="form-select">
                    <option value="tenant">Tenant</option>
                    <option value="owner">Owner</option>
                </select>
            </div>
        </fieldset> -->
				<fieldset class="form-group">
					<label>Email</label> <input type="email"
						  class="form-control"
						name="email" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Building</label> <input type="text"
						  class="form-control"
						name="building" required="required">
				</fieldset>
		
				<fieldset class="form-group">
					<label>Password</label> <input type="password"	
						 class="form-control"
						name="password" required="required">
				</fieldset> 
				


				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>