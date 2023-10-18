package com.example.QRcodewithSpringboot.Utils;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.example.QRcodewithSpringboot.Model.Student;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class QRCodeGenerator {

	public static void generatQRCode(Student student) throws WriterException, IOException {
		
		String qrcodepath ="D:\\twilio\\";
		String qrCodeName = qrcodepath+student.getFirstName()+student.getId()+ "-QRCODE.png";
		
		String qrCodeContent = "StudentID: " + student.getId(); 
		var qrCodeWriter = new QRCodeWriter();
				
		BitMatrix bitMatrix = qrCodeWriter.encode(
			    " ID: " + student.getId() + "\n" +
			    "Firstname: " + student.getFirstName() + "\n" +
			    "Lastname: " + student.getLastName() + "\n" +
			    "Email: " + student.getEmail() + "\n" +
			    "Mobile: " + student.getMobile(),
			    BarcodeFormat.QR_CODE, 400, 400);
		Path path =FileSystems.getDefault().getPath(qrCodeName);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

	}
}
