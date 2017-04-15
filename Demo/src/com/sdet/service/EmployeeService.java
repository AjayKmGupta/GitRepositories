package com.mindtree.sdet.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mindtree.sdet.dao.EmployeesDao;
import com.mindtree.sdet.dao.jdbc.EmployeesDaoImpl;
import com.mindtree.sdet.entitiy.Competency;
import com.mindtree.sdet.entitiy.Employee;
import com.mindtree.sdet.entitiy.Vertical;
import com.mindtree.sdet.exceptions.DaoException;
import com.mindtree.sdet.exceptions.DeleteException;
import com.mindtree.sdet.exceptions.FetchException;
import com.mindtree.sdet.exceptions.PersistentException;
import com.mindtree.sdet.exceptions.UpdateException;

public class EmployeeService {

	public int saveEmployee(Employee e) throws PersistentException {

		int status = -100;
		boolean isValidMid = false;
		boolean isValidName = false;
		boolean isValidPrac = false;
		boolean isValidVer = false;
		try {
			EmployeesDao dao = new EmployeesDaoImpl();
			isValidMid = validateMid(e.getMid());
			isValidName = isValidText(e.getEname());
			isValidPrac = isValidText(e.getPractice());
			isValidVer = isValidText(e.getVertical());

			if (isValidMid && isValidName && isValidPrac && isValidVer)
				status = dao.saveEmployee(e);

		} catch (DaoException e1) {
			throw new PersistentException(e1.getMessage());
		}
		return status;
	}

	public int updateEmployee(Employee e) throws UpdateException {

		int status = 0;
		boolean isValidMid = false;
		boolean isValidName = false;
		boolean isValidPrac = false;
		boolean isValidVer = false;
		try {
			EmployeesDao dao = new EmployeesDaoImpl();
			isValidMid = validateMid(e.getMid());
			isValidName = isValidText(e.getEname());
			isValidPrac = isValidText(e.getPractice());
			isValidVer = isValidText(e.getVertical());

			if (isValidMid && isValidName && isValidPrac && isValidVer)
				status = dao.update(e);
			else
				status = -100;
		} catch (DaoException e1) {
			throw new UpdateException(e1.getMessage());
		}
		return status;
	}

	public int deleteEmployee(String mid) throws DeleteException {

		int status;

		try {
			EmployeesDao dao = new EmployeesDaoImpl();
			status = dao.delete(mid);
		} catch (DaoException e1) {
			throw new DeleteException(e1.getMessage());
		}
		return status;
	}

	public Employee getEmployeeById(String mid) throws FetchException {

		Employee emp = null;
		boolean isValidMid = false;
		try {
			EmployeesDao dao = new EmployeesDaoImpl();
			isValidMid = validateMid(mid);
			if (isValidMid)
				emp = dao.getEmployeeById(mid);
		} catch (DaoException e) {
			throw new FetchException(e.getMessage());
		}
		return emp;
	}

	public List<Employee> getAllEmployees(String competency, String vertica) throws FetchException {

		List<Employee> list;
		try {
			EmployeesDao dao = new EmployeesDaoImpl();
			list = dao.getAllEmployees(competency,vertica);
		} catch (DaoException e) {
			throw new FetchException(e.getMessage());
		}
		return list;
	}

	public List<Competency> getCompetency() throws FetchException {

		List<Competency> list;
		try {
			EmployeesDao dao = new EmployeesDaoImpl();
			list = dao.getAllCompetency();
		} catch (DaoException e) {
			throw new FetchException(e.getMessage());
		}
		return list;
	}
	public List<Employee> getEmployeesByID(String mid) throws FetchException {

		List<Employee> list = null;
		try {
			EmployeesDao dao = new EmployeesDaoImpl();
			list = dao.getEmployeesByID(mid);
		} catch (DaoException e) {
			throw new FetchException(e.getMessage());
		}
		return list;
	}

	public boolean validateMid(String mid) {

		boolean isValidMid = true;

		if (!Pattern.matches("[M][0-9]{7}", mid))
			isValidMid = false;

		return isValidMid;
	}

	private boolean isValidText(String name) {
		boolean isValidName;
		Pattern p = Pattern.compile("[a-zA-Z\\s']+");
		Matcher m = p.matcher(name);
		isValidName = m.matches();
		return isValidName;
	}

	public List<Vertical> getVerticals() throws FetchException {
		List<Vertical> list;
		try {
			EmployeesDao dao = new EmployeesDaoImpl();
			list = dao.getAllVerticals();
		} catch (DaoException e) {
			throw new FetchException(e.getMessage());
		}
		return list;
	}

}
