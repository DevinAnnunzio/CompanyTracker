<%@ page import="java.util.*,com.annunzio.web.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update company</title>
</head>
<body>
<h1>WORKING</h1>

<form action="CompanyControllerServlet" method ="GET">

		<input type="hidden" name="command" value="SUBMIT_EDIT">
		<input type="hidden" name="companyId" value="${COMPANY_TO_LOAD.getId()}">
		
  			<label for="companyName">Company name:</label>
  			<input type="text" id="cname" name="companyName" value="${COMPANY_TO_LOAD.getCompanyName()}">
  			
  			<label for="ceoName">Ceo name:</label>
  			<input type="text" id="ceoN" name="ceoName" value="${COMPANY_TO_LOAD.getCeo()}">
  			
  			<label for="headquarters">Headquarters:</label>
  			<input type="text" id="hq" name="headquarters" value="${COMPANY_TO_LOAD.getHeadquarters()}">
  			
  			<input type="submit" value="Update">
  		</form>
  		<br/>
  		<br/>
<p><a href="CompanyControllerServlet">Back to company list</a></p>
</body>
</html>