<%@page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
for (Throwable c = exception; c != null; c = c.getCause()) {
  out.println("<b>" + c.getClass().getSimpleName() + "</b><br><value>" + c.getLocalizedMessage() + "</value><br>");
  %>
<pre><%
  for (StackTraceElement element : c.getStackTrace()) {
    out.println("> " + element);
  }
%></pre>
  <%
}%>
</body>
</html>
