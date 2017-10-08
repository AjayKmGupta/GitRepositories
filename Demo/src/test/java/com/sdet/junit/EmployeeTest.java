package com.sdet.junit;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sdet.entitiy.Employee;
import com.sdet.exceptions.DeleteException;
import com.sdet.exceptions.FetchException;
import com.sdet.exceptions.PersistentException;
import com.sdet.exceptions.UpdateException;
import com.sdet.service.EmployeeService;

public class EmployeeTest {

@Test
public void testGetEmployeeById() throws FetchException {
	Employee empExp = new Employee("M1030571", "Ajay Kumar Gupta", "RCM", "SDET", "C2");
	EmployeeService serviceObj =  new EmployeeService();
	Employee empAct = serviceObj.getEmployeeById("M1030571");
	System.out.println(empAct.getMid() + " " + empAct.getEname() + " " + empAct.getVertical() + " " + empAct.getPractice() + " " + empAct.getCompetency());
    Assert.assertEquals(empExp, empAct);
}
	
@Test
public void testValidMid() {
	String validMid = "M1030571";
	EmployeeService serObj = new EmployeeService();
	boolean actualStatus = serObj.validateMid(validMid);
	Assert.assertTrue(actualStatus);
}

@Test
public void testInvalidMid() {
	String validMid = "D1030571";
	EmployeeService serObj = new EmployeeService();
	boolean actualStatus = serObj.validateMid(validMid);
	Assert.assertFalse(actualStatus);
}

@Test
public void testFetchEmployeesWithMatchingString() throws FetchException {
	EmployeeService serObj = new EmployeeService();
	List<Employee> list = null;
	list = serObj.getEmployeesByID("M103");
	Assert.assertNotNull(list);
}

@Test
public void testFetchEmployeesWithInvalidString() throws FetchException {
	EmployeeService serObj = new EmployeeService();
	Employee emp = serObj.getEmployeeById("D103");
	Assert.assertNull(emp);
}

@Test
public void testGetEmployeeByIdForAssertSame() throws DeleteException {
	EmployeeService serviceObj =  new EmployeeService();
	int status = serviceObj.deleteEmployee("M1027320");
	Assert.assertSame(1, status);
}

@Test
public void testUpdateEmployee() throws UpdateException {
	int status = 0;
	Employee emp = new Employee("M1030524", "Sonali Soni", "HTMS", "Testing", "C1");
	EmployeeService serviceObj =  new EmployeeService();
	status = serviceObj.updateEmployee(emp);
	Assert.assertNotSame(2, status);
}

@Test (expected=PersistentException.class)
public void testAddingExistingEmployee() throws PersistentException {
	
	Employee emp = new Employee("M1030571", "Ajay Kumar Gupta", "RCM", "Testing", "C1");
	EmployeeService serviceObj =  new EmployeeService();
	serviceObj.saveEmployee(emp);	
}

@Test
public void testAddingEmployee() throws PersistentException {	
	Employee emp = new Employee("M1027320", "Santosh Bhoje", "RCM", "Testing", "C1");
	EmployeeService serviceObj =  new EmployeeService();
	int status = serviceObj.saveEmployee(emp);
	Assert.assertEquals(1, status);
}
}
