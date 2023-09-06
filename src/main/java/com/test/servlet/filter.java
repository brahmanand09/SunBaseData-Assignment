package com.test.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns={"/del_cust","/update_cust","/get_cust","/add_cust","/add_cust.html","/customer.jsp","/del_cust.html","/update_cust.html"})
public class filter extends HttpFilter implements Filter {
       
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse res = (HttpServletResponse)response;
		
		res.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		
		res.setHeader("Pragma", "No-cache");
		res.setDateHeader("Expires", 0);
		
		System.out.println("I am in filter");
		HttpSession session =  ((HttpServletRequest)request).getSession();
		String token = (String)session.getAttribute("token");
		System.out.println("This is token in filter : "+token);
		if(token==null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}
		else
		{
			chain.doFilter(request, response);
		}
	}
}
