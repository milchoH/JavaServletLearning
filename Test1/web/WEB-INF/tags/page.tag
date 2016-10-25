<%@ taglib prefix="ct" uri="http://jwd.bg/tags" %>
<%@ attribute name="title" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title></title>
</head>
<body>
<ct:Header title="${title}" />
<jsp:doBody/>
<ct:Footer/>
</body>
</html>