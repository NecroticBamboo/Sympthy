<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 22/08/2020
  Time: 16:42
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
    <title> Find test </title>
</head>

<body>
<%
    String contextPath=request.getContextPath();
    String email=request.getParameter("email");
%>

<p>Type the name of test to edit</p>

<%
    out.println("<form class=\"online_test_form\" action=\""+contextPath+"/FindTest?email="+email+"\" method=\"post\">");
%>
<%--<form class="online_test_form" action="${pageContext.request.contextPath}/FindTest" method="post">--%>
    <label>Test name: </label>
    <input type="text" name="test_name"><br>

    <input type="submit" value="Submit and edit" />
<%--</form>--%>
<%
    out.println("</form>");
%>
<div class="navigation-header">
    <%
        out.println("<a href=\"homePage_teacher.jsp?email="+email+"\"> Go back</a>");
    %>

</div>
</body>

</html>
