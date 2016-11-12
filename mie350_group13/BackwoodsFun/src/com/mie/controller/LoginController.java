package com.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.dao.UserDao;
import com.mie.model.User;

//git hub tutorial

public class LoginController extends HttpServlet{
	
	private UserDao dao;
	
	public LoginController() {
		super();
		dao = new UserDao();
	}
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String user = request.getParameter("email");
        String pass = request.getParameter("pass");
		
		if(dao.loginValidated(dao.getAllUsers(), user, pass)){
			HttpSession session = request.getSession(true);	    
	        session.setAttribute(user,dao.getUserByEmail(user)); 
	        //response.sendRedirect("INSERT HOMEPAGE NAME.jsp"); //user homepage
		}
		else{
			response.sendRedirect("invalidLogin.jsp"); //retry login!
		}
	}

}