package main.servlets;

import DI.IServiceLocator;
import Interfaces.ITestManagement;
import Interfaces.IUserManagement;
import Interfaces.OrganisationInfo;
import Interfaces.TestInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class CreateTestServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var userManagement=(IUserManagement) serviceLocator.getService("IUserManagement");
        var testManagement=(ITestManagement) serviceLocator.getService("ITestManagement");

        long orgId=Long.parseLong(request.getParameter("orgId"));
        String email=request.getParameter("email");

        String subjectName=request.getParameter("subject_name");
        Date timer=new Date();
        String testName=request.getParameter("test_name").replaceAll("\\s+","");
        String isPublic=request.getParameter("isPublic");
        boolean isPublicInBoolean=false;
        if(isPublic.equals("Yes")){
            isPublicInBoolean=true;
        }
//        int numberOfQuestions=Integer.parseInt(request.getParameter("number_of_questions"));

        OrganisationInfo org=userManagement.getOrganisationInfoWithId(orgId);
        TestInfo holder=testManagement.createTest(org);

        holder.setOrgId(orgId);
        holder.setSubjectName(subjectName);
//        holder.setTimer(timer); //???
        holder.setTestName(testName);
        holder.setPublic(isPublicInBoolean);
        holder.setNumberOfQuestions(0);

        testManagement.updateTest(holder);
        response.sendRedirect(request.getContextPath() + "/website/HTML/test_with_questions.jsp?test_name="+holder.getTestName()+"&email="+email);

//        PrintWriter out = response.getWriter();
//        out.println(holder.getId()+" "+holder.getSubjectName()+" "+holder.getTimer()+" "+holder.getTestName()+" "+holder.isPublic()+" "+holder.getNumberOfQuestions());
    }
}
