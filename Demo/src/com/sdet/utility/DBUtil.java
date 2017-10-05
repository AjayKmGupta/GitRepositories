package com.sdet.utility;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sdet.exceptions.DaoException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DBUtil {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/sdet";
	public static final String USR = "root";
	public static final String PWD = "Welcome123";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static Connection getConnnection() throws DaoException {
		Connection con = null;
		try {
			con = (Connection) DriverManager.getConnection(URL, USR, PWD);
		} catch (SQLException e) {
			throw new DaoException("Connection can't be created");
		}
		return con;
	}

	public static void releaseConnection(Connection con) throws DaoException {

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DaoException("Connection can't be closed");
			}
		}
	}

	public static void releaseResultSet(ResultSet rs) throws DaoException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DaoException("Result Set can not be closed");
			}
		}
	}

	public static void releaseStatement(Statement stmt) throws DaoException {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DaoException("Statement can't be released");
			}
		}
	}

	public static void releasePreparedStatement(PreparedStatement ps) throws DaoException {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new DaoException("Prepared statement can't be closed");
			}
		}
	}
}
