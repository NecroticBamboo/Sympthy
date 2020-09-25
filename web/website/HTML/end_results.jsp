<%@ page import="DI.IServiceLocator" %>
<%@ page import="Interfaces.*" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 23/09/2020
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

    TestInfo test = testManagement.getTestInfoByName(testName);
    ArrayList<QuestionInfo> list = testManagement.getQuestionInfo(test);

%>
<p>Your results are</p>
<div>
    <%
        for(int i=0;i<list.size();i++){
            out.println("<p>Question context: "+list.get(i).getContent()+"<br>");

            if(answerSequence.startsWith("1", i)){
                out.println("Your answer was: Correct</p>");
            }else{
                out.println("Your answer was: Incorrect</p>");
            }
        }
    %>
</div>

<div class="navigation-header">
    <%
        out.println("<a href=homePage_student.jsp?email=" + email + "> Go back</a>");
    %>
</div>

</body>
</html>
