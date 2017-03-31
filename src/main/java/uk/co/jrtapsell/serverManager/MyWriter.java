package uk.co.jrtapsell.serverManager;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;

/**
 * @author James Tapsell
 */
public class MyWriter {

  public static MyWriter prep(JspWriter writer) {
    return new MyWriter(writer);
  }

  private final JspWriter wrapped;

  public MyWriter(JspWriter wrapped) {
    this.wrapped = wrapped;
  }

  public void printf(String name, Object... args) throws IOException {
    wrapped.print(String.format(name, args));
  }
}
