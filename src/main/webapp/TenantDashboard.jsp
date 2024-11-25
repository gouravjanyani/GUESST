<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.GUESST.dao.TenantDao" %>
<%@ page import="com.GUESST.bean.Tenant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tenant Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

    <header class="p-3" style="background-color: blue;">
        <nav class="navbar navbar-expand-md navbar-dark" >
            <a href="" class="navbar-brand">GUESST</a>
            <ul class="navbar-nav">
            <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/TenantDashboard.jsp" class="nav-link">Home</a>
                </li>
                <li class="nav-item justify-self-end">
                    <a href="<%=request.getContextPath()%>/" class="nav-link">Logout</a>
                </li> 
            </ul>
        </nav>
    </header>
    <%	TenantDao tenantDao = new TenantDao();
    	int id = (int) session.getAttribute("id");
    	Tenant tenant = tenantDao.selectTenant(id);
    	int rent = 0;
    	if(tenant.isPayVerification() != true){
    		rent = tenant.getRent();
    	}
    	
    %>
    <section class="h-100 w-100 p-5">
    	<div class="mt-5 w-100 d-flex justify-content-around">
	    		<div class="card shadow-lg" style="width: 18rem;">
	    			<div class="card-body">
	    			<div class="card-title fw-bold"> Rent for July Month</div>
	    				<div class="fs-2  text-muted"><%= rent %></div>
	    			</div>
	    		</div>
    	
    		<a class="text-decoration-none " href="<%=request.getContextPath()%>/TenantController?action=payment-form">
    		<div class="card shadow-lg " style="width: 18rem;">
    			<div class="card-body bg-success">
    			<div class="card-title fw-bold text-"> Pay The Rent</div>
    			</div>
    		</div>
    		</a>
    	</div>
    	
    	
    	<div>
   		 <c:if test="${request.getAttribute('upload') == 'success'}">
	        <div class="text-success fs-1 fw-bold">File Uploaded Successfully</div>
	        <div class="text-success fs-1 fw-bold">Pending for Payment Verification from the Owner</div>
    	</c:if>
</div>	
    </section>

</body>
</html>