package com.simpleservet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet" },
        initParams = {
        @WebInitParam(name = "user", value = "Ashish"),
        @WebInitParam(name = "password", value = "Ashish@123")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String user = request.getParameter("user");
    String pwd = request.getParameter("pwd");

        String userID= getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");


            String validUserName = "^[A-Z][a-zA-Z]{2,}$";
            String validPassword = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!#@%^&*(){}])[a-zA-Z0-9+-_!@#$%^&*(){}'.,]{8,}$";

                if (userID.equals(user) && userID.matches(validUserName) && password.equals(pwd) && password.matches(validPassword)){
                    request.setAttribute("user",user);
                    request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
                }
                else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out = response.getWriter();
                    out.println("<font color = red > Either User or Password is wrong. </font>");
                    rd.include(request,response);
                }
    }
}
