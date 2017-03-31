package uk.co.jrtapsell.serverManager.resource;

public enum ResourceType {
  SQL_QUERY("sql");

  private final String extension;

  ResourceType(final String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }
}
