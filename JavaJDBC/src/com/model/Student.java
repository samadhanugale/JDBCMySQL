package com.model;

public class Student {
	private int stdId;
	private String stdName;
	private String stdCourse;
	private int marks;
	
	
	public Student(int stdId, String stdName, String stdCourse, int marks) {
		super();
		this.stdId = stdId;
		this.stdName = stdName;
		this.stdCourse = stdCourse;
		this.marks = marks;
	}
	public int getStdId() {
		return stdId;
	}
	public void setStdId(int stdId) {
		this.stdId = stdId;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public String getStdCourse() {
		return stdCourse;
	}
	public void setStdCourse(String stdCourse) {
		this.stdCourse = stdCourse;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	
}
