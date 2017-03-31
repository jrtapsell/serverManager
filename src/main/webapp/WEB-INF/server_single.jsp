<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%=request.getAttribute("data")%>
<button id="delete" class="mdl-button mdl-js-button mdl-button--raised" type="button">Show Toast</button>
<script>
$("#delete").click(function() {
    request_delete();
})
</script>