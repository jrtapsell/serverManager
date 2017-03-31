package uk.co.jrtapsell.serverManager.serverlets;

import static uk.co.jrtapsell.serverManager.HttpCode.NO_CONTENT;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.UUID;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.co.jrtapsell.serverManager.HttpCode;
import uk.co.jrtapsell.serverManager.Model;
import uk.co.jrtapsell.serverManager.connectors.ServerConnector;
import uk.co.jrtapsell.serverManager.data.Server;

/**
 * @author James Tapsell
 */
@WebServlet("/servers/*")
public class ServerSingle extends SingleView{
  private static final WebSocketManager INSTANCE = WebSocketManager.getInstance();

  public ServerSingle() throws SQLException, UnknownHostException {}

  @Override
  protected Object getItem(UUID id) throws SQLException, NamingException {
    return MODEL.getServerConnector().getForUUID(id);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      final ServerConnector serverConnector = MODEL.getServerConnector();
      final UUID id = getUUID(req);
      Server server = serverConnector.getForUUID(id);
      serverConnector.delete(id);
      NO_CONTENT.setCode(resp);
      INSTANCE.postUpdate(WebSocketManager.MessageType.DELETE, server);
    } catch (SQLException ex) {
      throw new ServletException(ex);
    }
  }

  @Override
  public String getTitle() {
    return "Server";
  }

  @Override
  public String getJSPName() {
    return "server_single";
  }
}
