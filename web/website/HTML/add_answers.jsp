<%@ page import="Interfaces.ITestManagement" %>
<%@ page import="DI.IServiceLocator" %>
<%@ page import="Interfaces.QuestionInfo" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 02/09/2020
  Time: 15:45
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
    <title> Home </title>
</head>

<body>

<%
    int numberOfAnswers = Integer.parseInt(request.getParameter("numberOfAnswers"));
    String questionId = request.getParameter("questionId");
    String contextPath = request.getContextPath();
    String testName = request.getParameter("test_name");
    String email=request.getParameter("email");

    ServletContext context = request.getServletContext();
    IServiceLocator serviceLocator = (IServiceLocator) context.getAttribute("ServiceLocator");
    ITestManagement testManagement = (ITestManagement) serviceLocator.getService("ITestManagement");
    QuestionInfo questionInfo = testManagement.getQuestionInfoById(Long.parseLong(questionId));
    int answersLeft = testManagement.getNumberOfNonFilledAnswers(questionInfo);
    int currentAnswerIndex = ((numberOfAnswers - answersLeft) + 1);

    if (currentAnswerIndex > numberOfAnswers) {
        response.sendRedirect(request.getContextPath() + "/website/HTML/test_with_questions.jsp?test_name=" + testName+"&email="+email);
    }

    out.println("Current question index " + currentAnswerIndex + " out of " + numberOfAnswers);
    out.println("<form class=\"online_test_form\" action=" + contextPath + "/AddAnswer?questionId=" + questionId + "&numberOfAnswers=" + numberOfAnswers + "&currentAnswerIndex=" + currentAnswerIndex + "&test_name=" + testName + "&email="+email+" method=\"post\">");
%>

<%--<p>Current answer index out of </p>--%>
<%--<form class="online_test_form" action="${pageContext.request.contextPath}/" method="post">--%>
<label>Type of answer: </label>
<select id="answer_type" name="answer_type"><br>
    <option value="Something 1">Something 1</option>
    <option value="Something 2">Something 2</option>
    <option value="Something 3">Something 3</option>
    <option value="Something 4">Something 4</option>
</select>
<br>
<label>Answer content: </label><br>
<textarea name="answer_content" rows="5" cols="40" style="resize: none"></textarea>
<br>
<label>Is this answer correct: </label>
<select id="answer_type" name="isCorrect"><br>
    <option value="Yes">Yes</option>
    <option value="No">No</option>
</select>
<input type="submit" value="Submit"/>
<%--</form>--%>
<%
    out.println("</form>");
%>
<div class="navigation-header">
    <%
        out.println("<a href=test_with_questions.jsp?test_name=" + testName + "&email="+email+"> Go back</a>");
    %>
</div>
</body>

</html>
