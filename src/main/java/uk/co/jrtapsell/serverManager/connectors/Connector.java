package uk.co.jrtapsell.serverManager.connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import javax.naming.Context;
import javax.naming.NamingException;
import uk.co.jrtapsell.serverManager.data.Key;
import uk.co.jrtapsell.serverManager.database.ConnectionProvider;

public abstract class Connector<T> {
  protected final ConnectionProvider provider;
  protected final ResultParser<T> parser = new ResultParser<>(this::parser);

  public Connector(ConnectionProvider provider) {
    this.provider = provider;
  }

  public T getForUUID(UUID id) throws SQLException {
    final String command = String.format("SELECT * FROM %s WHERE id = ?", getTableName());
    try (Connection c = provider.makeConnection();
         PreparedStatement s = c.prepareStatement(command)) {
      s.setString(1, id.toString());
      try (ResultSet rs = s.executeQuery()) {
        return parser.getOne(rs);
      }
    }
  }
  public List<T> getAll() throws SQLException {
    final String command = String.format("SELECT * FROM %s", getTableName());
    try (Connection c = provider.makeConnection();
         Statement s = c.createStatement()) {
      try (ResultSet rs = s.executeQuery(command)) {
        return parser.getAll(rs);
      }
    }

  }
  protected abstract String getTableName();
  protected abstract T parser(ResultSet rs) throws SQLException;

  public abstract void delete(UUID data) throws SQLException;

  public abstract T insert(T data) throws SQLException;
}
