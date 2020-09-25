package main.servlets;

import DI.IServiceLocator;
import Interfaces.IUserManagement;
import Interfaces.OrganisationInfo;
import Interfaces.UserInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet
public class SignUpServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var userManagement=(IUserManagement) serviceLocator.getService("IUserManagement");

        PrintWriter out = response.getWriter();
        String orgName=request.getParameter("org_name");
        String role=request.getParameter("role");
        String firstName=request.getParameter("first_name");
        String lastName=request.getParameter("last_name");
        String email=request.getParameter("email");

        boolean userExist=userManagement.userExist(email);
        boolean organisationExist=userManagement.organisationExist(orgName);

        if(userExist){
            out.println("<html><body><b> That user exist! </b></body></html>");
        } else if(!organisationExist){
            out.println("<html><body><b> That company does not exist! </b></body></html>");
        }else {
            OrganisationInfo org=userManagement.getOrganisationInfo(orgName); //?????

            UserInfo holder = userManagement.createUser();
            holder.setOrgId(org.getId());
            holder.setRole(role);
            holder.setFirstName(firstName);
            holder.setLastName(lastName);
            holder.setEmail(email);
            holder.setIsActive(1);

            userManagement.updateUser(holder);
            out.println("<html><body><b> User created! </b></body></html>");
        }

    }
}
 