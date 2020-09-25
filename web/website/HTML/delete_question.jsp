<%@ page import="Interfaces.QuestionInfo" %>
<%@ page import="Interfaces.ITestManagement" %>
<%@ page import="DI.IServiceLocator" %>
<%@ page import="Interfaces.TestInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Interfaces.AnswerInfo" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 25/09/2020
  Time: 15:25
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
    <title> Delete question </title>
</head>

<body>
<%
    String testName = request.getParameter("test_name");
    String email=request.getParameter("email");
    String questionId = request.getParameter("questionId");

    ServletContext context = request.getServletContext();
    IServiceLocator serviceLocator = (IServiceLocator) context.getAttribute("ServiceLocator");
    ITestManagement testManagement = (ITestManagement) serviceLocator.getService("ITestManagement");

    QuestionInfo questionInfo = testManagement.getQuestionInfoById(Long.parseLong(questionId));
    TestInfo test=testManagement.getTestInfoByName(testName);
    ArrayList<AnswerInfo> list=testManagement.getAllAnswersInfo(questionInfo);

    if (list.size()!=0) {
        for (AnswerInfo answerInfo : list) {
            testManagement.removeAnswer(answerInfo);
        }
    }

    testManagement.removeQuestion(questionInfo);

    test.setNumberOfQuestions(test.getNumberOfQuestions()-1);
    testManagement.updateTest(test);
    response.sendRedirect(request.getContextPath() + "/website/HTML/test_with_questions.jsp?test_name=" + test.getTestName() + "&email=" + email);

%>
</body>

</html>
