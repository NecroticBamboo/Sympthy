<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 16/09/2020
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="description" content="Web site with online tests">
    <meta name="keywords" content="tests,online tests">
    <meta name="Andrew" content="u1821659">
    <title> Find test to take</title>
</head>

<body>

<p>Please enter the name of the class and subject.</p>
<%
    String studentEmail=request.getParameter("email");
    String contextPath=request.getContextPath();
    out.println("<form class=\"online_test_form\" action=\""+contextPath+"/FindTestToTake?student_email="+studentEmail+"\" method=\"post\">");
%>
<%--<form class="online_test_form" action="${pageContext.request.contextPath}/TakeTest" method="post">--%>
    <label>Class name: </label>
    <input type="text" name="class_name"><br>

    <label>Subject name: </label>
    <input type="text" name="subject_name"><br>

    <input type="submit" value="Submit" />
<%--</form>--%>
<%
    out.println("</form>");
%>

<div class="navigation-header">
    <%
        out.println("<a href=homePage_student.jsp?email=" + studentEmail + "> Go back</a>");
    %>
</div>
</body>

</html>
