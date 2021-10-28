package com.controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCProperties {
	public static String driverclass = null;
	public static String url = null;
	public static String username = null;
	public static String password = null;
	
	static Connection con = null;

	 static void loadProperties() {
		try {
			FileInputStream fis = new FileInputStream("resources/database.properties");
			Properties prop = new Properties();
			prop.load(fis);
			driverclass = prop.getProperty("MySQLJdbc.driver");
			url = prop.getProperty("MySQLJdbc.url");
			username = prop.getProperty("MySQLJdbc.user");
			password = prop.getProperty("MySQLJdbc.pass");
			prop.clear();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		loadProperties();
		System.out.println(driverclass);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
	}


public static Connection getConnection() {
	try {
		loadProperties();
		Class.forName(driverclass);
		con = DriverManager.getConnection(url,username,password);
		System.out.println("-- Connection Established --");
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return con;
	
}
}