package main.servlets;

import DI.IServiceLocator;
import Interfaces.IUserManagement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//@WebServlet("/signin")
public class CheckInServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var userManagement=(IUserManagement) serviceLocator.getService("IUserManagement");

        PrintWriter out = response.getWriter();
        var userName=request.getParameter("login");
        boolean userExist=userManagement.userExist(userName);

        if(userExist){
            String role=userManagement.getUserInfo(userName).getRole();
            String email=userManagement.getUserInfo(userName).getEmail();
            long orgId=userManagement.getUserInfo(userName).getOrgId();
            String firstName=userManagement.getUserInfo(userName).getFirstName();
            String lastName=userManagement.getUserInfo(userName).getLastName();


            switch (role) {
                case "admin":
                    response.sendRedirect(request.getContextPath() + "/website/HTML/homePage_admin.jsp?email="+email);
                    break;
                case "student":
                    response.sendRedirect(request.getContextPath() + "/website/HTML/homePage_student.jsp?email="+email+"&firstName="+firstName+"&lastName="+lastName);
                    break;
                case "teacher":
                    response.sendRedirect(request.getContextPath() + "/website/HTML/homePage_teacher.jsp?email="+email+"&orgId="+orgId+"&firstName="+firstName+"&lastName="+lastName);
                    break;
            }

//            out.println(holder.getId()+" "+holder.getOrgId()+" "+holder.getRole()+" "+holder.getFirstName()+" "+holder.getLastName()+" "+holder.getEmail()+" "+holder.getIsActive());
        } else{
            out.println("<html><body><b> That user doesn't exist! </b></body></html>");
        }


    }
}
