package uk.co.jrtapsell.serverManager.serverlets;

import static uk.co.jrtapsell.serverManager.HttpCode.NO_CONTENT;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.UUID;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.co.jrtapsell.serverManager.connectors.KeyConnector;
import uk.co.jrtapsell.serverManager.data.Key;

/**
 * @author James Tapsell
 */
@WebServlet("/keys/*")
public class KeySingle extends SingleView{
  private static final WebSocketManager INSTANCE = WebSocketManager.getInstance();

  public KeySingle() throws SQLException, UnknownHostException {}

  @Override
  protected Object getItem(UUID id) throws SQLException, NamingException {
    return MODEL.getKeyConnetor().getForUUID(id);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      final KeyConnector keyConnector = MODEL.getKeyConnetor();
      final UUID uuid = getUUID(req);
      Key key = keyConnector.getForUUID(uuid);
      keyConnector.delete(uuid);
      NO_CONTENT.setCode(resp);
      INSTANCE.postUpdate(WebSocketManager.MessageType.DELETE, key);
    } catch (SQLException ex) {
      throw new ServletException(ex);
    }
  }

  @Override
  public String getTitle() {
    return "Key";
  }

  @Override
  public String getJSPName() {
    return "key_single";
  }
}
