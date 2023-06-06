<%@ page import="java.util.*,com.annunzio.web.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add company</title>
</head>
<body>

		<h2>Add a company</h2>
	
		
		<form action="CompanyControllerServlet" method ="POST">
  			<label for="companyName">Company name:</label>
  			<input type="text" id="cName" name="companyName">
  			<br/>
  			<label for="companyCeo">Company CEO:</label>
  			<input type="text" id="cCeo" name="companyCeo">
  			<br/>
  			<label for="companyHeadquarters">Company headquarters:</label>
  			<input type="text" id="cHeadquarters" name="companyHeadquarters">
				<br/>
  			<input type="submit" value="Submit" class="save">
  		</form>
  
  		<br/>
  		<hr/>
  		<br/>
  	
  		<p>
  			<a href="CompanyControllerServlet">Back to company list</a>
  		</p>
		

</body>
</html>