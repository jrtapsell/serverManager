package uk.co.jrtapsell.serverManager;

import javax.servlet.http.HttpServletResponse;

/**
 * @author James Tapsell
 */
public enum HttpCode {
  OK(200),
  NO_CONTENT(204),
  BAD_REQUEST(400),
  SERVER_ERROR(500);

  private final int value;

  HttpCode(final int value) {
    this.value = value;
  }

  public int get() {
    return value;
  }

  public void setCode(final HttpServletResponse resp) {
    resp.setStatus(value);
  }
}
