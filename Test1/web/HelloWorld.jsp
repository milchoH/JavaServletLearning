<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://jwd.bg/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset-ISO-8859-1">
    <title>Hello World</title>
</head>
<body>
<c:Header title="Test"/>
<h2>Hello JSP</h2>
<h3>new date is: <%= new java.util.Date()%> </h3>
<ct:Footer/>
</body>
</html>