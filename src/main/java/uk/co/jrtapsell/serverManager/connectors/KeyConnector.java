package uk.co.jrtapsell.serverManager.connectors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import javax.naming.Context;
import javax.naming.NamingException;
import uk.co.jrtapsell.serverManager.database.ConnectionProvider;
import uk.co.jrtapsell.serverManager.data.Key;

/**
 * @author James Tapsell
 */
public class KeyConnector extends Connector<Key> {

  private static final String INSERT_QUERY = "INSERT INTO key (id, name, data) VALUES (?,?,?) RETURNING *";

  public KeyConnector(ConnectionProvider provider) {
    super(provider);
  }

  @Override
  protected String getTableName() {
    return "key";
  }

  @Override
  protected Key parser(ResultSet rs) throws SQLException {
    UUID id = Utils.getId(rs, "id");
    String name = rs.getString("name");
    String data = rs.getString("data");
    return new Key(id, name, data);
  }

  @Override
  public void delete(UUID id) throws SQLException {
    try (Connection c = provider.makeConnection();
         PreparedStatement ps = c.prepareStatement("DELETE FROM key WHERE id = ?")) {
      ps.setString(1, id.toString());
      ps.execute();
    }
  }

  public Key insert(Key temp) throws SQLException {
    try (Connection connection = provider.makeConnection();
         PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
      Utils.putId(ps, temp.getId(), 1);
      ps.setString(2, temp.getName());
      ps.setString(3, temp.getData());
      try (ResultSet rs = ps.executeQuery()) {
        return parser.getOne(rs);
      }
    }
  }
}
