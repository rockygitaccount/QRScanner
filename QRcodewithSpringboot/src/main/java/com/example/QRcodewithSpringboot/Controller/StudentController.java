package com.example.QRcodewithSpringboot.Controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QRcodewithSpringboot.Model.Student;
import com.example.QRcodewithSpringboot.Service.StudentService;
import com.example.QRcodewithSpringboot.Utils.QRCodeGenerator;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/getAllStudent")
	public ResponseEntity<List<Student>> getStudents() throws WriterException, IOException{
		List<Student> students =studentService.getSudents();
		if(students.size()!=0) {
			for(Student student :students) {
				QRCodeGenerator.generatQRCode(student);
			}
		}
		return ResponseEntity.ok(studentService.getSudents());
	}

	@PostMapping("/addStudent")
	    public ResponseEntity<Student> addStudent(@RequestBody Student student) throws WriterException, IOException {
	        Student addedStudent = studentService.addStudent(student);
	        QRCodeGenerator.generatQRCode(addedStudent); // Generate QR code for the added student
	        return ResponseEntity.ok(addedStudent);
	    }
	
//	@GetMapping("/{id}")
//	public Student findById(@PathVariable Long id) {
//		return studentService.findById(id);
//	}
}
