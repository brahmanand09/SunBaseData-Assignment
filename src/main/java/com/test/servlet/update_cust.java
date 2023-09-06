package com.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns= {"/update_cust"})
public class update_cust extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
			HttpSession session = request.getSession();
			String token = (String) session.getAttribute("token");
			String uuid = request.getParameter("uuid");
			String first_name = request.getParameter("first_name");
			String last_name = request.getParameter("last_name");
			String street = request.getParameter("street");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String jsonString = "{\n" +
					"\"uuid\": \"" + uuid + "\",\n" +
			        "\"first_name\": \"" + first_name + "\",\n" +
			        "\"last_name\": \"" + last_name + "\",\n" +
			        "\"street\": \"" + street + "\",\n" +
			        "\"address\": \"" + address + "\",\n" +
			        "\"city\": \"" + city + "\",\n" +
			        "\"email\": \"" + email + "\",\n" +
			        "\"phone\": \"" + phone + "\"\n" +
			        "}";
			String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=update&uuid="+uuid;
			HttpURLConnection con = null;
			 URL obj = new URL(url);
	         con = (HttpURLConnection) obj.openConnection();
	         con.setRequestMethod("POST");
	         con.setRequestProperty("Content-Type", "application/json");
	         con.setRequestProperty("Authorization", "Bearer " + token);
	         con.setDoOutput(true);
			try (OutputStream os = con.getOutputStream()) {
	            byte[] input = jsonString.getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }
		        
			try {

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