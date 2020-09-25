<%@ page import="Interfaces.ITestManagement" %>
<%@ page import="DI.IServiceLocator" %>
<%@ page import="Interfaces.TestInfo" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 10/09/2020
  Time: 15:04
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
    <title> Edit test </title>
</head>

<body>

<%
    String contextPath = request.getContextPath();
    String testName=request.getParameter("test_name");
    String email=request.getParameter("email");

    ServletContext context=request.getServletContext();
    IServiceLocator serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
    ITestManagement testManagement=(ITestManagement) serviceLocator.getService("ITestManagement");

    TestInfo test=testManagement.getTestInfoByName(testName);

    out.println("<form class=\"online_test_form\" action=" + contextPath + "/EditTest?test_name=" + testName + "&email="+email+"&orgId="+test.getOrgId()+"&oldNumberOfQuestions="+test.getNumberOfQuestions()+" method=\"post\">");
%>
<%--<form class="online_test_form" action="${pageContext.request.contextPath}/CreateTest" method="post" onsubmit="time()">--%>
<label>Subject name: </label>
<%
    out.println("<input type=\"text\" name=\"subject_name\" value=\""+test.getSubjectName()+"\"><br>");
%>

<label>Timer:</label>
<input type="text" name="timer"><br>

<label>Test name:</label>
<%
    out.println("<input type=\"text\" name=\"new_test_name\" value=\""+test.getTestName()+"\"><br>");
%>

<label>Public:</label>
<select id="public" name="isPublic">
    <option value="Yes">Yes</option>
    <option value="No">No</option>
</select>
<br>

<%--<label>Number of questions:</label>--%>
<%--<%--%>
<%--    out.println("<input type=\"text\" name=\"number_of_questions\" value=\""+test.getNumberOfQuestions()+"\"><br>");--%>
<%--%>--%>

<input type="submit" value="Submit and edit" />
<%
    out.println("</form>");
%>
<%--</form>--%>

<div class="navigation-header">
    <%
        out.println("<a href=test_with_questions.jsp?test_name="+testName+"&email="+email+"> Go back</a>");
    %>
</div>
</body>

</html>
