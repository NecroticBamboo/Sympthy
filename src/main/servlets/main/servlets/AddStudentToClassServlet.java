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

public class AddStudentToClassServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String teacherEmail=request.getParameter("email");
        String studentEmail=request.getParameter("student_email");
        String className=request.getParameter("class_name");
        String studentType=request.getParameter("student_type");

        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var userManagement=(IUserManagement) serviceLocator.getService("IUserManagement");

        PrintWriter out = response.getWriter();
        UserInfo user=userManagement.getUserInfo(studentEmail);
        ClassInfo classInfo=userManagement.getClassInfo(className);

        if(user==null && classInfo==null){
            out.println("User and class don't exist!");
            return;
        } else if(user==null){
            out.println("User doesn't exist!");
            return;
        } else if(classInfo==null){
            out.println("Class doesn't exist!");
            return;
        }

        StudentInfo holder=userManagement.createStudent();
        holder.setUserId(user);
        holder.setClassId(classInfo);
        holder.setUserType(studentType);

        userManagement.updateStudent(holder);

        response.sendRedirect(request.getContextPath()+ "/website/HTML/add_students_to_class.jsp?email="+teacherEmail+"&class_name="+className);
    }
}
