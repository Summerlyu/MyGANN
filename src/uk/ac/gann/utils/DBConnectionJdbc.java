package uk.ac.gann.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class DBConnectionJdbc {
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private static Connection con = null;
	private static String driveName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url = "jdbc:sqlserver://localhost:1433; databaseName = SaleRecordDB";
	private static String userName = "securityuser";
	private static String password = "password";
	
	static{
		try{
			Class.forName(driveName);
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public static Connection getCon() {
		try{
			con = DriverManager.getConnection(url, userName, password);
		}catch (SQLException e){
			System.out.println(e.getMessage() );
			e.printStackTrace();
		}
		return con;
	}
	
	public static void endConnection(){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
