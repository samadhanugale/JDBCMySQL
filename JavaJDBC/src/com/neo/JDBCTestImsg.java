package com.neo;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.imageio.stream.FileImageOutputStream;

public class JDBCTestImsg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final String DB_url = "jdbc:mysql://localhost:3306/mydb";
		final String DB_user = "root";
		final String DB_pass = "95951214@Sam";

		Connection conn = null;
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
			
			String createTableQuerry = "CREATE TABLE IF NOT EXISTS Profile(pid INT AUTO_INCREMENT PRIMARY KEY,photo MEDIUMBLOB NOT NULL)";
			pst = conn.prepareStatement(createTableQuerry);
			System.out.println("Table Creation : "+pst.executeUpdate());
			pst.close();
			
			File pic = new File("D:\\img.jpg");
			FileInputStream fis = new FileInputStream(pic);
			System.out.println("Pic Size in bytes : "+pic.length());
			
			  pst = conn.prepareStatement("INSERT INTO profile(photo) VALUES(?)");
			  pst.setBlob(1, fis,(int) pic.length());
			  System.out.println("Image Insertion : "+pst.executeUpdate()); pst.close();
			 
			pst = conn.prepareStatement("SELECT * FROM profile  WHERE pid= ?");
			pst.setInt(1, 2);
			rs = pst.executeQuery();
			
			Blob blob;
			byte b[];
			if(rs.next()) {
				File file = new File("D:\\image"+rs.getInt(1)+".jpg");
				FileImageOutputStream fos = new FileImageOutputStream(file);
				blob = rs.getBlob(2);
				b = blob.getBytes(1,(int) blob.length());
				fos.write(b);
				System.out.println("Image Downloaded as : "+ file.getPath());
				fos.close();
			}
			pst.close();
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {// Close resources and connection
			try {

				if (rs != null) {
					rs.close();
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
