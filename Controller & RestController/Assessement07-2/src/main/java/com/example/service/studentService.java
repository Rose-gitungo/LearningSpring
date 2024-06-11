package com.example.service;

import java.util.List;

import com.example.model.Student;

public interface studentService {

	 List<Student> getAllstudents();
	
    Student createStudent (Student s);
	
	 void deleteStudent(Integer id);

	void updateStudent(Integer id, Student student);
}
