package com.annunzio.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				String ceo = null;
				String headquarters = null;
				if(myRs.getString("ceo") != null) {
					 ceo = myRs.getString("ceo");
				}
				if(myRs.getString("headquarters") != null) {
					 headquarters = myRs.getString("headquarters");
				}
				Company tempComp = new Company(id, companyName, ceo, headquarters);
				
				companies.add(tempComp);
			}
			
			
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

	public void addCompany(Company compToAdd) throws SQLException {
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "INSERT INTO company " + "(company_name, ceo, headquarters) " + "VALUES (?,?,?)";

			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, compToAdd.getCompanyName());
			myStmt.setString(2, compToAdd.getCeo());
			myStmt.setString(3, compToAdd.getHeadquarters());
			
			myStmt.execute();
			
		}
		
		finally {
				close(myConn, myStmt, null);
		}
		
		
	}
	

}
