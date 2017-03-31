package uk.co.jrtapsell.serverManager.serverlets;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.UUID;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.co.jrtapsell.serverManager.Model;

/**
 * @author James Tapsell
 */
public abstract class SingleView extends Root {

  public SingleView() throws SQLException, UnknownHostException {}

  public void setAttributes(HttpServletRequest request, Object keys) {
    request.setAttribute("data", keys);
    request.setAttribute("page_title", getTitle());
  }

  protected void doGet(
      final HttpServletRequest request,
      final HttpServletResponse response) throws ServletException, IOException {

    super.doGet(request, response);
    final ServletContext context = getServletContext();
    final Object keys;
    final String[] split = request.getRequestURI().split("/");
    String idString = split[split.length - 1];
    UUID id = UUID.fromString(idString);

    try {
      keys = getItem(id);
    } catch (Exception e) {
      throw new ServletException(e);
    }

    if ("json".equals(request.getParameter("type"))) {
      response.setContentType("application/javascript");
      response.getWriter().print(json.toJson(keys));
    } else {
      setAttributes(request, keys);
      renderToPage(request, response, context, getJSPName());
    }

  }

  protected abstract Object getItem(UUID id) throws SQLException, NamingException;
}
