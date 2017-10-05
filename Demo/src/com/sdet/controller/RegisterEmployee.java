package com.sdet.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.sdet.exceptions.PersistentException;
import com.sdet.service.EmployeeService;

/**
 * Servlet implementation class RegisterEmployee
 */
public class RegisterEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Competency> competency1 = null;
	private List<Vertical> vertical1;

	public void init(ServletConfig config) throws ServletException {

		EmployeeService manager = new EmployeeService();
		try {
			competency1 = manager.getCompetency();
			vertical1 = manager.getVerticals();
		} catch (FetchException e) {
			System.out.println(e.getMessage());
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside GEtmethod");
		request.setAttribute("competency", competency1);
		request.setAttribute("vertical1", vertical1);
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		rd.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		EmployeeService manager = new EmployeeService();
		String msg = null;
		int status = 30;
		Employee e = null;

		String ename = request.getParameter("ename");
		String mid = request.getParameter("mid");
		String competency = request.getParameter("competency");
		String practice = request.getParameter("practice");
		String vertical = request.getParameter("vertical");

		try {
			mid = mid.toUpperCase();
			e = manager.getEmployeeById(mid);
			if (e != null) {
				msg = "Employee with " + mid + " already Exists.!";
				request.setAttribute("status", "Error");
			} else {
				e = new Employee(mid, ename, vertical, practice, competency);
				status = manager.saveEmployee(e);
				if (status > 0) {
					msg = "Employee Record Saved Successfully.!";
					request.setAttribute("status", "Success");
				} else if (status == -100) {
					msg = "Please enter valid data";
					request.setAttribute("status", "Error");
				}
			}
		} catch (PersistentException | FetchException e1) {
			msg = e1.getMessage();
			request.setAttribute("status", "Error");
		}
		request.setAttribute("message", msg);
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		rd.forward(request, response);

		out.close();

	}

}
