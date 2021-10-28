package com.neo;

import com.controller.StudentController;

public class StudentMainApp {

	public static void main(String[] args) throws Exception {
		//Get Student name and marks
		StudentController.getAllStudent().stream().forEach(
				student -> System.out.println(student.getStdName() +" : "+ student.getMarks()));
		
		System.out.println("---------------------------");
		
		StudentController.getAllStudent().stream().filter(st -> st.getMarks() >= 80 ).forEach(st -> System.out.println(st.getStdName()+" : "+st.getMarks()));

	}

}
