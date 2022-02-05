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

    /**UC 4: Extend the servlet to accept a valid name and password */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        // The username should start with capital letters and contain at least three characters
        Pattern pattern =Pattern.compile("[A-Z][a-zA-Z//s]{2,}");
        Matcher matcher = pattern.matcher(userName);
        boolean userNameValid =matcher.matches();

        // The password should have minimum 8 characters, should have at least 1 upper case,
        // should have at least 1 numeric number and exactly 1 special character
        int cnt_spl_char = 0;
        boolean pwd_Correct = false;
        Pattern pattern1 = Pattern.compile("[~!@#$%^&*()_+-]");
        Matcher matcher1 = pattern1.matcher(pwd);
        // count the number of special characters
        while (matcher1.find()){
            cnt_spl_char++;
        }

        // If only one special character exists
        if (cnt_spl_char==1){
            // Check for Minimum 8 characters out of which at least 1 upper case and 1 number
            Pattern pattern2 = Pattern.compile("(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9!@#$%^^&*)(_+-]{8,}");
            Matcher matcher2 = pattern2.matcher(pwd);
            pwd_Correct = matcher2.matches();
        }
        if(userNameValid  && pwd_Correct){
            request.setAttribute("user",userName);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request,response);
        }else if(!userNameValid){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>User name  is invalid.<font>");
            rd.include(request,response);
        }else if(!pwd_Correct){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Password  is invalid.<font>");
            rd.include(request,response);
        }
    }
}
