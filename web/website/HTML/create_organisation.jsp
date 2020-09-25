<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 14/07/2020
  Time: 15:24
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
    <title> Create organisation </title>
</head>

<body>

<header>
    <div class="flex-container-header">
        <div class="navigation-header-selected">
            <a href="../../index.jsp"> Online test</a>
        </div>
        <div class="navigation-header">
            Sign up
        </div>
        <div class="navigation-header">
            Create new organisation
        </div>
        <div class="navigation-header">
            <a href="create_test.jsp"> Create new online test</a>
        </div>
    </div>
</header>

<p>If you want to create new organisation, fill this form.</p>
<form class="online_test_form" action="${pageContext.request.contextPath}/CreateOrganisationServlet" method="post">
    <label>Organisation name:</label>
    <input type="text" name="org_name"><br>
    <label>Organisation address: </label>
    <input type="text" name="org_address"><br>

    <input type="submit" value="Submit" />
</form>
</body>

</html>
