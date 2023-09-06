package com.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns= {"/del_cust"})
public class del_cust extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String uuid = request.getParameter("uuid");
		System.out.println("UUID : "+uuid);
		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("token");
		System.out.println("Token : "+token);
		String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid="+uuid;
		
		 HttpURLConnection con = null;
	        
	        try {
	            URL obj = new URL(url);
	            con = (HttpURLConnection) obj.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("Content-Type", "application/json");
	            con.setRequestProperty("Authorization", "Bearer " + token);
	            con.setDoOutput(true);

	            int responseCode = con.getResponseCode();
	            System.out.println("Response Code: " + responseCode);

	            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
	            StringBuilder response1 = new StringBuilder();
	            String responseLine;
	            while ((responseLine = br.readLine()) != null) {
	                response1.append(responseLine.trim());
	            }
	            String responseData = response1.toString();
	            System.out.println("Response Data: " + responseData);
	            response.sendRedirect("get_cust");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (con != null) {
	                con.disconnect();
	            }
	        }

	}
	
}