<%@ page import="Interfaces.UserInfo" %>
<%@ page import="Interfaces.IUserManagement" %>
<%@ page import="DI.IServiceLocator" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 21/08/2020
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta charset="utf-8"/>
    <meta name="description" content="Web site with online tests">
    <meta name="keywords" content="tests,online tests">
    <meta name="Andrew" content="u1821659">
    <title> Create class </title>
</head>

<body>

<p>If you want to create new class, fill this form.</p>

<%
//    String orgId = request.getParameter("orgId");
    String email=request.getParameter("email");
    ServletContext context=request.getServletContext();
    IServiceLocator serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
    IUserManagement userManagement=(IUserManagement) serviceLocator.getService("IUserManagement");

    UserInfo user=userManagement.getUserInfo(email);

    String contextPath = request.getContextPath();
    out.println("<form action=\"" + contextPath + "/CreateClass?orgId=" + user.getOrgId() + "\" method=\"post\" class=\"navigation-header-selected\">");
%>

<label>Class name: </label>
<input type="text" name="class_name"><br>

<input class="button form-sub" type="submit" value="Submit"/>
<%
    out.println("</form>");
%>

<div class="navigation-header">
    <%
        out.println("<a href=\"homePage_teacher.jsp?email=" + email + "\"> Go back</a>");
    %>
</div>

</body>

</html>
