<%@ page import="java.util.*,com.annunzio.web.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Company tracking app</title>
</head>

<%
List<Company> allCompanies = (List<Company>) request.getAttribute("COMPANY_LIST");
%>


<body>
<h1>List of current companies followed</h1>
	
		<table>
			<tr>
				<th>Company ID</th>
				<th>Company Name</th>
				<th>CEO</th>
				<th>Stock Price</th>
				<th>Headquarters</th>
				<th>Action</th>
			</tr>
			
			<%for(Company c: allCompanies){%>
			
			<tr>
				<td><%=c.getId() %></td>
				<td><%=c.getCompanyName() %></td>
				<td><%=c.getCeo() %></td>
				<td><%=c.getStockPrice() %></td>
				<td><%=c.getHeadquarters() %></td>
				<td> <a href="">Update</a>
				 	 <a href="CompanyControllerServlet?command=DELETE&companyId=<%=(c.getId())%>">Delete</a> 
				</td>
			</tr>
			<%}%>
			
		
		</table>

		<input type="button" value="Add company"
	  onclick="window.location.href='add-company-form.jsp'"
	 />

</body>
</html>