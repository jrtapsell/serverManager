<%@ page import="uk.co.jrtapsell.serverManager.Model" %>
<%@ page import="uk.co.jrtapsell.serverManager.data.Server" %>
<%@ page import="javax.servlet.jsp.JspWriter" %>
<%@ page import="uk.co.jrtapsell.serverManager.Renderer" %>
<%@ page import="java.util.List" %>
<%@ page import="uk.co.jrtapsell.serverManager.MyWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp mdl-cell--12-col">
      <tr>
      <%
      for(String s : (List<String>) request.getAttribute("titles")) {
       %><th><%=s%></th><%
      }
      %>
      </tr>
  <%
      MyWriter real = MyWriter.prep(out);
      for(Object s : (List<Object>) request.getAttribute("data")) {
          Renderer.renderRow(s, real);
      }
  %>
  </table>

<style>
    #add {
        position: absolute;
        bottom: 3em;
        right: 3em;
    }
</style>
<c:if test="${not empty page_dialogue}" >
    <jsp:include page='/WEB-INF/${page_dialogue}.jsp'/>
</c:if>

<button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored" id="add">
    <i class="material-icons">add</i>
</button>

<script>
    dialog = document.querySelector('dialog');
    if (! dialog.showModal) {
        dialogPolyfill.registerDialog(dialog);
    }
    $("#add").click(function() {
        dialog.showModal();
    });
    dialog.querySelector('.close').addEventListener('click', function() {
        dialog.close();
    });
</script>