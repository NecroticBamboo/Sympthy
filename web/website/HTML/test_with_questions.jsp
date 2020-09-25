<%@ page import="DI.IServiceLocator" %>
<%@ page import="Interfaces.ITestManagement" %>
<%@ page import="Interfaces.TestInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Interfaces.QuestionInfo" %>
<%--
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
    <title> Browse test </title>
</head>

<body>

<div>
    <p>Test information:</p>
    <%
        ServletContext context=request.getServletContext();
        IServiceLocator serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        ITestManagement testManagement=(ITestManagement) serviceLocator.getService("ITestManagement");
        String testName=request.getParameter("test_name");
        String email=request.getParameter("email");
        TestInfo test =testManagement.getTestInfoByName(testName);

        out.println("Test name: "+ test.getTestName()+"<br>");
        out.println("Subject name: "+ test.getSubjectName()+"<br>");
        out.println("Number of questions: "+ test.getNumberOfQuestions()+"<br>");
        out.println("<a href=edit_test.jsp?test_name="+testName+"&email="+email+">Edit test</a><br>");
        out.println("<a href=add_question_to_test.jsp?test_name="+testName+"&email="+email+">Add questions</a>");
    %>
</div>

<div>
    <%
        ArrayList<QuestionInfo> list=testManagement.getQuestionInfo(test);
        if(list.size()==0){
        }else {
            out.println("<p>Questions: </p>");
            for (int i = 0; i < test.getNumberOfQuestions(); i++) {
                out.println("<p>Question content: " + list.get(i).getContent() + "</p>");
                out.print("<a href=add_answers.jsp?questionId=" + list.get(i).getId() + "&numberOfAnswers=" + list.get(i).getNumberOfAnswers() + "&test_name=" + testName +"&email="+email+ ">Add answers</a> \n");
                out.print("<a href=edit_answers.jsp?questionId=" + list.get(i).getId() + "&currentAnswerIndex=1&numberOfAnswers=" + list.get(i).getNumberOfAnswers() + "&test_name=" + testName + "&email="+email+">Edit answers</a> \n");
                out.print("<a href=edit_question.jsp?questionId=" + list.get(i).getId() + "&test_name=" + testName + "&email="+email+">Edit question</a><br>");
                out.print("<a href=delete_question.jsp?questionId=" + list.get(i).getId() + "&test_name=" + testName + "&email="+email+">Delete question</a><br>");
            }
        }

    %>

</div>
<div class="navigation-header">
    <%
        out.println("<a href=\"homePage_teacher.jsp?email="+email+"\"> Go back</a>");
    %>
</div>
</body>

</html>
