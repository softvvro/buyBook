package com.local.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edited")
public class EditedServlet extends HttpServlet {
	
	private static final String query ="update BOOKRECORED set BOOKNAME=?, BOOKEDITION=?, BOOKPRICE=?  where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//set printwriter for print on web page
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		Connection con = null;
		PreparedStatement ps = null;
		//get id recored
		int id =	Integer.parseInt(req.getParameter("id"));
		System.out.println(id);
		
		//get data for edited
		String bookName = req.getParameter("bookname");
		System.out.println(bookName);
		String bookEdition =req.getParameter("bookedition");
		System.out.println(bookEdition);
		float bookPrice =Float.parseFloat(req.getParameter("bookprice"));
		System.out.println(bookPrice);
		try {
		
		
		//load jdbc driver	
	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql:///book","root","lucky$JTC@2023");
	ps = con.prepareStatement(query);
ps.setString(1, bookName);
ps.setString(2, bookEdition);
ps.setFloat(3, bookPrice);
ps.setInt(1, id);
	int count = ps.executeUpdate();
	if(count ==1) {
		pw.println("<h3>Recored updated</h3>");
	}else {
		pw.println("<h3>Recored not updated</h3>");
	}
	
	}catch(ClassNotFoundException cnf) {
cnf.printStackTrace();
pw.println("<h3>"+cnf.getMessage()+"</h3>");
	}catch(SQLException sql) {
		sql.printStackTrace();
		pw.println("<h3>"+sql.getMessage()+"</h3>");
	}catch(Exception e) {
		e.printStackTrace();
		pw.println("<h3>"+e.getMessage()+"</h3>");
	}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req, resp);
	}

}
