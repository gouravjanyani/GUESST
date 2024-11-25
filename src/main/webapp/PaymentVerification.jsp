<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<%
	String imgFileName = (String) request.getAttribute("image");
	System.out.println("img---" +imgFileName);
	int id = Integer.parseInt(request.getParameter("id"));
%>

<h1>Rent Payment Proof</h1>

<%
	if(imgFileName != ""){
%>

	<img src="assets/<%=imgFileName %>" > 

<% } %>




	<a href="<%=request.getContextPath()%>/TenantController?action=payVerified&id=<%= id %>" class="btn btn-success">Verify</a>

</body>
</html>