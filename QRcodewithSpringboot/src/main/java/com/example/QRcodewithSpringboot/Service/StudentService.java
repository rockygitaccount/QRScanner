package com.example.QRcodewithSpringboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.QRcodewithSpringboot.Dao.StudentRepository;
import com.example.QRcodewithSpringboot.Model.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	
	
	@Autowired
	StudentRepository studentRepository;
	
	
	public List<Student>  getSudents(){
		return studentRepository.findAll();
	}
	
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
//	public Student findById(Long id) {
//		return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
//	}

	public Student getStudentById(Long studentId) {
		return studentRepository.findById(studentId).get();
	}

}
