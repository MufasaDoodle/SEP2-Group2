package database.feedback.toaccount;

import stuffs.FeedbackToAccount;

import java.sql.*;

public class FeedbackToAccountDAOImpl implements FeedbackToAccountDAO
{
  private static FeedbackToAccountDAOImpl instance;

  private FeedbackToAccountDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized FeedbackToAccountDAOImpl getInstance()
      throws SQLException
  {
    if (instance == null)
    {
      instance = new FeedbackToAccountDAOImpl();
    }
    return instance; 
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5433/projectsep2",
        "group2", "password");
  }

  @Override public FeedbackToAccount createFeedback(int starRating,
      int accountId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".FeedbackToItem (starRating,accountId) " + "VALUES (?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, starRating);
      statement.setInt(2, accountId);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new FeedbackToAccount(keys.getInt(1), starRating, accountId);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public FeedbackToAccount createFeedback(int starRating,
      String writtenFeedback, int accountId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".FeedbackToItem (starRating,writtenFeedback, accountId) "
              + "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, starRating);
      statement.setString(2, writtenFeedback);
      statement.setInt(3, accountId);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new FeedbackToAccount(keys.getInt(1), starRating,
            writtenFeedback, accountId);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public FeedbackToAccount createFeedback(String writtenFeedback,
      int accountId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".FeedbackToItem (writtenFeedback,accountId) "
              + "VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, writtenFeedback);
      statement.setInt(2, accountId);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new FeedbackToAccount(keys.getInt(1), writtenFeedback,
            accountId);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public void update(FeedbackToAccount feedbackToAccount)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE \"SEP2\".FeedbackToItem SET  starRating = ?, writtenFeedback = ? WHERE accountId = ? AND id =?");
      statement.setInt(1, feedbackToAccount.getStartRating());
      statement.setString(2, feedbackToAccount.getWrittenFeedback());
      statement.setInt(3, feedbackToAccount.getAccountId());
      statement.setInt(4, feedbackToAccount.getId());
      statement.executeUpdate();
    }
  }

  @Override public void delete(FeedbackToAccount feedbackToAccount)
      throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM \"SEP2\".FeedbackToItem WHERE id = ?");
      statement.setInt(1, feedbackToAccount.getId());
      statement.executeUpdate();
    }
  }
}
