package com.mindtree.sdet.dao;

import java.util.List;

import com.mindtree.sdet.entitiy.Competency;
import com.mindtree.sdet.entitiy.Employee;
import com.mindtree.sdet.entitiy.Vertical;
import com.mindtree.sdet.exceptions.DaoException;

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
