package com.annunzio.web;

import java.io.IOException;
import java.sql.SQLException;
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
	@Resource(name="jdbc/company_tracker")
	private DataSource dataSource;

	
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
			case "DELETE":
				deleteCompany(request,response);
				break;
			
			case "LOAD_EDIT":
				loadEdit(request,response);
				break;
				
			case "SUBMIT_EDIT":
				submitEdit(request, response);
				break;
				
			default: 
				listCompanies(request, response);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void submitEdit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int compId = Integer.valueOf(request.getParameter("companyId"));
		String compName = request.getParameter("companyName");
		String ceoName = request.getParameter("ceoName");
		String headquarters = request.getParameter("headquarters");
		
		Company compToEdit = new Company(compId, compName, ceoName, headquarters);
		
		try {
			companyDbUtil.submitEdit(compToEdit);
			listCompanies(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void loadEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		try {
			String companyId = request.getParameter("companyId");
			Company comp = companyDbUtil.getCompany(companyId);
			request.setAttribute("COMPANY_TO_LOAD", comp);
			
			if(comp.getId() == -1) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/search-for-company-error.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-company-form.jsp");
				dispatcher.forward(request, response);
			}
			
			//ISSUE WITH NULL POINTER EXCEPTION, POSSIBLY LINKED TO OBJECTS BEING SET HAVING NULL VALUE
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void deleteCompany(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		try {
			int companyId = Integer.valueOf(request.getParameter("companyId"));
			companyDbUtil.deleteCompany(companyId);
			listCompanies(request,response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void listCompanies(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			List<Company> companies = companyDbUtil.getCompanies();
			request.setAttribute("COMPANY_LIST", companies);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-companies.jsp");
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
		try {
			addCompany(request,response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void addCompany(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String companyName = request.getParameter("companyName");
		String companyCeo = request.getParameter("companyCeo");
		String companyHeadquarters = request.getParameter("companyHeadquarters");
		
		Company compToAdd = new Company(companyName, companyCeo, companyHeadquarters);
		
		companyDbUtil.addCompany(compToAdd);
		response.sendRedirect(request.getContextPath() + "/CompanyControllerServlet?command=LIST");
		
	}

}
