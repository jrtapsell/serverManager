package uk.co.jrtapsell.serverManager.connectors;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import uk.co.jrtapsell.serverManager.database.ConnectionProvider;
import uk.co.jrtapsell.serverManager.data.Server;

/**
 * @author James Tapsell
 */
public class ServerConnector extends Connector<Server> {

  private static final String INSERT_QUERY = "INSERT INTO server (id, name, ip, hostname) VALUES (?,?,?,?) RETURNING *";

  public ServerConnector(ConnectionProvider provider) {
    super(provider);
  }

  @Override
  protected String getTableName() {
    return "server";
  }

  @Override
  protected Server parser(ResultSet rs) throws SQLException {
    UUID id = Utils.getId(rs, "id");
    String name = rs.getString("name");
    final String ip = rs.getString("ip");
    final String hostname = rs.getString("hostname");
    return new Server(id, name, ip, hostname);
  }

  public Server insert(Server server) throws SQLException {
    try (Connection connection = provider.makeConnection();
         PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {
      Utils.putId(ps, server.getId(), 1);
      ps.setString(2, server.getName());
      ps.setString(3, server.getAddress());
      ps.setString(4, server.getHostname());
      try (ResultSet rs = ps.executeQuery()) {
        return parser.getOne(rs);
      }
    }
  }

  @Override
  public void delete(UUID id) throws SQLException {
    try (Connection c = provider.makeConnection();
         PreparedStatement ps = c.prepareStatement("DELETE FROM server WHERE id = ?")) {
      ps.setString(1, id.toString());
      ps.execute();
    }
  }
}
