package uk.co.jrtapsell.serverManager.serverlets;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author James Tapsell
 */
public abstract class TableView extends Root {
  public TableView() throws SQLException, UnknownHostException {}


  public void setAttributes(HttpServletRequest request, Object keys) {
    request.setAttribute("data", keys);
    request.setAttribute("titles", getTitles());
    request.setAttribute("page_title", getTitle());
    request.setAttribute("page_dialogue", getDialogue());
  }


  protected void doGet(
      final HttpServletRequest request,
      final HttpServletResponse response) throws ServletException, IOException {

    super.doGet(request, response);
    final ServletContext context = getServletContext();
    final Object keys;
    try {
      keys = getData();
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

  public abstract String getDialogue();


  public abstract List<String> getTitles();

  public abstract List<?> getData() throws Exception;

  @Override
  public String getJSPName() {
    return "tableview";
  }
}
