package com.sdet.dao;

import java.util.List;

import com.sdet.entitiy.Competency;
import com.sdet.entitiy.Employee;
import com.sdet.entitiy.Vertical;
import com.sdet.exceptions.DaoException;

public interface EmployeesDao {

	int saveEmployee(Employee e) throws DaoException;

	int update(Employee e) throws DaoException;

	int delete(String mid) throws DaoException;

	Employee getEmployeeById(String mid) throws DaoException;

	List<Employee> getAllEmployees(String competency, String vertica) throws DaoException;

	List<Employee> getEmployeesByID(String mid) throws DaoException;

	List<Competency> getAllCompetency() throws DaoException;

	List<Vertical> getAllVerticals() throws DaoException;
	
	

}
