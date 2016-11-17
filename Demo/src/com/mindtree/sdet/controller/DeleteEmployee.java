package com.mindtree.sdet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.sdet.exceptions.DeleteException;
import com.mindtree.sdet.service.EmployeeService;

/**
 * Servlet implementation class DeleteEmployee
 */
@SuppressWarnings("serial")
public class DeleteEmployee extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeService manager = new EmployeeService();
		String msg = "";
		
		String sid = request.getParameter("id");
		try {
			manager.deleteEmployee(sid);
			response.sendRedirect("ViewAllEmployees");
		} catch (DeleteException e) {
			msg = e.getMessage();
			request.setAttribute("message", msg);
			RequestDispatcher rd = request.getRequestDispatcher("viewEmployee.jsp");
			rd.forward(request, response);
		}
	}

}
