package uk.co.jrtapsell.serverManager.database;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ConnectionProvider {
  Connection makeConnection() throws SQLException;
}
