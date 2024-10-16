package com.ispan.wu.controller;

import java.io.IOException;

import com.ispan.wu.Constants;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/QueryObscureByName")
public class QueryObscureByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public QueryObscureByName() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cityNo = Integer.parseInt(request.getParameter("cityNo"));
		request.setAttribute("cityNo", cityNo);
		
		request.setAttribute(Constants.getWorkString(), Constants.getQueryObscureByNameString());
		request.getRequestDispatcher(Constants.getAtmDaoImpl()).include(request, response);
		
		request.getRequestDispatcher(Constants.getQueryAllJsp()).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
