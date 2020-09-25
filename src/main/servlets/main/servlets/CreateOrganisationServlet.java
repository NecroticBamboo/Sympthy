package main.servlets;

import DI.IServiceLocator;
import Interfaces.IUserManagement;
import Interfaces.OrganisationInfo;
import main.utils.DatabaseConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//@WebServlet
public class CreateOrganisationServlet extends HttpServlet {

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
        String orgAddress=request.getParameter("org_address");

        boolean companyExist=userManagement.organisationExist(orgName);
        if(companyExist){
            out.println("<html><body><b> That company exist! </b></body></html>");
        } else {
            OrganisationInfo holder=userManagement.createOrganisation();
            holder.setName(orgName);
            holder.setAddress(orgAddress);

            userManagement.updateOrganisation(holder);
            out.println("<html><body><b> Company created! </b></body></html>");
            response.sendRedirect(request.getContextPath() + "/website/HTML/create_organisation.jsp");
        }

    }
}
