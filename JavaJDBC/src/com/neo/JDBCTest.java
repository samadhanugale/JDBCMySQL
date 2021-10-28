package com.neo;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;









public class JDBCTest {

	public static void main(String[] args) {

		final String DB_url = "jdbc:mysql://localhost:3306/mydb";
		final String DB_user = "root";
		final String DB_pass = "95951214@Sam";

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		CallableStatement cst = null;

		try {
			// Register Drivers
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");

			// Create a Connection
			conn = DriverManager.getConnection(DB_url, DB_user, DB_pass);
			System.out.println("Connection Established!");

			// Use Statement object to perform querries
			st = conn.createStatement();

			//Create a Table
//			String createTableQuerry = "CREATE TABLE IF NOT EXISTS Sample(sid INT AUTO_INCREMENT PRIMARY KEY,sname VARCHAR(50) NOT NULL)";
//			System.out.println("Querry Execution : " + st.executeUpdate(createTableQuerry));

			
			//Insert Records in table
//			String insertQuerry = "INSERT INTO sample(sname) VALUES('HTML'),('cpp')";
//			System.out.println("Insert Records : "+st.executeUpdate(insertQuerry));
			
			//Update Records
//			String updateRecords = "UPDATE sample SET sname = 'SQL' WHERE sid = 3";
//			System.out.println("Update Records : "+st.executeUpdate(updateRecords));
			
			/*
			 * String deleteRecords = "DELETE FROM sample WHERE sid = 5";
			 * //System.out.println("Deleting Records : "+st.executeUpdate(deleteRecords));
			 * 
			 * //Fetch records String selectQuerry = "SELECT * FROM sample"; rs =
			 * st.executeQuery(selectQuerry); System.out.println("Fetch Records : ");
			 * while(rs.next()) {
			 * System.out.println(rs.getInt("sid")+"  :  "+rs.getString("sname")); }
			 * rs.close();
			 * 
			 * //Using prepared Statements -- Pre-compiled SQL Statements String
			 * selectPreparedQuerry = "SELECT * FROM sample WHERE sid=?"; pst =
			 * conn.prepareStatement(selectPreparedQuerry); pst.setInt(1, 2); // Index,value
			 * rs = pst.executeQuery(); if(rs.next()) {
			 * System.out.println("selectPreparedQuerry :");
			 * System.out.println(rs.getInt("sid")+"  :  "+rs.getString("sname")); }
			 * pst.setInt(1, 1); rs = pst.executeQuery(); if(rs.next()) {
			 * System.out.println(rs.getInt("sid")+"  :  "+rs.getString("sname")); }
			 * pst.close(); //Using pst for insertion String insertPrepareQuerry =
			 * "INSERT INTO sample(sname) VALUES(?)"; pst =
			 * conn.prepareStatement(insertPrepareQuerry);
			 * 
			 * pst.setString(1, "React"); System.out.println("insertPrepareQuerry : "+
			 * pst.executeUpdate());
			 * 
			 */
			
			//Batch Insertion using Array
			/*
			 * String[] names = new String[] {"Jonas","Hannah","Ulrich","Noah"}; String
			 * insertPrepareQuerry = "INSERT INTO sample(sname) VALUES(?)"; pst =
			 * conn.prepareStatement(insertPrepareQuerry); for (String name : names) {
			 * pst.setString(1, name); pst.addBatch(); }
			 * System.out.println("Batch Records Added : "+pst.executeBatch().length);
			 * pst.close();
			 */
			
			//Calling Stored Procedures
			/*
			 * String prepareCallQuerry = "CALL getAllEmpAndDeptByNo(?)";
			 * 
			 * cst = conn.prepareCall(prepareCallQuerry); cst.setInt(1, 20); rs =
			 * cst.executeQuery(); while(rs.next()) {
			 * System.out.println("Emp Name : "+rs.getString("ename") +
			 * " Emp No : "+rs.getInt("empno")+" Location : "+rs.getString("loc")
			 * +"Dept No : "+rs.getInt("deptno")); }
			 */
			
			//Getting meta data from Resultset : Getting column name and type
			String selectQuerry2 = "SELECT * FROM sample";
			rs = st.executeQuery(selectQuerry2);
			ResultSetMetaData rsmd = rs.getMetaData();
			int col_counter = rsmd.getColumnCount();
			System.out.println("No. of columns : "+col_counter);
			
			for (int i = 1; i <= col_counter; i++) {
				System.out.println(rsmd.getColumnName(i) + " : "+rsmd.getColumnTypeName(i));
			}
			rs.close();
			
			//Getting Metadata using database : Databasemetadata
			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println("DriverName : "+dbmd.getDriverName());
			System.out.println("Driver Driver Version : "+dbmd.getDriverVersion());
			System.out.println("User Name : "+dbmd.getUserName());
			System.out.println("Database Product Version : "+dbmd.getDatabaseProductVersion());
			
			
			//Getting table names from current database
			System.out.println("---------Table Names----------");
			String tables[] = {"TABLE"};
			rs = dbmd.getTables(null, null, null, tables);
			//rs = dbmd.getProcedures(conn.getCatalog(), null, null);
			while(rs.next()) {
				System.out.println(rs.getString(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// Close resources and connection
			try {
				if (pst != null) {
					pst.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
					System.out.println("-- Connection Closed --");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
