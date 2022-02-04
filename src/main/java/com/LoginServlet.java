package com;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@WebServlet(
    description="Login Servlet Testing",
        urlPatterns={"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {

    /**UC 3: Extend the servlet to accept a valid name*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        // The username should start with capital letters and contain at least three characters
        Pattern pattern =Pattern.compile("[A-Z][a-zA-Z//s]{2,}");
        Matcher matcher = pattern.matcher(userName);
        boolean userNameValid =matcher.matches();
        if(userNameValid){
            request.setAttribute("user",userName);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request,response);
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>User name  is invalid.<font>");
            rd.include(request,response);
        }
    }
}
