<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
</head>
  <body>
	  
	      <header class="p-3" style="background-color: blue;">
        <nav class="navbar navbar-expand-md navbar-dark" >
            <a href="#" class="navbar-brand fw-bold">GUESST</a>
            
        </nav>
    </header>
    <section>
      <div class="d-flex justify-content-around align-items-center h-50">
        <div class="fs-1 fw-bolder">
        WELCOME TO <br />
          GUESST <br />
          <a class="btn btn-dark" href="Register.jsp">signup</a>
          <a class="btn btn-dark" href="Login.jsp">signin</a>
        </div>

        <img class="" src="${pageContext.request.contextPath}/assets/modern-building.webp" width="30%" />
      </div>
    </section>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
  </body>
</html>