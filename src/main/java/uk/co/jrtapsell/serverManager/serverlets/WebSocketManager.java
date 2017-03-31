package uk.co.jrtapsell.serverManager.serverlets;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author James Tapsell
 */
public class WebSocketManager {

  public enum MessageType {
    CREATE, UPDATE, DELETE;
  }

  public static final WebSocketManager instance = new WebSocketManager();

  public static WebSocketManager getInstance() {
    return instance;
  }

  private final ArrayList<WebSocketSession> sessions = new ArrayList<>();

  public void postUpdate(MessageType type, Object object) {
    JsonObject root = new JsonObject();
    root.add("payload",Root.json.toJsonTree(object));
    root.addProperty("type", object.getClass().getSimpleName().toLowerCase());
    root.addProperty("method", type.name().toLowerCase());
    messageAll(root.toString());
  }

  public void messageAll(String message) {
    for (WebSocketSession session : sessions) {
      try {
        session.sendMessage(message);
      } catch (IOException e) {}
    }
  }

  public void onMessage(WebSocketSession webSocketSession, String message) {

  }

  public void onOpen(WebSocketSession webSocketSession) {
    sessions.add(webSocketSession);
  }

  public void onClose(WebSocketSession webSocketSession) {
    sessions.remove(webSocketSession);
  }
}
