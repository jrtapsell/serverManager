package uk.co.jrtapsell.serverManager.serverlets;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author James Tapsell
 */
@ServerEndpoint ("/ws/")
public class WebSocketSession {

  private Session session;

  @OnOpen
  public void onOpen(Session session){
    this.session = session;
    WebSocketManager.getInstance().onOpen(this);
  }

  @OnClose
  public void onClose(Session session){
    this.session = null;
    WebSocketManager.getInstance().onClose(this);
  }

  @OnMessage
  public void onMessage(String message){
    WebSocketManager.getInstance().onMessage(this, message);
  }

  public void sendMessage(String message) throws IOException {
    session.getBasicRemote().sendText(message);
  }
}
