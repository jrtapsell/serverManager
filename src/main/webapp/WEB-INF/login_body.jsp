<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if ("true".equals(request.getParameter("error"))) {
      %>
        <h1>Login Failed</h1>
      <%
    }
%>
<form method="POST" action="/j_security_check" class="mdl-cell--12-col">
    <div class="mdl-textfield mdl-js-textfield mdl-cell--12-col">
        <input class="mdl-textfield__input" type="text" id="j_username">
        <label class="mdl-textfield__label" for="j_username">Username</label>
    </div>
    <div class="mdl-textfield mdl-js-textfield mdl-cell--12-col">
        <input class="mdl-textfield__input" type="password" id="j_password">
        <label class="mdl-textfield__label" for="j_password">Password</label>
    </div>

    <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent mdl-cell--12-col">
        Login
    </button>
</form>