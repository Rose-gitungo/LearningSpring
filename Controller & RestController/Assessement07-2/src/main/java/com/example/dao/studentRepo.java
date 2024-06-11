package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Student;

public interface studentRepo extends JpaRepository<Student,Integer> {

}
