package com.sdet.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.sdet.dao.EmployeesDao;
import com.sdet.entitiy.Competency;
import com.sdet.entitiy.Employee;
import com.sdet.entitiy.Vertical;
import com.sdet.exceptions.DaoException;
import com.sdet.utility.DBUtil;


public class EmployeesDaoImpl implements EmployeesDao
{

    private static Logger log = Logger.getLogger( "EmployeesDaoImpl" );

    @Override
    public int saveEmployee( Employee e ) throws DaoException
    {

        int status = 0;

        try ( Connection con = DBUtil.getConnnection();
            PreparedStatement ps = con.prepareStatement( "insert into employee values(?,?,?,?,?)" ); ) {

            ps.setString( 1, e.getMid() );
            ps.setString( 2, e.getEname() );
            ps.setString( 3, e.getCompetency() );
            ps.setString( 4, e.getPractice() );
            ps.setString( 5, e.getVertical() );
            status = ps.executeUpdate();
            log.info( "User is registered  successfully" );
        } catch ( SQLException ex ) {
            log.severe( "While registration exception occured: " + ex.getMessage() );
            throw new DaoException( "Due to some technical error, Employee can't be registered" );
        }

        return status;
    }


    @Override
    public int update( Employee e ) throws DaoException
    {

        int status = 0;

        try ( Connection con = DBUtil.getConnnection();
            PreparedStatement ps = con
                .prepareStatement( "update employee set ename=?, practice=?, competency=?, vertical=? where mid=?" ); ) {
            ps.setString( 1, e.getEname() );
            ps.setString( 2, e.getPractice() );
            ps.setString( 3, e.getCompetency() );
            ps.setString( 4, e.getVertical() );
            ps.setString( 5, e.getMid() );
            status = ps.executeUpdate();
            log.info( "User updated his details successfully" );
        } catch ( SQLException ex ) {
            log.severe( "While editing exception occured: " + ex.getMessage() );
            throw new DaoException( "Due to some technical error, Employee details can't be updated" );
        }

        return status;
    }


    @Override
    public int delete( String mid ) throws DaoException
    {

        int status = 0;

        try ( Connection con = DBUtil.getConnnection();
            PreparedStatement ps = con.prepareStatement( "delete from employee where mid=?" ); ) {
            ps.setString( 1, mid );
            status = ps.executeUpdate();
            log.info( "User deleted records successfully" );
        } catch ( SQLException e ) {
            log.severe( "While deleting exception occured: " + e.getMessage() );
            throw new DaoException( "Due to some technical error, Employee can't be removed" );
        }

        return status;
    }


    @Override
    public Employee getEmployeeById( String mid ) throws DaoException
    {

        Employee e = null;

        try ( Connection con = DBUtil.getConnnection();
            PreparedStatement ps = con.prepareStatement( "select * from employee where mid=?" ); ) {
            ps.setString( 1, mid );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                e = new Employee( rs.getString( 1 ), rs.getString( 2 ), rs.getString( 5 ), rs.getString( 4 ),
                    rs.getString( 3 ) );
            }
            log.info( "User fetched Employee details by MID" );
        } catch ( SQLException ex ) {
            log.severe( "Exception occured while fetching records with MID: " + ex.getMessage() );
            throw new DaoException( "Due to some technical error, Employee details can't be fetched" );
        }

        return e;
    }


    @Override
    public List<Employee> getAllEmployees( String competency, String vertica ) throws DaoException
    {
        String sqlQuery = null;
        if ( ( competency == null || competency.equalsIgnoreCase( "Default" ) )
            && ( vertica == null || vertica.equalsIgnoreCase( "Default" ) ) )
            sqlQuery = "select * from employee order by ename";

        else if ( competency.equalsIgnoreCase( "Default" ) && !vertica.equalsIgnoreCase( "Default" ) )
            sqlQuery = "select * from employee where vertical ='" + vertica + "' order by ename";

        else if ( !competency.equalsIgnoreCase( "Default" ) && vertica.equalsIgnoreCase( "Default" ) )
            sqlQuery = "select * from employee where competency ='" + competency + "' order by ename";


        else if ( !competency.equalsIgnoreCase( "Default" ) && !vertica.equalsIgnoreCase( "Default" ) )
            sqlQuery = "select * from employee where competency ='" + competency + "' and vertical ='" + vertica
                + "' order by ename";

        List<Employee> list = new ArrayList<>();

        try ( Connection con = DBUtil.getConnnection();
            PreparedStatement ps = con.prepareStatement( sqlQuery );
            ResultSet rs = ps.executeQuery(); ) {
            while ( rs.next() ) {
                Employee e = new Employee( rs.getString( 1 ), rs.getString( 2 ), rs.getString( 5 ), rs.getString( 4 ),
                    rs.getString( 3 ) );
                list.add( e );
            }
            log.info( "User successfully fetched all the records" );
        } catch ( SQLException e ) {
            log.severe( "Exception occured while fetching all the records: " + e.getMessage() );
            throw new DaoException( "Due to some technical error, Employees details can't be fetched" );
        }

        return list;
    }


    @Override
    public List<Competency> getAllCompetency() throws DaoException
    {

        List<Competency> list = new ArrayList<>();

        try ( Connection con = DBUtil.getConnnection();
            PreparedStatement ps = con.prepareStatement( "select code,competency_name from competency order by id" );
            ResultSet rs = ps.executeQuery(); ) {
            while ( rs.next() ) {
                Competency com = new Competency( rs.getString( "code" ), rs.getString( 2 ) );
                list.add( com );
            }
            log.info( "User successfully fetched all the records" );
        } catch ( SQLException e ) {
            log.severe( "Exception occured while fetching all the records: " + e.getMessage() );
            throw new DaoException( "Due to some technical error, Employees details can't be fetched" );
        }

        return list;
    }


    @Override
    public List<Employee> getEmployeesByID( String mid ) throws DaoException
    {

        List<Employee> list = new ArrayList<>();
        String sql = "select * from employee where mid like '%" + mid + "%' order by ename";

        try ( Connection con = DBUtil.getConnnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql ); ) {

            while ( rs.next() ) {
                Employee e = new Employee( rs.getString( 1 ), rs.getString( 2 ), rs.getString( 5 ), rs.getString( 4 ),
                    rs.getString( 3 ) );
                list.add( e );
            }
            log.info( "User successfully fetched all the records by matching ID" );
        } catch ( SQLException e ) {
            log.severe( "Exception occured while fetching records by matching ID: " + e.getMessage() );
            throw new DaoException( "Some technical error occured, we apologize for inconvenience" );
        }

        return list;
    }


    @Override
    public List<Vertical> getAllVerticals() throws DaoException
    {
        List<Vertical> list = new ArrayList<>();

        try ( Connection con = DBUtil.getConnnection();
            PreparedStatement ps = con.prepareStatement( "select code,vert_name from verticals order by id" );
            ResultSet rs = ps.executeQuery(); ) {
            while ( rs.next() ) {
                Vertical vert = new Vertical( rs.getString( "code" ), rs.getString( 2 ) );
                list.add( vert );
            }
            log.info( "User successfully fetched all the records" );
        } catch ( SQLException e ) {
            log.severe( "Exception occured while fetching all the records: " + e.getMessage() );
            throw new DaoException( "Due to some technical error, Competencies can't be fetched" );
        }

        return list;
    }
}
