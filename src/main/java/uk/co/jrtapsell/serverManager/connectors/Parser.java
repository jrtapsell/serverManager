package uk.co.jrtapsell.serverManager.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author James Tapsell
 */
@FunctionalInterface
public interface Parser<T> {
  public T apply(ResultSet rs) throws SQLException;
}
