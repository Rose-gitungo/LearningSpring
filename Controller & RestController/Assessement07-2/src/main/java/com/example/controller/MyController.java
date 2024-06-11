package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.Student;
import com.example.service.studentService;

@Controller
public class MyController {
	
	@Autowired
	studentService ss;
	
	@GetMapping("/")
	public String getPage() {
		return "student";
	}

	@PostMapping("createStudent")
	public Student createStudent(@RequestBody Student student) {
	return ss.createStudent(student);
	}
	 
	@GetMapping("getAll")
    public List<Student> getAllStudents() {
	List<Student> studentList = ss.getAllstudents();
	return studentList;
}
	
	@PutMapping("updateStudent/{id}")
	public void updateStudent(@PathVariable Integer id,@RequestBody Student student) {
		ss.updateStudent(id, student);
	}
	
	@DeleteMapping("deleteStudent/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		 ss.deleteStudent(id);
	}
}
