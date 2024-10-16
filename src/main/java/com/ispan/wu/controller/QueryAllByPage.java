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

@WebServlet("/QueryAllByPage")
public class QueryAllByPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public QueryAllByPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page=request.getParameter("page");
		request.setAttribute("page", page);
		
		request.setAttribute(Constants.getWorkString(), "QueryAllByPage");
		request.getRequestDispatcher(Constants.getAtmDaoImpl()).include(request, response);
		
//		request.getRequestDispatcher("TEST2.jsp").forward(request, response);
		request.getRequestDispatcher("QueryAllByPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
