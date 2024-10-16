package com.ispan.wu.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UploadFile")
@MultipartConfig()
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public UploadFile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

		 Path uploadPath = Paths.get(getServletContext().getRealPath("/upload/"));
		 System.out.println(uploadPath);
		 System.out.println(fileName);
		 if (!Files.exists(uploadPath)) {
			 Files.createDirectories(uploadPath);
		 }
	        Path filePath = uploadPath.resolve(fileName);
	        try (InputStream fileContent = filePart.getInputStream()) {
	            Files.copy(fileContent, filePath);
	        }
		 
	        request.setAttribute("filePath", uploadPath+"\\"+fileName);
		System.out.println(uploadPath);
		System.out.println(Files.exists(uploadPath));
//		
//		Part part = request.getPart("file");
//		String filename = part.getSubmittedFileName();
//		System.out.println(filename);
//		part.write(filename);
//		
		
		
//		
//		
//		
//		request.setAttribute(Constants.getWorkString(), Constants.getInsertFromFileString());
////		request.setAttribute(Constants.getWorkString(), Constants.getInsertString());
//		request.getRequestDispatcher(Constants.getAtmDaoImpl()).include(request, response);
//		
//		request.getRequestDispatcher(Constants.getQueryAllJsp()).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
