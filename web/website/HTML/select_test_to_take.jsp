<%@ page import="DI.IServiceLocator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Interfaces.*" %>
<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 17/09/2020
  Time: 17:03
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
    <title> Select test </title>
</head>

<body>
<%
    ServletContext context = request.getServletContext();
    IServiceLocator serviceLocator = (IServiceLocator) context.getAttribute("ServiceLocator");
    ITestManagement testManagement = (ITestManagement) serviceLocator.getService("ITestManagement");

    String email = request.getParameter("student_email");
    String subjectName = request.getParameter("subject_name");
%>

<p>Please select the test that you want to take.</p>
<%
    ArrayList<TestInfo> list = testManagement.getTestInfoBySubject(subjectName);
    if (list == null) {
        out.println("<p>Tests with this subject don't exist</p>");
    } else for (TestInfo testInfo : list) {

        int numberOfQuestions=testInfo.getNumberOfQuestions();
        ArrayList<QuestionInfo> questionInfoList=testManagement.getQuestionInfo(testInfo);

        StringBuilder answerSequence= new StringBuilder();
        for(int i=0;i<numberOfQuestions;i++){
            answerSequence.append("0");
        }

        if(questionInfoList.size()==0){
            out.println("<div><p>Test name: " + testInfo.getTestName() + " (This test is not ready)<br>");
            break;
        } else {
            boolean error = false;
            for (int i = 0; i < questionInfoList.size(); i++) {
                ArrayList<AnswerInfo> answerInfoList = testManagement.getAllAnswersInfo(questionInfoList.get(i));
                if (answerInfoList.size() != questionInfoList.get(i).getNumberOfAnswers()) {
                    error = true;
                    out.println("<div><p>Test name: " + testInfo.getTestName() + " (This test is not ready)<br>");
                    break;
                }
            }
            if (!error) {
                out.println("<div><p>Test name: " + testInfo.getTestName() + "<br>");
                out.println("Number of questions: " + numberOfQuestions + "<br>");
                out.println("Timer: " + testInfo.getTimer() + "<br>");
                out.print("<a href=take_test.jsp?currentQuestionIndex=1&totalQuestions=" + numberOfQuestions + "&answer_sequence=" + answerSequence + "&student_email=" + email + "&test_name=" + testInfo.getTestName() + ">Take test</a></p></div> \n");
            }
        }
    }
%>

<div class="navigation-header">
    <%
        out.println("<a href=homePage_student.jsp?email=" + email + "> Go back</a>");
    %>
</div>
</body>

</html>
