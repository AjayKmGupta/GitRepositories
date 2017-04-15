package com.mindtree.sdet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.sdet.entitiy.Employee;
import com.mindtree.sdet.exceptions.FetchException;
import com.mindtree.sdet.service.EmployeeService;

/**
 * Servlet implementation class EditServlet
 */
@SuppressWarnings("serial")
public class EditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeService manager = new EmployeeService();
		Employee e = null;
		String msg;

		String sid = request.getParameter("mid");
		try {
			e = manager.getEmployeeById(sid);
			request.setAttribute("employee", e);
			request.setAttribute("message", "success");
			RequestDispatcher rd = request.getRequestDispatcher("editEmployee.jsp");
			rd.forward(request, response);
		} catch (FetchException e1) {
			msg = e1.getMessage();
			request.setAttribute("message", msg);
			RequestDispatcher rd = request.getRequestDispatcher("viewEmployee.jsp");
			rd.forward(request, response);
		}
		
	}
}
