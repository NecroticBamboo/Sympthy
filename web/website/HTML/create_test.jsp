<%@ page import="Interfaces.UserInfo" %>
<%@ page import="Interfaces.IUserManagement" %>
<%@ page import="DI.IServiceLocator" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 14/07/2020
  Time: 15:24
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
    <title> Create test </title>
</head>

<body>

<p>If you want to create new test, fill this form.</p>

<%
//    String orgId = request.getParameter("orgId");
    String email=request.getParameter("email");
    ServletContext context=request.getServletContext();
    IServiceLocator serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
    IUserManagement userManagement=(IUserManagement) serviceLocator.getService("IUserManagement");

    UserInfo user=userManagement.getUserInfo(email);

    String contextPath = request.getContextPath();
    out.println("<form action=\"" + contextPath + "/CreateTest?orgId=" + user.getOrgId() + "&email="+email+"\" method=\"post\" class=\"navigation-header-selected\">");
%>
<%--<form class="online_test_form" action="${pageContext.request.contextPath}/CreateTest" method="post" onsubmit="time()">--%>
    <label>Subject name: </label>
    <input type="text" name="subject_name"><br>

    <label>Timer:</label>
    <input type="text" name="timer"><br>

    <label>Test name:</label>
    <input type="text" name="test_name"><br>

    <label>Public:</label>
    <select id="public" name="isPublic">
        <option value="Yes">Yes</option>
        <option value="No">No</option>
    </select>
    <br>

<%--    <label>Number of questions:</label>--%>
<%--    <input type="text" name="number_of_questions"><br>--%>

    <input type="submit" value="Submit and edit" />
<%
    out.println("</form>");
%>
<%--</form>--%>

<div class="navigation-header">
    <%
        out.println("<a href=\"homePage_teacher.jsp?email=" + email + "\"> Go back</a>");
    %>
</div>

</body>

</html>
