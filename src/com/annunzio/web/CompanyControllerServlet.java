package com.annunzio.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class CompanyControllerServlet
 */
@WebServlet("/CompanyControllerServlet")
public class CompanyControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	private CompanyDbUtil companyDbUtil;
	private DataSource dataSource;
	@Resource(name="jdbc/company_tracker")
	
	//Servlet lifecycle method
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		//Create db util, pass in conn pool/data source
		try {
			companyDbUtil = new CompanyDbUtil(dataSource);
			
			
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String command = request.getParameter("command");
			if(command == null) {
				command = "LIST";
			}
			
			switch(command) {
			case "LIST":
				listCompanies(request, response);
				break;
			default: 
				listCompanies(request, response);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void listCompanies(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			List<Company> companies = companyDbUtil.getCompanies();
			request.setAttribute("COMPANY_LIST", companies);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list_companies.jsp");
			
			for(Company c : companies) {
				System.out.println("The company: " + c);
			}
			dispatcher.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
