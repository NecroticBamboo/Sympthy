<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 22/08/2020
  Time: 16:41
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
    <title> Create question </title>
</head>

<body>

<%
//    int numberOfQuestions = Integer.parseInt(request.getParameter("numberOfQuestions"));
//    int currentQuestionIndex = Integer.parseInt(request.getParameter("currentQuestionIndex"));
    String testName = request.getParameter("test_name");
    String email=request.getParameter("email");
    String contextPath = request.getContextPath();
//    out.println("Current question index " + currentQuestionIndex + " out of " + numberOfQuestions);
    out.println("<form class=\"online_test_form\" action=" + contextPath + "/AddQuestion?test_name=" + testName +"&email="+email+" method=\"post\">");
%>
<%----%>
<label>Question content: </label><br>
<textarea name="question_content" rows="5" cols="40" style="resize: none"></textarea><br>
<label>Type of question: </label>
<select id="question_type" name="question_type"><br>
    <option value="Something 1">Something 1</option>
    <option value="Something 2">Something 2</option>
    <option value="Something 3">Something 3</option>
    <option value="Something 4">Something 4</option>
</select>
<br>
<label>Score value: </label>
<input type="text" name="score_value"><br>
<label>Number of answers: </label>
<input type="text" name="number_of_answers" id="number_of_answers"><br>
<label>Type of answers: </label>
<select id="answer_type" name="answer_type"><br>
    <option value="Something 1">Something 1</option>
    <option value="Something 2">Something 2</option>
    <option value="Something 3">Something 3</option>
    <option value="Something 4">Something 4</option>
</select>
<br>

<input type="submit" value="Submit" name="submit"/><br>
<input type="submit" value="Stop" name="submit"/>
<%
    out.println("</form>");
%>

</body>

</html>
