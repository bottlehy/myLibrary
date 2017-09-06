package org.lanqiao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection conn=null;
	private static final String URL="jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String USER="scott";
	private static final String PASSWORD="tiger";
	private static final String Driver="oracle.jdbc.driver.OracleDriver";
	public static Connection getConn() {
		try {
			Class.forName(Driver);
			conn=DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
