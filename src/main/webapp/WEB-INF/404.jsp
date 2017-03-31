<%@page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("page_name", "404_body");
    request.setAttribute("page_title", "Page Not Found");
%>
<%@include file="page.jsp"%>