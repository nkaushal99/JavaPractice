package com.practice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//urlPattern should be same as the form action
@WebServlet(name="register", urlPatterns={"/RegisterServlet"})
public class RegisterServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		
		response.setContentType("text/html");
		try(PrintWriter out = response.getWriter()){
			out.println("<h2>Welcome to Register Servlet</h2>");
			
			String name = request.getParameter("user_name");
			String password = request.getParameter("user_password");
			String email = request.getParameter("user_email");
			String gender = request.getParameter("user_gender");
			String course = request.getParameter("user_course");
			String cond = request.getParameter("condition");
			
			if("checked".equals(cond)) {
				out.println("<h2>Name: "+ name +"</h2>");
				out.println("<h2>Password: "+ password +"</h2>");
				out.println("<h2>Email: "+ email +"</h2>");
				out.println("<h2>Gender: "+ gender +"</h2>");
				out.println("<h2>Course: "+ course +"</h2>");
				
				////////////////////////////
				//Assume Saved to Database//
				////////////////////////////
				
				RequestDispatcher rd = request.getRequestDispatcher("/success");
				rd.forward(request, response);
			}
			else {
				out.println("<h2>You have not accepted terms and conditions.");
				
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
		}
		catch(IOException ioex) {
			System.out.println("doPost Exception");
		}
	}

}
