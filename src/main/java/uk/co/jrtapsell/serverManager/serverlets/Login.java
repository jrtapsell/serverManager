package uk.co.jrtapsell.serverManager.serverlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author James Tapsell
 */
@WebServlet("/login/")
public class Login extends Root {
  @Override
  protected void setAttributes(HttpServletRequest request, Object keys) {
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doGet(req, resp);
    req.setAttribute("page_title", "Login");
    renderToPage(req, resp, getServletContext(), getJSPName());
  }

  @Override
  public String getTitle() {
    return "Login";
  }

  @Override
  public String getJSPName() {
    return "login_body";
  }
}
