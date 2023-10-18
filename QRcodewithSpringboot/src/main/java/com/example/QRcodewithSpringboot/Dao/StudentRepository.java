package com.example.QRcodewithSpringboot.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.QRcodewithSpringboot.Model.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {

	
}
