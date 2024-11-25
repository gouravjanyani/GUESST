<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GUESST Login</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
 <header class="p-3" style="background-color: blue;">
        <nav class="navbar navbar-expand-md navbar-dark" >
            <a href="#" class="navbar-brand fw-bold">GUESST</a>
            
        </nav>
    </header>
<div class="container col-md-5 mt-5">
		<div class="card">
			<div class="card-body">
			
			<form action="TenantController?action=login" method="post">
				

				<caption>
					<h2>	
            			User Login
            
					</h2>
				</caption>

<%-- 				<fieldset class="form-group">
					<label>Tenant Name</label> <input type="dro"
						value="<c:out value='${tenant.name}' />" class="form-control"
						name="role" required="required">
				</fieldset> --%>
				
				<!-- <fieldset class="form-group">
				<label for="role">Choose the user role:</label>
			        <select id="role" name="role" class="form-group">
			            <option value="tenant">Tenant</option>
			            <option value="owner">Owner</option>
			        </select>
				</fieldset> -->
				
				<fieldset class="mb-3">
            <legend class="col-form-label">Choose the user role:</legend>
            <div class="mb-3">
                <select id="role" name="role" class="form-select">
                    <option value="tenant">Tenant</option>
                    <option value="owner">Owner</option>
                </select>
            </div>
        </fieldset>
				<fieldset class="form-group">
					<label> Email</label> <input type="email"
						  class="form-control"
						name="email" required="required">
				</fieldset>
		
				<fieldset class="form-group">
					<label>Password</label> <input type="password"	
						 class="form-control"
						name="password" required="required">
				</fieldset> 
				


				<button type="submit" class="btn btn-success mt-3">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>