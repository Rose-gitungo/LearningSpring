package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.studentRepo;
import com.example.model.Student;

@Service
public class studentServiceImpl implements studentService {

	@Autowired
	studentRepo sr;
	
	@Override
	public List<Student> getAllstudents() {
		return sr.findAll();
	}

	@Override
	public Student createStudent(Student s) {
		sr.save(s);
		return s;
	}

	@Override
	public void updateStudent(Integer id,Student student) {
		Optional<Student> optionalStudent = sr.findById(id);
		if (optionalStudent.isPresent()) {
			Student s = optionalStudent.get();
			s.setName(student.getName());
			s.setAddress(student.getAddress());
			sr.save(s);
		}else {
			 throw new RuntimeException("Student not found with ID: " + id);
		}
	}

	@Override
	public void deleteStudent(Integer id) {
		sr.deleteById(id);
	}

}
