package com.mindtree.sdet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindtree.sdet.entitiy.Employee;
import com.mindtree.sdet.exceptions.FetchException;
import com.mindtree.sdet.exceptions.UpdateException;
import com.mindtree.sdet.service.EmployeeService;

/**
 * Servlet implementation class UpdateDetails
 */
public class UpdateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		EmployeeService manager = new EmployeeService();
		String msg;
		int status;

		String ename = request.getParameter("ename");
		String mid = request.getParameter("mid");
		String competency = request.getParameter("competency");
		String practice = request.getParameter("practice");
		String vertical = request.getParameter("vertical");
		Employee e = new Employee(mid, ename, vertical, practice, competency);
		try {
			status = manager.updateEmployee(e);
			if (status > 0)
				response.sendRedirect("ViewAllEmployees");
			else if (status == -100) {
				msg = "Please enter valid details";
				request.setAttribute("employee", e);
				request.setAttribute("message", msg);
				RequestDispatcher rd = request
						.getRequestDispatcher("editEmployee.jsp");
				rd.forward(request, response);
			}
		} catch (UpdateException e1) {
			try {
				e = manager.getEmployeeById(mid);
			} catch (FetchException e2) {
			}
			request.setAttribute("employee", e);
			msg = e1.getMessage();
			request.setAttribute("message", msg);
			RequestDispatcher rd = request
					.getRequestDispatcher("editEmployee.jsp");
			rd.forward(request, response);

		}

		out.close();
	}

}
