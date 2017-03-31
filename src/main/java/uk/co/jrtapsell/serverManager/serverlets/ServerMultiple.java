package uk.co.jrtapsell.serverManager.serverlets;

import static uk.co.jrtapsell.serverManager.serverlets.WebSocketManager.MessageType.CREATE;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.co.jrtapsell.serverManager.HttpCode;
import uk.co.jrtapsell.serverManager.data.Server;

/**
 * @author James Tapsell
 */
@WebServlet("/servers/")
public class ServerMultiple extends TableView {

  private static final WebSocketManager INSTANCE = WebSocketManager.getInstance();

  public ServerMultiple() throws SQLException, UnknownHostException {}

  @Override
  public String getDialogue() {
    return "server_tail";
  }

  public List<String> getTitles() {
    return Arrays.asList("ID", "Name", "IP", "Hostname", "Delete");
  }

  @Override
  public String getTitle() {
    return "Servers";
  }

  @Override
  public List<Server> getData() throws Exception {
    return MODEL.getServers();
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Server key = readFromRequest(req, Server.class);
    if (!key.validate()) {
      HttpCode.BAD_REQUEST.setCode(resp);
      return;
    }
    try {
      Server server = MODEL.getServerConnector().insert(key);
      resp.getWriter().println(json.toJson(server));
      INSTANCE.postUpdate(CREATE, server);
    } catch (SQLException e) {
      throw new ServletException(e);
    }
  }
}
