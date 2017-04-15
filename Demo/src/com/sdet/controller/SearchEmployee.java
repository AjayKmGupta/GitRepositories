package com.mindtree.sdet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.sdet.entitiy.Competency;
import com.mindtree.sdet.entitiy.Employee;
import com.mindtree.sdet.entitiy.Vertical;
import com.mindtree.sdet.exceptions.FetchException;
import com.mindtree.sdet.service.EmployeeService;

/**
 * Servlet implementation class SearchEmployee
 */
public class SearchEmployee extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private List<Competency> competency1;
	private List<Vertical> vertical1;

	public void init(ServletConfig config) throws ServletException {

		EmployeeService manager = new EmployeeService();
			try {
				 competency1 =  manager.getCompetency();
				 vertical1 = manager.getVerticals();
				 for (Competency c : competency1){
					 System.out.println("Comp  "+c.getComptencyName());
				 }
			} catch (FetchException e) {
				System.out.println(e.getMessage());
			}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmployeeService manager = new EmployeeService();
		List<Employee> list = null;
		String msg;
		
		String mid = request.getParameter("search_id");
		try {
			list = manager.getEmployeesByID(mid);
			request.setAttribute("message", "success");
			request.setAttribute("empList", list);
			request.setAttribute("competency", competency1);
			request.setAttribute("vertical", vertical1);
			RequestDispatcher rd = request.getRequestDispatcher("viewEmployee.jsp");
			rd.forward(request, response);
		} catch (FetchException e1) {
			request.setAttribute("empList", list);
			msg = e1.getMessage();
			request.setAttribute("message", msg);
			RequestDispatcher rd = request.getRequestDispatcher("viewEmployee.jsp");
			rd.forward(request, response);
		}

	}

}
