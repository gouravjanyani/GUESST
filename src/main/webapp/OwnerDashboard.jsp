<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.GUESST.dao.TenantDao" %>
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
    <%	TenantDao tenantDao = new TenantDao();
    	String building = (String) session.getAttribute("building");
    	long expectedAmount = tenantDao.expectedAmount(building);
    	long rentCollected = tenantDao.rentCollected(building);
    	long pendingCollection = tenantDao.pendingCollection(building);
    	long pendingRentVerification = tenantDao.pendingRentVerification(building);
    %>
    <section class="h-100 w-100 p-5">
    	<div class="ms-5 mb-5">
            <a href="<%=request.getContextPath()%>/TenantController?action=new" class="btn btn-success">Add New Tenant</a>
        </div>
    	<div class="mt-5 w-100 d-flex justify-content-around">
    		<a class="text-decoration-none" href="<%=request.getContextPath()%>/TenantController?action=list">
	    		<div class="card shadow-lg" style="width: 18rem;">
	    			<div class="card-body">
	    			<div class="card-title fw-bold"> Total Expected Rent Collection</div>
	    				<div class="fs-2  text-muted"><%= expectedAmount %></div>
	    			</div>
	    		</div>
    		</a>
    		<a class="text-decoration-none" href="<%=request.getContextPath()%>/TenantController?action=list&collected=true">
    		<div class="card shadow-lg" style="width: 18rem;">
    			<div class="card-body">
    			<div class="card-title fw-bold"> Rent Collected</div>
    				<div class="fs-2 text-success "><%= rentCollected %></div>
    			</div>
    		</div>
    		</a>
    		<a class="text-decoration-none" href="<%=request.getContextPath()%>/TenantController?action=list&collected=false">
    		<div class="card shadow-lg" style="width: 18rem;">
    			<div class="card-body">
    			<div class="card-title fw-bold"> Pending Collection</div>
    				<div class="fs-2 text-danger"><%= pendingCollection %></div>
    			</div>
    		</div>
    		</a>
    		 <a class="text-decoration-none" href="<%=request.getContextPath()%>/TenantController?action=list&proof=true">
    		<div class="card shadow-lg" style="width: 18rem;">
    			<div class="card-body">
    			<div class="card-title fw-bold"> Pending Rent Verification</div>
    				<div class="fs-2 text-danger"><%= pendingRentVerification %></div>
    			</div>
    		</div>
    		</a>
    	</div>
    	
    	
    </section>

</body>
</html>