package com.example.QRcodewithSpringboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QRcodewithSpringboot.Model.Student;
import com.example.QRcodewithSpringboot.Service.StudentService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
public class QRScanController {

	@Autowired
    StudentService studentService;



    @PostMapping("/scanQR")
    public ResponseEntity<String> scanQRCode(@RequestBody String studentId) {
        try {
            Long sId = extractStudentIdFromQR(studentId);
            Student student = studentService.getStudentById(sId);

            if (student != null) {
                boolean smsSent = sendSMSToStudent(student);
                if (smsSent) {
                    return ResponseEntity.ok("SMS sent successfully to " + student.getFirstName());
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Failed to send SMS to " + student.getFirstName());
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the QR code: " + e.getMessage());
        }
    }

//    private Long extractStudentIdFromQR(String qrCodeContent) {
//        // Parse the JSON payload to get the student ID
//        JsonObject jsonObject = new JsonParser().parse(qrCodeContent).getAsJsonObject();
//        String studentIdString = jsonObject.get("StudentID").getAsString();
//        return Long.parseLong(studentIdString.split(": ")[1]);
//    }
    private Long extractStudentIdFromQR(String studentId) {
    	studentId = studentId.replaceAll("[{}\"]", "");
        String[] parts = studentId.split(":");
        return Long.parseLong(parts[1].trim());
    }



    private boolean sendSMSToStudent(Student student) {
        return true;
    }
}
