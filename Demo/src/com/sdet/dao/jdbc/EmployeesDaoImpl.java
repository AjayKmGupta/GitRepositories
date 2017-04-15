package com.mindtree.sdet.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mindtree.sdet.dao.EmployeesDao;
import com.mindtree.sdet.entitiy.Competency;
import com.mindtree.sdet.entitiy.Employee;
import com.mindtree.sdet.entitiy.Vertical;
import com.mindtree.sdet.exceptions.DaoException;
import com.mindtree.sdet.utility.DBUtil;

public class EmployeesDaoImpl implements EmployeesDao {

	private static Logger log = Logger.getLogger(EmployeesDaoImpl.class);

	@Override
	public int saveEmployee(Employee e) throws DaoException {

		int status = 0;
		PreparedStatement ps = null;

		try (Connection con = DBUtil.getConnnection()) {
			ps = con.prepareStatement("insert into employee values(?,?,?,?,?)");
			ps.setString(1, e.getMid());
			ps.setString(2, e.getEname());
			ps.setString(3, e.getCompetency());
			ps.setString(4, e.getPractice());
			ps.setString(5, e.getVertical());
			status = ps.executeUpdate();
			log.info("User is registered  successfully");
		} catch (SQLException ex) {
			log.error("While registration exception occured: " + ex.getMessage());
			throw new DaoException("Due to some technical error, Employee can't be registered");
		}

		return status;
	}

	@Override
	public int update(Employee e) throws DaoException {

		int status = 0;
		PreparedStatement ps = null;

		try (Connection con = DBUtil.getConnnection()) {
			ps = con.prepareStatement("update employee set ename=?, practice=?, competency=?, vertical=? where mid=?");
			ps.setString(1, e.getEname());
			ps.setString(2, e.getPractice());
			ps.setString(3, e.getCompetency());
			ps.setString(4, e.getVertical());
			ps.setString(5, e.getMid());
			status = ps.executeUpdate();
			log.info("User updated his details successfully");
		} catch (SQLException ex) {
			log.error("While editing exception occured: " + ex.getMessage());
			throw new DaoException("Due to some technical error, Employee details can't be updated");
		}

		return status;
	}

	@Override
	public int delete(String mid) throws DaoException {
		
		int status = 0;
		PreparedStatement ps = null;
		
		try (Connection con = DBUtil.getConnnection()) {
			ps = con.prepareStatement("delete from employee where mid=?");
			ps.setString(1, mid);
			status = ps.executeUpdate();
			log.info("User deleted records successfully");
		} catch (SQLException e) {
			log.error("While deleting exception occured: " + e.getMessage());
			throw new DaoException("Due to some technical error, Employee can't be removed");
		}

		return status;
	}

	@Override
	public Employee getEmployeeById(String mid) throws DaoException {

		PreparedStatement ps = null;
		Employee e = null;

		try (Connection con = DBUtil.getConnnection()) {
			ps = con.prepareStatement("select * from employee where mid=?");
			ps.setString(1, mid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e = new Employee(rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(4), rs.getString(3));
			}
			log.info("User fetched Employee details by MID");
		} catch (SQLException ex) {
			log.error("Exception occured while fetching records with MID: " + ex.getMessage());
			throw new DaoException("Due to some technical error, Employee details can't be fetched");
		}

		return e;
	}

	@Override
	public List<Employee> getAllEmployees(String competency, String vertica) throws DaoException {
String sqlQuery = null;
if((competency ==null || competency.equalsIgnoreCase("Default")) && (vertica == null|| vertica.equalsIgnoreCase("Default")))
	sqlQuery = "select * from employee order by ename";

else if(competency.equalsIgnoreCase("Default") && ! vertica.equalsIgnoreCase("Default"))
	sqlQuery = "select * from employee where vertical ='"+vertica+"' order by ename";

else if(!competency.equalsIgnoreCase("Default") && vertica.equalsIgnoreCase("Default"))
	sqlQuery = "select * from employee where competency ='"+competency+"' order by ename";


else if(!competency.equalsIgnoreCase("Default") && ! vertica.equalsIgnoreCase("Default"))
	sqlQuery = "select * from employee where competency ='"+competency+"' and vertical ='"+vertica+"' order by ename";

System.out.println(sqlQuery);
		PreparedStatement ps = null;
		List<Employee> list = new ArrayList<Employee>();
		
		try (Connection con = DBUtil.getConnnection()) {
			ps = con.prepareStatement(sqlQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee e = new Employee(rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(4), rs.getString(3));
				list.add(e);
			}
			log.info("User successfully fetched all the records");
		} catch (SQLException e) {
			System.out.println("Exception");
			log.error("Exception occured while fetching all the records: " + e.getMessage());
			throw new DaoException("Due to some technical error, Employees details can't be fetched");
		}

		return list;
	}

	@Override
	public List<Competency> getAllCompetency() throws DaoException {

		PreparedStatement ps = null;
		List<Competency> list = new ArrayList<Competency>();
		
		try (Connection con = DBUtil.getConnnection()) {
			ps = con.prepareStatement("select code,competency_name from competency order by id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getString(1));
				Competency com = new Competency(rs.getString("code"), rs.getString(2));
				list.add(com);
				//System.out.println("In dao layer");
				//System.out.println(com.getComptencyName());
			}
			log.info("User successfully fetched all the records");
		} catch (SQLException e) {
			System.out.println("Exception");
			log.error("Exception occured while fetching all the records: " + e.getMessage());
			throw new DaoException("Due to some technical error, Employees details can't be fetched");
		}

		return list;
	}
	@Override
	public List<Employee> getEmployeesByID(String mid) throws DaoException {

		List<Employee> list = new ArrayList<Employee>();
		Statement stmt = null;
		
		try (Connection con = DBUtil.getConnnection()) {
			stmt = con.createStatement();
			String sql = "select * from employee where mid like '%" + mid + "%' order by ename";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Employee e = new Employee(rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(4), rs.getString(3));
				list.add(e);
			}
			log.info("User successfully fetched all the records by matching ID");
		} catch (SQLException e) {
			log.error("Exception occured while fetching records by matching ID: " + e.getMessage());
			throw new DaoException("Some technical error occured, we apologize for inconvenience");
		}

		return list;
	}

	@Override
	public List<Vertical> getAllVerticals() throws DaoException {
		PreparedStatement ps = null;
		List<Vertical> list = new ArrayList<Vertical>();
		
		try (Connection con = DBUtil.getConnnection()) {
			ps = con.prepareStatement("select code,vert_name from verticals order by id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//System.out.println(rs.getString(1));
				Vertical vert = new Vertical(rs.getString("code"), rs.getString(2));
				list.add(vert);
				//System.out.println("In dao layer");
				System.out.println(vert.getVertName());
			}
			log.info("User successfully fetched all the records");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			log.error("Exception occured while fetching all the records: " + e.getMessage());
			throw new DaoException("Due to some technical error, Competencies can't be fetched");
		}

		return list;
	}
}
