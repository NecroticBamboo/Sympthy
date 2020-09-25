
<%@ page import="DI.IServiceLocator" %>
<%@ page import="Interfaces.IUserManagement" %>
<%@ page import="Interfaces.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 17/08/2020
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="description" content="Web site with online tests">
    <meta name="keywords" content="tests,online tests,home,teacher">
    <meta name="Andrew" content="u1821659">
    <title> Personal Office </title>
</head>

<body>


<%
    String email=request.getParameter("email");
    ServletContext context=request.getServletContext();
    IServiceLocator serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
    IUserManagement userManagement=(IUserManagement) serviceLocator.getService("IUserManagement");

    UserInfo user=userManagement.getUserInfo(email);

    out.println("<h1> Welcome "+user.getFirstName()+" "+user.getLastName()+"!</h1>");
%>

<p>This is your home page. From here you can do different things such as...</p>

<div class="flex-container-header">
    <%
        String contextPath = request.getContextPath();
        out.println("<form action=\""+contextPath+"/CheckProfileInfo?email="+email+"\" method=\"post\" class=\"navigation-header-selected\">" );
    %>
    <input class="button form-sub" name="teacherProfile" type="submit" value="Check profile info" />
    <%
        out.println("</form>");
    %>

    <%
        out.println("<a href=\"create_class.jsp?email="+email+"\"> Create class</a><br>");
        out.println("<a href=\"add_students_to_class.jsp?email="+email+"&class_name= \"> Add students to class</a><br>");
        out.println("<a href=\"create_test.jsp?email="+email+"\"> Create test</a><br>");
        out.println("<a href=\"find_test_to_edit.jsp?email="+email+"\"> Edit test</a><br>");
    %>

    <div class="navigation-header">
        <a href=""> Check results</a>
    </div>
    <div class="navigation-header">
        <a href="../../index.jsp"> Sign out</a>
    </div>
</div>
</body>

</html>
