package main.servlets;

import DI.IServiceLocator;
import Interfaces.IUserManagement;
import Interfaces.UserInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckProfileInfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var userManagement=(IUserManagement) serviceLocator.getService("IUserManagement");

        String email=request.getParameter("email");

        PrintWriter out = response.getWriter();
        UserInfo holder=userManagement.getUserInfo(email);

         out.println(holder.getId()+" "+holder.getOrgId()+" "+holder.getRole()+" "+holder.getFirstName()+" "+holder.getLastName()+" "+holder.getEmail()+" "+holder.getIsActive());
    }

}
