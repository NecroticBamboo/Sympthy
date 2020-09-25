<%@ page import="Interfaces.ITestManagement" %>
<%@ page import="DI.IServiceLocator" %>
<%@ page import="Interfaces.TestInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Interfaces.QuestionInfo" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 07/09/2020
  Time: 16:51
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
    <title> Edit question </title>
</head>

<body>

<%--<p>Test information</p>--%>

<%
//    int numberOfQuestions = Integer.parseInt(request.getParameter("numberOfQuestions"));
//    int currentQuestionIndex = Integer.parseInt(request.getParameter("currentQuestionIndex"));
    long questionId = Long.parseLong(request.getParameter("questionId"));
    String testName = request.getParameter("test_name");
    String email=request.getParameter("email");

    ServletContext context=request.getServletContext();
    IServiceLocator serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
    ITestManagement testManagement=(ITestManagement) serviceLocator.getService("ITestManagement");
//    TestInfo testInfo=testManagement.getTestInfoByName(testName);
//    ArrayList<QuestionInfo> list=testManagement.getQuestionInfo(testInfo);
    QuestionInfo questionInfo=testManagement.getQuestionInfoById(questionId);


    String contextPath = request.getContextPath();
//    out.println("Current question index " + currentQuestionIndex + " out of " + numberOfQuestions);
//    "&numberOfQuestions="+numberOfQuestions+"&currentQuestionIndex="+currentQuestionIndex+
    out.println("<form class=\"online_test_form\" action=" + contextPath + "/EditQuestion?test_name=" + testName + "&questionId="+questionId+ "&oldNumberOfAnswers="+questionInfo.getNumberOfAnswers()+"&email="+email+" method=\"post\">");
%>
<%----%>
<label>Question content: </label><br>
<%--<textarea name="question_content" rows="5" cols="40" style="resize: none"></textarea><br>--%>
<%
    out.println("<textarea name=\"question_content\" rows=\"5\" cols=\"40\" style=\"resize: none\">"+questionInfo.getContent()+"</textarea><br>");
%>

<label>Type of question: </label>
<select id="question_type" name="question_type"><br>
    <option value="Something 1">Something 1</option>
    <option value="Something 2">Something 2</option>
    <option value="Something 3">Something 3</option>
    <option value="Something 4">Something 4</option>
</select>
<br>

<label>Score value: </label>
<%--<input type="text" name="score_value"><br>--%>
<%
    out.println("<input type=\"text\" name=\"score_value\" value=\""+questionInfo.getScoreValue()+"\"><br>");
%>
<label>Number of answers: </label>
<%--<input type="text" name="number_of_answers" id="number_of_answers"><br>--%>
<%
    out.println("<input type=\"text\" name=\"number_of_answers\" id=\"number_of_answers\" value=\""+questionInfo.getNumberOfAnswers()+"\"><br>");
%>
<label>Type of answers: </label>
<select id="answer_type" name="answer_type"><br>
    <option value="Something 1">Something 1</option>
    <option value="Something 2">Something 2</option>
    <option value="Something 3">Something 3</option>
    <option value="Something 4">Something 4</option>
</select>
<br>

<input type="submit" value="Submit"/>
<%
    out.println("</form>");
%>

<div class="navigation-header">
    <%
        out.println("<a href=test_with_questions.jsp?test_name="+testName+"&email="+email+"> Go back</a>");
    %>
</div>
</body>

</html>
