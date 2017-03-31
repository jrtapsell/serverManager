package uk.co.jrtapsell.serverManager.connectors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author James Tapsell
 */
public class ResultParser<T> {
  private final Parser<T> parser;

  public ResultParser(final Parser<T> parser) {
    this.parser = parser;
  }

  public T getOne(final ResultSet resultSet) throws SQLException {
    if (!resultSet.next()) {
      throw new AssertionError("No values returned");
    }
    final T apply = parser.apply(resultSet);
    if (resultSet.next()) {
      throw new AssertionError("Multiple values returned");
    }
    return apply;
  }

  public List<T> getAll(final ResultSet resultSet) throws SQLException {
    final List<T> data = new ArrayList<>();
    while (resultSet.next()) {
      data.add(parser.apply(resultSet));
    }
    return data;
  }
}
