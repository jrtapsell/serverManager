package uk.co.jrtapsell.serverManager.serverlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.UUID;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.co.jrtapsell.serverManager.Model;

/**
 * @author James Tapsell
 */
public abstract class Root extends HttpServlet {
  protected static final Model MODEL;
  public static final Gson json;

  static {
    try {
      MODEL = new Model();
    } catch (NamingException e) {
      throw new AssertionError(e);
    }
    GsonBuilder gb = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .setPrettyPrinting();
    json = gb.create();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getSession();
  }

  protected void setAttributes(HttpServletRequest request, Object keys) {
  }

  public void renderToPage(HttpServletRequest request, HttpServletResponse response, ServletContext context, String page) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = context.getRequestDispatcher("/WEB-INF/page.jsp");
    request.setAttribute("page_name", page);
    requestDispatcher.forward(request, response);
  }

  public abstract String getTitle();

  public abstract String getJSPName();

  public  <T> T readFromRequest(HttpServletRequest req, Class<T> type) throws IOException {
    return json.fromJson(req.getReader(), type);
  }

  public UUID getUUID(HttpServletRequest req) {
    String[] sections = req.getRequestURI().split("/");
    String idS = sections[sections.length - 1];
    return UUID.fromString(idS);
  }
}
