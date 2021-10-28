package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.model.Student;

public class StudentController {
	public static List<Student> getAllStudent() throws Exception{
		List<Student> students = new ArrayList<Student>();
		Connection con = JDBCProperties.getConnection();
		try {
			String querry = "SELECT * FROM mydb.student";
			PreparedStatement pst = con.prepareStatement(querry);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Student student = new Student(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getInt(4));
				students.add(student);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		return students;
	}
}
