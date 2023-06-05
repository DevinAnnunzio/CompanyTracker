package com.annunzio.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name= "jdbc/company_tracker")
	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Used to send data back to browser
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		CompanyDbUtil cdbU = new CompanyDbUtil(dataSource);
		try {
			cdbU.getCompanies();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			//DataSource is same as connection pool
			myConn = dataSource.getConnection();
			
			//Create SQL statement
			String sqlStmt = "SELECT * FROM company";
			myStmt = myConn.createStatement();
			
			//Execute SQL query
			myRs = myStmt.executeQuery(sqlStmt);
			
			//Process sql result
			while(myRs.next()) {
				String email = myRs.getString("company_name");
				out.println(email);
			}
					
		}  catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
