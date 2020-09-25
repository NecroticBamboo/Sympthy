<%@ page import="DI.IServiceLocator" %>
<%@ page import="Interfaces.IUserManagement" %>
<%@ page import="Interfaces.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 16/09/2020
  Time: 16:00
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
    <title> Add student </title>
</head>

<body>

<p>If you want to add student to class, fill this form.</p>

<%
    String email=request.getParameter("email");
    String className=request.getParameter("class_name");

    String contextPath = request.getContextPath();
    out.println("<form action=\"" + contextPath + "/AddStudentToClass?email=" + email + "\" method=\"post\" class=\"navigation-header-selected\">");
%>

<label>Class name: </label>
<%
    out.println("<input type=\"text\" name=\"class_name\" value="+className+"><br>");
%>

<label>Student email: </label>
<input type="text" name="student_email"><br>

<label for="student_type"> Choose student type :</label>
<select id="student_type" name="student_type">
    <option value="Something 1" name="Something 1">Something 1</option>
    <option value="Something 2" name="Something 2">Something 2</option>
</select><br>

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
