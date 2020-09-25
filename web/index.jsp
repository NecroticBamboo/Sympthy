<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 13/07/2020
  Time: 15:10
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
    <link rel="stylesheet" media="screen" href="website/CSS/main.css" />
    <title> Home </title>
</head>

<body>

<header>
    <div class="flex-container-header">
        <div class="navigation-header-selected">
            Online test
        </div>
        <div class="navigation-header">
            Create new user(inactive)
<%--            <a href="website/HTML/sign_up.jsp"> </a>--%>
        </div>
        <div class="navigation-header">
            <a href="website/HTML/create_organisation.jsp"> Create new organisation </a>
        </div>
        <div class="navigation-header">
            <a href="website/HTML/create_test.jsp"> Create new online test</a>
        </div>

    </div>
    <br>
    <div class="auth-form sign-in-form">
        <form action="${pageContext.request.contextPath}/Checked_in" accept-charset="UTF-8" method="post">

            <div class="form-fields">
                <label for="login">Email Address</label>
                <br>
                <input type="text" name="login" id="login" class="text-input" autocapitalize="off" />
                <br>
            </div>

            <input class="button form-sub" type="submit" value="Sign In" />
        </form>
        <p class="auth-link-mobile">
            Not a member? <a href="website/HTML/sign_up.jsp">Sign up now</a>
        </p>
    </div>
</header>

</body>

</html>
