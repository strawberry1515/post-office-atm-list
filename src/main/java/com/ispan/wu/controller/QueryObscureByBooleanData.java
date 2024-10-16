package com.ispan.wu.controller;

import java.io.IOException;

import com.ispan.wu.Constants;

//import com.ispan.result.Result;
//import com.ispan.result.ResultImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/QueryObscureByBooleanData")
public class QueryObscureByBooleanData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public QueryObscureByBooleanData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute(Constants.getWorkString(), "queryObscureByBooleanData");
		request.getRequestDispatcher(Constants.getAtmDaoImpl()).include(request, response);
		
//		request.getRequestDispatcher("TEST2.jsp").forward(request, response);
		request.getRequestDispatcher(Constants.getQueryAllJsp()).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
