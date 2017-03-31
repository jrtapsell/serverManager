package uk.co.jrtapsell.serverManager.resource;

import java.io.IOException;
import java.io.InputStream;

public enum Resource {
  KEY_SQL(ResourceType.SQL_QUERY, "key"),
  SERVER_SQL(ResourceType.SQL_QUERY, "server");

  private static final int EOF_VALUE = -1;
  private final String fileContent;

  Resource(final ResourceType type, final String name) {
    final String filePath = getFilename(type.getExtension(), name);
    final InputStream stream = getClass().getResourceAsStream(filePath);
    if (stream == null) {
      final String message = String.format("Missing fle %s", filePath);
      throw new AssertionError(message);
    }
    try {
      fileContent = getText(stream);
    } catch (final IOException ex) {
      final String message = String.format("Failed to load %s|%s", type.name(), name);
      throw new AssertionError(message, ex);
    }
  }

  private static String getFilename(final String extension, final String name) {
    return String.format("/%s/%s.%s", extension, name, extension);
  }

  private static String getText(final InputStream stream) throws IOException {
    final StringBuffer buffer = new StringBuffer(stream.available());
    for (int input = stream.read(); input != EOF_VALUE; input = stream.read()) {
      buffer.appendCodePoint(input);
    }
    return buffer.toString();
  }

  public String getFileContent() {
    return fileContent;
  }
}
