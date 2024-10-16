package com.ispan.wu.controller;

import java.io.IOException;

import com.ispan.wu.Constants;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteAll")
public class DeleteAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DeleteAll() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute(Constants.getWorkString(), Constants.getDeleteAllString());
		request.getRequestDispatcher(Constants.getAtmDaoImpl()).include(request, response);
		
		request.getRequestDispatcher(Constants.getQueryAllJsp()).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
