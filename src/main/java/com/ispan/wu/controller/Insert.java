package com.ispan.wu.controller;

import java.io.IOException;

import com.ispan.wu.Constants;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Insert() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		request.setAttribute(Constants.getWorkString(), Constants.getInsertFromFileString());
		request.setAttribute(Constants.getWorkString(), Constants.getInsertString());
		request.getRequestDispatcher(Constants.getAtmDaoImpl()).include(request, response);
		
		request.getRequestDispatcher(Constants.getQueryByIdJsp()).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
