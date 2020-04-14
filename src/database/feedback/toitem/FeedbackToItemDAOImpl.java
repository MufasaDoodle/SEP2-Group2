package database.feedback.toitem;

import stuffs.FeedbackToItem;

import java.sql.*;
import java.util.List;

public class FeedbackToItemDAOImpl implements FeedbackToItemDAO
{
  private static FeedbackToItemDAOImpl instance;

  private FeedbackToItemDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized FeedbackToItemDAOImpl getInstance()
      throws SQLException
  {
    if (instance == null)
    {
      instance = new FeedbackToItemDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/projectsep2",
        "group2", "password");
  }

  @Override public FeedbackToItem createFeedback(int starRating, int itemId)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".FeedbackToItem (starRating,itemId) " + "VALUES (?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, starRating);
      statement.setInt(2, itemId);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new FeedbackToItem(keys.getInt(1), starRating, itemId);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public FeedbackToItem createFeedback(int starRating,
      String writtenFeedback, int itemId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".FeedbackToItem (starRating,writtenFeedback, itemId) "
              + "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, starRating);
      statement.setString(2, writtenFeedback);
      statement.setInt(3, itemId);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new FeedbackToItem(keys.getInt(1), starRating, writtenFeedback,
            itemId);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public FeedbackToItem createFeedback(String writtenFeedback,
      int itemId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".FeedbackToItem (writtenFeedback,itemId) "
              + "VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, writtenFeedback);
      statement.setInt(2, itemId);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new FeedbackToItem(keys.getInt(1), writtenFeedback, itemId);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  /*@Override public List<FeedbackToItem> readByLowToHigh() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT l.title, l.description, l.category, l.location, l.price, l.duration, l.date, l.avgStarRating  FROM Listings l JOIN FeedbackToItem f ON l.id = f.itemId GROUP BY l.avgStartRating ORDER BY ASC");

    }
  }*/

  @Override public void update(FeedbackToItem feedbackToItem)
      throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE \"SEP2\".FeedbackToItem SET  starRating = ?, writtenFeedback = ? WHERE itemId = ? AND id =?");
      statement.setInt(1, feedbackToItem.getStartRating());
      statement.setString(2, feedbackToItem.getWrittenFeedback());
      statement.setInt(3, feedbackToItem.getItemId());
      statement.setInt(4, feedbackToItem.getId());
      statement.executeUpdate();
    }
  }

  @Override public void delete(FeedbackToItem feedbackToItem)
      throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM \"SEP2\".FeedbackToItem WHERE id = ?");
      statement.setInt(1, feedbackToItem.getId());
      statement.executeUpdate();
    }
  }
}
