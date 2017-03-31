package uk.co.jrtapsell.serverManager.connectors;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author James Tapsell
 */
public class Utils {
  static UUID getId(ResultSet rs, String col) throws SQLException {
    return UUID.fromString(rs.getString(col));
  }

  public static void putId(PreparedStatement ps, UUID id, int column) throws SQLException {
    ps.setString(column, id.toString());
  }
}
