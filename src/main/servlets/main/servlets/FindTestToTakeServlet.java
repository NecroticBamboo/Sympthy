package main.servlets;

import DI.IServiceLocator;
import Interfaces.ClassInfo;
import Interfaces.IUserManagement;
import Interfaces.StudentInfo;
import Interfaces.UserInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FindTestToTakeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String studentEmail=request.getParameter("student_email");
        String className=request.getParameter("class_name");
        String subjectName=request.getParameter("subject_name");

        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var userManagement=(IUserManagement) serviceLocator.getService("IUserManagement");

        PrintWriter out = response.getWriter();

        UserInfo user=userManagement.getUserInfo(studentEmail);
        ClassInfo classInfo=userManagement.getClassInfo(className);
        if(user==null){
            out.println("That user doesn't exist!");
        }
        StudentInfo student=userManagement.getStudentInfo(user,classInfo);
        if(student==null){
            out.println("Your teacher hasn't yet added you to your class!");
        }

        response.sendRedirect(request.getContextPath()+"/website/HTML/select_test_to_take.jsp?student_email="+studentEmail+"&subject_name="+subjectName);
    }
}
