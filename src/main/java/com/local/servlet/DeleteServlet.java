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

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	private static final String query = "delete FROM BOOKRECORED where id=?";
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	 //set print window
	 PrintWriter pw = res.getWriter();
	 res.setContentType("text/html");
int id=	 Integer.parseInt(req.getParameter("id"));
	 
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
		 int count = ps.executeUpdate();
		 if(count == 1) {
			 pw.println("<h3>Recored deleted!</h3>");
		 }else {
			 pw.println("<h3>Recored not deleted!</h3>");
		 }
		 
	 }
	catch(SQLException sq) {
		sq.printStackTrace();
		 pw.println("<h3>"+sq.getMessage()+"</h3>");
	}catch(Exception e) {
		 e.printStackTrace();
		 pw.println("<h3>"+e.getMessage()+"</h3>");
	 }
	 pw.println("<a href='home.html'>Home</a>");
	 pw.println("<br/>");
	 pw.println("<a href ='booklist'>Book List</a>");
}
 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	doPost(req, resp);
	}

}
