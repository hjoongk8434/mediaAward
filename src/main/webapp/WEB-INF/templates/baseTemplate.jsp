<%--
  Created by IntelliJ IDEA.
  User: kya9t
  Date: 25. 11. 20.
  Time: 오후 7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title>meaid Award</title>
    <tiles:insertAttribute name="css"/>
    <tiles:insertAttribute name="scripts"/>
</head>
<body>
    <tiles:insertAttribute name="head"/>
    <div class="contents">
        <tiles:insertAttribute name="body"/>
    </div>
    <tiles:insertAttribute name="foot"/>
</body>
</html>
