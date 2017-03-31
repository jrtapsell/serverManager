package uk.co.jrtapsell.serverManager.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.NamingException;
import uk.co.jrtapsell.serverManager.connectors.KeyConnector;
import uk.co.jrtapsell.serverManager.connectors.ServerConnector;
import uk.co.jrtapsell.serverManager.resource.Resource;

public class DatabaseWrapper {

  private final ConnectionProvider provider;

  public DatabaseWrapper(final ConnectionProvider provider) {
    this.provider = provider;
  }

  public void createTables() throws SQLException, NamingException {
    dropTable("server");
    dropTable("key");
    createTable(Resource.SERVER_SQL);
    createTable(Resource.KEY_SQL);
  }

  private void dropTable(final String server) throws SQLException, NamingException {
    try (Connection connection = provider.makeConnection();
         Statement statement = connection.createStatement()) {
      statement.execute(String.format("DROP TABLE IF EXISTS %s", server));
    }
  }

  private void createTable(final Resource resource) throws SQLException, NamingException {
    try (Connection connection = provider.makeConnection();
         Statement statement = connection.createStatement()) {
      statement.execute(resource.getFileContent());
    }
  }

  public ServerConnector getServerConnector() {
    return new ServerConnector(provider);
  }

  public KeyConnector getKeyConnector() {
    return new KeyConnector(provider);
  }
}
