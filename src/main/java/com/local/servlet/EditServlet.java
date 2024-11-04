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

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	private static final String query = "SELECT BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKRECORED where id=? ";
	 //set print window
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		 res.setContentType("text/html");
		 
		 int id=	 Integer.parseInt(req.getParameter("id"));
		 System.out.println(id);
		 try {
		 //load jdbc driver
		 
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 }catch(ClassNotFoundException cnf) {
			 cnf.printStackTrace();
			 pw.println("<h2>"+cnf.getMessage()+"</h2>");
		 }catch(Exception e) {
			 e.printStackTrace();
			pw.println("<h3>"+e.getMessage()+"</h3>");
		 }
		 //get connection of jdbc to database using try parameter
		 Connection con =null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 try {
			 
			  con =DriverManager.getConnection("jdbc:mysql:///book", "root", "lucky$JTC@2023");
			  ps = con.prepareStatement(query);
		 }catch(SQLException sq) {
			 sq.printStackTrace();
			 pw.println("<h3>"+sq.getMessage()+"</h3>");
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 pw.println("<h3>"+e.getMessage()+"</h3>");
		 }
		 try {
			 ps.setInt(1, id);
			 rs= ps.executeQuery();
			 rs.next();
			 pw.println("<form action='edited?id='"+id+ "method='post'>");
			 pw.println("<table align='center'>");
			 pw.println("<tr>");
			 pw.println("<td> Book Name</td>");
			 pw.println("<td><input type='text' name='bookname' value='"+rs.getString(1)+"'></td>");
			 pw.println("</tr>");
			 pw.println("<tr>");
			 pw.println("<td> Book Edition</td>");
			 pw.println("<td><input type='text' name='bookedition' value='"+rs.getString(2)+"'></td>");
			 pw.println("</tr>");
			 pw.println("<tr>");
			 pw.println("<td> Book Price</td>");
			 pw.println("<td><input type='text' name='bookprice' value='"+rs.getFloat(3)+"'></td>");
			 pw.println("</tr>");
			 pw.println("<tr>");
			 pw.println("<td> <input type='submit' value='edited'</td>");
			 pw.println("<td><input type='reset' value='cancel'></td>");
			 pw.println("</tr>");
			 
			 pw.println("</table>");
			 pw.println("</form");
		 }catch(Exception e) {
			 e.printStackTrace();
			 pw.println("<h3>"+e.getMessage()+"</h3>");
		 }
		 
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		doPost(req, resp);
	}
	

}
