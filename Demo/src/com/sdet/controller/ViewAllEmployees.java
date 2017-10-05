package com.sdet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdet.entitiy.Competency;
import com.sdet.entitiy.Employee;
import com.sdet.entitiy.Vertical;
import com.sdet.exceptions.FetchException;
import com.sdet.service.EmployeeService;

/**
 * Servlet implementation class ViewAllEmployees
 */
@SuppressWarnings("serial")
public class ViewAllEmployees extends HttpServlet {
	List<Employee> list = null;
	private List<Competency> competency1;
	private List<Vertical> vertical1;

	public void init(ServletConfig config) throws ServletException {

		EmployeeService manager = new EmployeeService();
		try {
			competency1 = manager.getCompetency();
			vertical1 = manager.getVerticals();
			for (Competency c : competency1) {
				System.out.println("Comp  " + c.getComptencyName());
			}
		} catch (FetchException e) {
			System.out.println(e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeService manager = new EmployeeService();

		String msg;
		try {
			String competency = request.getParameter("competency");
			System.out.println("Competency :" + competency);
			String vertica = request.getParameter("vertical");
			System.out.println("Vertical :" + vertica);
			list = manager.getAllEmployees(competency, vertica);
			request.setAttribute("message", "success");
			request.setAttribute("empList", list);
			request.setAttribute("competency", competency1);
			request.setAttribute("vertical", vertical1);
			RequestDispatcher rd = request.getRequestDispatcher("viewEmployee.jsp");
			rd.forward(request, response);
		} catch (FetchException e) {
			msg = e.getMessage();
			request.setAttribute("message", msg);
			RequestDispatcher rd = request.getRequestDispatcher("viewEmployee.jsp");
			rd.forward(request, response);
		}

	}
}
