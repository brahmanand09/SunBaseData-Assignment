package com.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet(urlPatterns = {"/get_cust"})
public class get_cust extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
        String token = (String) session.getAttribute("token");
        System.out.println("This is Token : " + token);
        
        HttpURLConnection con = null;
        
        try {
            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
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
            Gson gson = new Gson();
            List<Person> personList = gson.fromJson(responseData, new TypeToken<List<Person>>() {}.getType());

            request.setAttribute("data", personList);
            RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
            rd.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
