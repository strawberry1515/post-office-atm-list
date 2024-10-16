package com.ispan.wu.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ispan.wu.Constants;
import com.ispan.wu.bean.AtmBean;

//import com.ispan.result.Result;
//import com.ispan.result.ResultImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/QueryAllCity")
public class QueryAllCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String QUERY_ALL_CITY="SELECT DISTINCT city, cityNo FROM post_atm ORDER BY cityNo "; 
	Connection conn;
		
	public QueryAllCity() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<AtmBean> atms = new ArrayList<AtmBean>();
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/midtern");
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(QUERY_ALL_CITY);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				AtmBean atm = new AtmBean();
				
				atm.setCity(rs.getString("city"));
				atm.setCityNo(rs.getInt("cityNo"));
				
				atms.add(atm);
			}
			request.setAttribute("cities", atms);
			stmt.close();
			conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
