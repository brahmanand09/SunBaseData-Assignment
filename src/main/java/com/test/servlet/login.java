package com.test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   		String login_id = request.getParameter("login_id");
   		String password = request.getParameter("password");
   		System.out.println("Login id : "+login_id+" and password is : "+password);
   		HttpSession session = request.getSession();
		session.setAttribute("login_id", login_id);
		session.setAttribute("password", password);
		session.setAttribute("url", "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp");
		new accesstoken(request, response);
   	}
}
