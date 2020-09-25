<%@ page import="DI.IServiceLocator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Interfaces.*" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 18/09/2020
  Time: 16:39
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
    <title>Take test</title>
</head>

<body>

<%
    ServletContext context = request.getServletContext();
    IServiceLocator serviceLocator = (IServiceLocator) context.getAttribute("ServiceLocator");
    ITestManagement testManagement = (ITestManagement) serviceLocator.getService("ITestManagement");

    String email = request.getParameter("student_email");
    String testName = request.getParameter("test_name");
    String answerSequence=request.getParameter("answer_sequence");
    String contextPath = request.getContextPath();

    int currentQuestionIndex = Integer.parseInt(request.getParameter("currentQuestionIndex"));
    int totalQuestions = Integer.parseInt(request.getParameter("totalQuestions"));

    TestInfo test = testManagement.getTestInfoByName(testName);
    List<QuestionInfo> list = testManagement.getQuestionInfo(test);
    QuestionInfo currentQuestion = list.get(currentQuestionIndex - 1);
    ArrayList<AnswerInfo> answerList = testManagement.getAllAnswersInfo(currentQuestion);

    out.println("Current question index: " + currentQuestionIndex + " out of: " + totalQuestions);
%>

<%
    out.println("<form class=\"online_test_form\" action=" + contextPath + "/SubmitAnswer?currentQuestionIndex="+currentQuestionIndex+"&totalQuestions="+totalQuestions+"&answer_sequence="+answerSequence+"&student_email="+email+"&test_name="+testName+" method=\"post\">");
%>
<div>
    <%
        out.println("<p>Question content: " + currentQuestion.getContent() + "</p>");
        for (int i = 0; i < currentQuestion.getNumberOfAnswers(); i++) {
            out.println("<input type=\"submit\" value=\"" + answerList.get(i).getContent() + "\" name=\"answer\">");
        }
    %>
</div>
<%
    out.println("</form>");
%>

</body>
</html>
