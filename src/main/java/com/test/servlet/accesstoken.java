package com.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class accesstoken {

    accesstoken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	HttpSession session = request.getSession();
    	String login_id = (String) session.getAttribute("login_id");
    	String password = (String) session.getAttribute("password");
    	String url = (String) session.getAttribute("url");
    	String jsonInput = "{\n" +
    		    "\"login_id\" : \"" + login_id + "\",\n" +
    		    "\"password\" :\"" + password + "\"\n" +
    		    "}";
    	URL obj = new URL(url);
    	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response1 = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response1.append(responseLine.trim());
            }
            System.out.println("Response Data: " + response1.toString());
            String token = extractAccessToken(response1.toString());
            session.setAttribute("token", token);
            session.removeAttribute("login_id");
            session.removeAttribute("password");
            session.removeAttribute("url");
            response.sendRedirect("get_cust");
        }
        catch(Throwable t) {
        	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ErrorPages/loginError.html");
        	rd.forward(request,response);
        }

        con.disconnect();
        
    }
    private String extractAccessToken(String jsonString) {
    	 String tokenKey = "\"access_token\":\"";
         int startIndex = jsonString.indexOf(tokenKey);
         if (startIndex != -1) {
             int endIndex = jsonString.indexOf("\"", startIndex + tokenKey.length());
             if (endIndex != -1) {
                 return jsonString.substring(startIndex + tokenKey.length(), endIndex);
             }
         }
         return null;
    }
}
