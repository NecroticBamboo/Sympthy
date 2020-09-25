<%@ page import="Interfaces.ITestManagement" %>
<%@ page import="DI.IServiceLocator" %>
<%@ page import="Interfaces.QuestionInfo" %>
<%@ page import="Interfaces.AnswerInfo" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <meta charset="utf-8" />
    <meta name="description" content="Web site with online tests">
    <meta name="keywords" content="tests,online tests">
    <meta name="Andrew" content="u1821659">
    <title> Edit answer </title>
</head>

<body>

<%
    int numberOfAnswers = Integer.parseInt(request.getParameter("numberOfAnswers"));
    int currentAnswerIndex = Integer.parseInt(request.getParameter("currentAnswerIndex"));
    String questionId = request.getParameter("questionId");
    String contextPath = request.getContextPath();
    String testName=request.getParameter("test_name");
    String email=request.getParameter("email");

    ServletContext context=request.getServletContext();
    IServiceLocator serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
    ITestManagement testManagement=(ITestManagement) serviceLocator.getService("ITestManagement");
    QuestionInfo questionInfo=testManagement.getQuestionInfoById(Long.parseLong(questionId));
    ArrayList<AnswerInfo> answerList=testManagement.getAllAnswersInfo(questionInfo);

    if(answerList.isEmpty()) {
        response.sendRedirect(request.getContextPath() + "/website/HTML/test_with_questions.jsp?test_name=" + testName + "&email="+email);
        return;
    }
//
//    int answersLeft=testManagement.getNumberOfNonFilledAnswers(questionInfo);
//
//    if(currentAnswerIndex>numberOfAnswers){
//        response.sendRedirect(request.getContextPath()+"/website/HTML/test_with_questions.jsp?test_name="+testName);
//    }

        out.println("Current question index " + currentAnswerIndex + " out of " + numberOfAnswers);
        out.println("<form class=\"online_test_form\" action=" + contextPath + "/EditAnswer?questionId=" + questionId + "&numberOfAnswers=" + numberOfAnswers + "&currentAnswerIndex=" + currentAnswerIndex + "&test_name=" + testName + "&email="+email+" method=\"post\">");
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
<%
    out.println("<textarea name=\"answer_content\" rows=\"5\" cols=\"40\" style=\"resize: none\">"+answerList.get(currentAnswerIndex-1).getContent()+"</textarea>");
%>
<br>
<label>Is this answer correct: </label>
<select id="answer_type" name="isCorrect"><br>
    <option value="Yes">Yes</option>
    <option value="No">No</option>
</select>

<input type="submit" value="Submit" />
<%--</form>--%>
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
