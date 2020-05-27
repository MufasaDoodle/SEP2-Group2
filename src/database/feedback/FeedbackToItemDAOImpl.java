package database.feedback;

import stuffs.FeedbackToItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles feedback details in the database
 * @author Group 2
 */
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

  @Override public FeedbackToItem createFeedback(String starRating, String writtenFeedback, int itemId, int accountId, String accountName) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".FeedbackToItem (starRating,writtenFeedback, itemId, accountId, accountName) "
              + "VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, starRating);
      statement.setString(2, writtenFeedback);
      statement.setInt(3, itemId);
      statement.setInt(4, accountId);
      statement.setString(5, accountName);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new FeedbackToItem(keys.getInt(3), starRating, writtenFeedback,
            itemId, accountId, accountName);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override
  public List<FeedbackToItem> getFeedback(int itemId) throws SQLException {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * from \"SEP2\".Feedbacktoitem where itemid = ? ");
      statement.setInt(1, itemId);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<FeedbackToItem> result = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String starRating = resultSet.getString("starrating");
        String writtenFeedback = resultSet.getString("writtenfeedback");
        int accountId = resultSet.getInt("accountId");
        String accountName = resultSet.getString("accountName");
        FeedbackToItem temp = new FeedbackToItem(id, starRating, writtenFeedback, itemId, accountId, accountName);
        result.add(temp);
      }
      return result;
    }
  }

  @Override
  public String getAvgStarRating(int itemId) throws SQLException {

    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT\n" +
              "    round(AVG(CAST(\n" +
              "        COALESCE(\n" +
              "            NULLIF(\n" +
              "                regexp_replace(starrating, '[^-0-9.]+', ''), \n" +
              "                ''),\n" +
              "            '0') \n" +
              "       AS numeric)), 2) as avgstarrating\n" +
              "FROM\n" +
              "    \"SEP2\".feedbacktoitem \n" +
              "where itemid = ?; ");
      statement.setInt(1, itemId);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return String.valueOf(resultSet.getDouble("avgstarrating"));
      }
      else
      {
        return "";
      }
    }
  }

  @Override public FeedbackToItem getFeedbackById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT * from \"SEP2\".Feedbacktoitem where id = ? ");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next())
      {
        int itemId = resultSet.getInt("itemid");
        String starRating = resultSet.getString("starrating");
        String writtenFeedback = resultSet.getString("writtenfeedback");
        int accountId = resultSet.getInt("accountId");
        String accountName = resultSet.getString("accountName");
        FeedbackToItem temp = new FeedbackToItem(id, starRating, writtenFeedback, itemId, accountId, accountName);
        return temp;
      }
      else
      {
        return null;
      }
    }
  }

  @Override public void update(FeedbackToItem feedbackToItem)
      throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE \"SEP2\".FeedbackToItem SET  starRating = ?, writtenFeedback = ? WHERE itemId = ? AND id =?");
      statement.setString(1, feedbackToItem.getStartRating());
      statement.setString(2, feedbackToItem.getWrittenFeedback());
      statement.setInt(3, feedbackToItem.getItemId());
      statement.setInt(4, feedbackToItem.getId());
      statement.executeUpdate();
    }
  }

  @Override public void delete(int id)
      throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM \"SEP2\".FeedbackToItem WHERE id = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }

  @Override public void deleteByItemId(int id) throws SQLException
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM \"SEP2\".FeedbackToItem WHERE itemid = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }
}