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
import uk.co.jrtapsell.serverManager.data.Key;

/**
 * @author James Tapsell
 */
@WebServlet("/keys/")
public class KeyMultiple extends TableView {

  private static final WebSocketManager INSTANCE = WebSocketManager.getInstance();

  public KeyMultiple() throws SQLException, UnknownHostException {
  }

  @Override
  public String getDialogue() {
    return "key_tail";
  }

  public List<String> getTitles() {
    return Arrays.asList("ID", "Name");
  }

  @Override
  public String getTitle() {
    return "Keys";
  }

  @Override
  public List<Key> getData() throws Exception {
    return MODEL.getKeys();
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Key key = readFromRequest(req, Key.class);
    if (!key.validate()) {
      final HttpCode badRequest = HttpCode.BAD_REQUEST;
      badRequest.setCode(resp);
      return;
    }
    try {
      Key k = MODEL.getKeyConnetor().insert(key);
      resp.getWriter().println(json.toJson(k));
      INSTANCE.postUpdate(CREATE, k);
    } catch (SQLException e) {
      throw new ServletException(e);
    }
  }

}
