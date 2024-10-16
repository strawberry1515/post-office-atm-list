package com.ispan.wu.controller;

import java.io.IOException;

import com.ispan.wu.Constants;
import com.ispan.wu.dao.result.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InsertFile")
//@MultipartConfig(location = "C:/Servlet/workspace_midtern/midtern/src/main/webapp/uploads/")
@MultipartConfig(location = "c:/midterm/workspace/midtern/src/main/webapp/uploads/")
public class InsertFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertFile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("UploadFileDefinite").include(request, response);
//		request.getRequestDispatcher(Constants.getUploadFile()).include(request, response);

		Result result = (Result) request.getAttribute("result");
		if (result == null || result.getException() != null) {
			request.getRequestDispatcher("Insert.jsp").forward(request, response);
		} else {
			request.setAttribute(Constants.getWorkString(), Constants.getInsertFromFileString());
			request.getRequestDispatcher(Constants.getAtmDaoImpl()).include(request, response);

			request.getRequestDispatcher(Constants.getQueryAllJsp()).forward(request, response);
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
