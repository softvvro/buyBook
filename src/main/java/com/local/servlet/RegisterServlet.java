package com.local.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="bookservice", urlPatterns = "/registration")
public class RegisterServlet extends HttpServlet {
	private static final String query = "INSERT INTO BOOKRECORED(BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES(?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
	String bookname =	req.getParameter("bookname");
	String bookedition=	req.getParameter("bookedition");
	float bookprice = Float.parseFloat(req.getParameter("bookprice"));
		//Load jdbc driver
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		//generate connection 
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "lucky$JTC@2023"); PreparedStatement ps=con.prepareStatement(query);){
			ps.setString(1, bookname);
			ps.setString(2, bookedition);
			ps.setFloat(3, bookprice);
			int count = ps.executeUpdate();
			if(count == 1) {
				pw.println("<h3>Recored is registered sucessfully! </h3>");
			}else {
				pw.println("<h3>Recored is not registered</h3>");
			}
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h2>"+se.getMessage()+"</h2>");
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<a href='home.html'>Home</a>");
		pw.println("<br />");
		pw.println("<a href='booklist'>Book List</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doGet(req,res);
	}

}
