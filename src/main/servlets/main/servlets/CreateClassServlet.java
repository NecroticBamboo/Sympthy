package main.servlets;

import DI.IServiceLocator;
import Interfaces.ClassInfo;
import Interfaces.IUserManagement;
import Interfaces.OrganisationInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateClassServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var userManagement=(IUserManagement) serviceLocator.getService("IUserManagement");

        String className=request.getParameter("class_name");
        String orgIdHolder=request.getParameter("orgId");
        long orgId=Long.parseLong(orgIdHolder);

        ClassInfo holder=userManagement.createClass();
        OrganisationInfo org=userManagement.getOrganisationInfoWithId(orgId);

        holder.setName(className);
        holder.setOrgId(org);
        userManagement.updateClass(holder);

        PrintWriter out = response.getWriter();
        out.println("Class created "+holder.getName()+" "+holder.getId());
    }
}
