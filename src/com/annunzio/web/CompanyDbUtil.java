package com.annunzio.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CompanyDbUtil {
	
	private DataSource dataSource;
	
	public CompanyDbUtil(DataSource ds) {
		this.dataSource = ds;
	}
	
	public List<Company> getCompanies() throws Exception{
		List<Company> companies = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {

			myConn = dataSource.getConnection();
			
			String qry = "SELECT * FROM company";
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(qry);
			
			
			while(myRs.next()) {
				int id = Integer.parseInt(myRs.getString("id"));
				String companyName = myRs.getString("company_name");
				
				Company tempComp = new Company(id, companyName);
				
				companies.add(tempComp);
			}
			
			System.out.println(companies);;
			
			return companies;
			
		} 
		
		
		finally {
			close(myConn, myStmt, myRs);
		}

	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			
			if(myRs != null) {
				myRs.close();
			}
			
			if(myStmt != null) {
				myStmt.close();
			}
			
			if(myConn != null) {
				myConn.close();
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
