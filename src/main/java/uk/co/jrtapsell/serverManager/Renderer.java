package uk.co.jrtapsell.serverManager;

import java.io.IOException;
import uk.co.jrtapsell.serverManager.data.Key;
import uk.co.jrtapsell.serverManager.data.Server;

/**
 * @author James Tapsell
 */
public class Renderer {

  public static void renderRow(Object object, MyWriter writer) throws IOException {
    if (object instanceof Server) {
      renderRow((Server) object, writer);
      return;
    }
    if (object instanceof Key) {
      renderRow((Key) object, writer);
      return;
    }
    throw new AssertionError("Bad type:  " + object.getClass());
  }
  private static void renderRow(Server server, MyWriter writer) throws IOException {
    writer.printf(
        "<tr onclick='window.location=\"%s/\"'><td><pre>%s</pre></td><td>%s</td><td>%s</td><td>%s</td><td><button onclick=\"request_delete('%s/');event.stopPropagation()\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\">Delete</button></td></tr>",
        server.getId(),
        server.getId(),
        server.getName(),
        server.getAddress(),
        server.getHostname(),
        server.getId());
  }

  private static void renderRow(Key key, MyWriter writer) throws IOException {
    writer.printf(
        "<tr onclick='window.location=\"%s/\"'><td><pre>%s</pre></td><td>%s</td><td><button onclick=\"request_delete('%s/');event.stopPropagation()\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\">Delete</button></td></tr>",
        key.getId(),
        key.getId(),
        key.getName(),
        key.getId());
  }
}
