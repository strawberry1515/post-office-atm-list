package com.ispan.wu.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.naming.InvalidNameException;

import com.ispan.wu.dao.result.Result;
import com.ispan.wu.dao.result.ResultImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UploadFileDefinite")
//@MultipartConfig(location = "C:/Servlet/workspace_midtern/midtern/src/main/webapp/uploads/")
@MultipartConfig(location = "c:/midterm/workspace/midtern/src/main/webapp/uploads/")
public class UploadFileDefinite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadFileDefinite() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new ResultImpl();
		request.setAttribute("result", result);

		Part part = request.getPart("file");
		String filename = part.getSubmittedFileName();
		String fileNameExtension = filename.substring(filename.length()-4);
		if(".csv".equals(fileNameExtension)||".txt".equals(fileNameExtension)) {
		request.setAttribute("fileName", filename);
//		File file=new File("c:/midterm/workspace/midtern/src/main/webapp/uploads/"+filename);
//		if(file.exists()) {file.delete();}
		part.write(filename);
		result.setMsg("格式正確");
		result.setIsSuccess(true);
		result.setException(null);
		}else {
			result.setMsg("檔案格式錯誤!");
			result.setException(new InvalidNameException());
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
