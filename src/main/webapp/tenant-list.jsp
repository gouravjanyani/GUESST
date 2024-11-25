<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.GUESST.bean.Tenant" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Tenants</title>
    <!-- Bootstrap CSS -->
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

    <div class="container mt-4">
        <h3 class="text-center">List of Tenants</h3>
        <hr>
        <div class="text-left mb-3">
            <a href="<%=request.getContextPath()%>/TenantController?action=new" class="btn btn-success">Add New Tenant</a>
        </div>
        
        <% List<Tenant> listTenant = (List<Tenant>) request.getAttribute("listTenant"); %>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Room Number</th>
                    <th>Floor</th>
        			<th>Building</th>
        			<th>Date Of Joining</th>
        			<th>Rent/Month</th>
        			<th>Rent Status</th>
        			<th>Payment Verification</th>
                </tr>
            </thead>
            	 
               <tbody>
               
              
               
             
               
               <% if("true".equals(request.getParameter("collected"))){ %>
           
                   <% for(Tenant tenant : listTenant){ %>
                    <% if(tenant.isPayVerification() == true){ %>
                    
                    		<!-- inside true -->
                            <tr>
                                <td><c:out value="<%=tenant.getId()%>" /></td>
                                <td><c:out value="<%=tenant.getName()%>" /></td>
                                <td><c:out value="<%=tenant.getEmail()%>" /></td>
                                <td><c:out value="<%=tenant.getRoomNumber()%>" /></td>
                                <td><c:out value="<%=tenant.getFloor()%>" /></td>
                                <td><c:out value="<%=tenant.getBuilding()%>" /></td>
                                <td><c:out value="<%=tenant.getDOJ()%>" /></td>
                                <td><c:out value="<%=tenant.getRent()%>" /></td>
                                <td><c:out value="<%=tenant.isRentStatus()%>" /></td>
                                <td><c:out value="<%=tenant.isPayVerification()%>" /></td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/TenantController?action=edit&id=<c:out value='<%=tenant.getId()%>' />" class="btn btn-warning btn-sm">Edit</a>
                                    <a href="<%=request.getContextPath()%>/TenantController?action=delete&id=<c:out value='<%=tenant.getId()%>' />" class="btn btn-danger btn-sm">Delete</a>
                                </td>
                            </tr>
                       
                    <% }}} %>
               
                <% if("false".equals(request.getParameter("collected"))){ %>
                   <% for(Tenant tenant : listTenant){ %>
                    <% if(tenant.isPayVerification() == false){ %>
                    		<!-- inside false -->
                        
                            <tr>
                                <td><c:out value="<%=tenant.getId()%>" /></td>
                                <td><c:out value="<%=tenant.getName()%>" /></td>
                                <td><c:out value="<%=tenant.getEmail()%>" /></td>
                                <td><c:out value="<%=tenant.getRoomNumber()%>" /></td>
                                <td><c:out value="<%=tenant.getFloor()%>" /></td>
                                <td><c:out value="<%=tenant.getBuilding()%>" /></td>
                                <td><c:out value="<%=tenant.getDOJ()%>" /></td>
                                <td><c:out value="<%=tenant.getRent()%>" /></td>
                                <td><c:out value="<%=tenant.isRentStatus()%>" /></td>
                                <td><c:out value="<%=tenant.isPayVerification()%>" /></td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/TenantController?action=edit&id=<c:out value='<%=tenant.getId()%>' />" class="btn btn-warning btn-sm">Edit</a>
                                    <a href="<%=request.getContextPath()%>/TenantController?action=delete&id=<c:out value='<%=tenant.getId()%>' />" class="btn btn-danger btn-sm">Delete</a>
                                </td>
                            </tr>
                         <% }} %>
                   
                   <% } if("true".equals(request.getParameter("proof"))){%>
                   
                   <% for(Tenant tenant : listTenant){ %>
                    <% if(tenant.getProof() != null && tenant.isPayVerification() == false && tenant.isRentStatus() == true){ %>
                    		<!-- proof -->
                            <tr>
                                <td><c:out value="<%=tenant.getId()%>" /></td>
                                <td><c:out value="<%=tenant.getName()%>" /></td>
                                <td><c:out value="<%=tenant.getEmail()%>" /></td>
                                <td><c:out value="<%=tenant.getRoomNumber()%>" /></td>
                                <td><c:out value="<%=tenant.getFloor()%>" /></td>
                                <td><c:out value="<%=tenant.getBuilding()%>" /></td>
                                <td><c:out value="<%=tenant.getDOJ()%>" /></td>
                                <td><c:out value="<%=tenant.getRent()%>" /></td>
                                <td><c:out value="<%=tenant.isRentStatus()%>" /></td>
                                <td><c:out value="<%=tenant.isPayVerification()%>" /></td>
                                
                                <td>
                                	<a href="${pageContext.request.contextPath}/TenantController?action=verify&id=<c:out value='<%=tenant.getId()%>' />" class="btn btn-success btn-sm mb-2">Verify proof</a>
                                    <a href="${pageContext.request.contextPath}/TenantController?action=edit&id=<c:out value='<%=tenant.getId()%>' />" class="btn btn-warning btn-sm mb-2">Edit</a>
                                    <a href="${pageContext.request.contextPath}/TenantController?action=delete&id=<c:out value='<%=tenant.getId()%>' />" class="btn btn-danger btn-sm mb-2">Delete</a>
                                </td>
                            </tr>
                         <% }} %>
                		
               <% }else if(request.getParameter("collected") == null){ %>
                   	<!-- inside empty -->
               	    <% for(Tenant tenant : listTenant){ %>
                  
  
                        
                        
                        <tr>
                                <td><c:out value="<%=tenant.getId()%>" /></td>
                                <td><c:out value="<%=tenant.getName()%>" /></td>
                                <td><c:out value="<%=tenant.getEmail()%>" /></td>
                                <td><c:out value="<%=tenant.getRoomNumber()%>" /></td>
                                <td><c:out value="<%=tenant.getFloor()%>" /></td>
                                <td><c:out value="<%=tenant.getBuilding()%>" /></td>
                                <td><c:out value="<%=tenant.getDOJ()%>" /></td>
                                <% System.out.println(tenant.getDOJ()); %>
                                <td><c:out value="<%=tenant.getRent()%>" /></td>
                                <td><c:out value="<%=tenant.isRentStatus()%>" /></td>
                                <td><c:out value="<%=tenant.isPayVerification()%>" /></td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/TenantController?action=edit&id=<c:out value='<%=tenant.getId()%>' />" class="btn btn-warning btn-sm">Edit</a>
                                    <a href="<%=request.getContextPath()%>/TenantController?action=delete&id=<c:out value='<%=tenant.getId()%>' />" class="btn btn-danger btn-sm">Delete</a>
                                </td>
                            </tr>
                 
                    <% }} %> 
        </tbody>
            
            
        </table>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
