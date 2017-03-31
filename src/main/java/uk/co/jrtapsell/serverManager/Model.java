package uk.co.jrtapsell.serverManager;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import uk.co.jrtapsell.serverManager.connectors.ServerConnector;
import uk.co.jrtapsell.serverManager.database.ConnectionProvider;
import uk.co.jrtapsell.serverManager.database.DatabaseWrapper;
import uk.co.jrtapsell.serverManager.connectors.KeyConnector;
import uk.co.jrtapsell.serverManager.data.Key;
import uk.co.jrtapsell.serverManager.data.Server;

/**
 * @author James Tapsell
 */
public class Model {

  private static class DataSourceProvider implements ConnectionProvider {
    private final DataSource ds;

    public DataSourceProvider(Context context) throws NamingException {
      ds = (DataSource) context.lookup("java:/comp/env/jdbc/database");
    }

    @Override
    public Connection makeConnection() throws SQLException {
      return ds.getConnection();
    }
  }

  private final DatabaseWrapper databaseWrapper;

  public Model() throws NamingException {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      throw new AssertionError(e);
    }
    databaseWrapper = new DatabaseWrapper(new DataSourceProvider(new InitialContext()));
  }

  public List<Server> getServers() throws SQLException, NamingException {
      return databaseWrapper.getServerConnector().getAll();
  }

  public List<Key> getKeys() throws SQLException, NamingException {
    return databaseWrapper.getKeyConnector().getAll();
  }

  public KeyConnector getKeyConnetor() {
    return databaseWrapper.getKeyConnector();
  }

  public ServerConnector getServerConnector() {
    return databaseWrapper.getServerConnector();
  }
}
