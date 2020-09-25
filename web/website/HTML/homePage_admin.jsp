<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 17/08/2020
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="description" content="Web site with online tests">
    <meta name="keywords" content="tests,online tests,home,admin">
    <meta name="Andrew" content="u1821659">
    <title> Personal Office </title>
</head>

<body>


<h1>Welcome admin!</h1>
<p>This is your home page. From here you can do different things such as...</p>

<div class="flex-container-header">
    <%
        String email=request.getParameter("email");
        String contextPath = request.getContextPath();
        out.println("<form action=\""+contextPath+"/CheckProfileInfo?email="+email+"\" method=\"post\" class=\"navigation-header-selected\">" );
    %>
    <input class="button form-sub" name="adminProfile" type="submit" value="Check profile info" />
    <%
        out.println("</form>");
    %>

<%--    <div class="navigation-header-selected">--%>
<%--        <a href=""> Create class</a>--%>
<%--    </div>--%>
<%--    <div class="navigation-header">--%>
<%--        <a href=""> Create test</a>--%>
<%--    </div>--%>
<%--    <div class="navigation-header">--%>
<%--        <a href=""> Check results</a>--%>
<%--    </div>--%>
    <div class="navigation-header">
        <a href="../../index.jsp"> Sign out</a>
    </div>
</div>
</body>

</html>
