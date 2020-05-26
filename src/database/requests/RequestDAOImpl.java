package database.requests;

import stuffs.Account;
import stuffs.Listing;
import stuffs.Request;
import stuffs.RequestListing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOImpl implements RequestDAO
{
  private static RequestDAOImpl instance;

  private RequestDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized RequestDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new RequestDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager
        .getConnection("jdbc:postgresql://localhost:5433/projectsep2", "group2",
            "password");
  }

  @Override public Request create(int itemId, int requestFrom, int requestTo)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO \"SEP2\".requests(itemId, requestFrom, requestTo) VALUES(?, ?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, itemId);
      statement.setInt(2, requestFrom);
      statement.setInt(3, requestTo);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        System.out.println("Request created in database");
        return new Request(keys.getInt(1), itemId, requestFrom, requestTo);
      }
      else
        throw new SQLException("No keys generated");
    }
  }

  @Override public void delete(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".Requests WHERE itemId = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }

  @Override public void deleteDecline(int itemId, int requestFromId)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".Requests WHERE itemId = ? AND requestfrom = ?");
      statement.setInt(1, itemId);
      statement.setInt(2, requestFromId);
      statement.executeUpdate();
    }
  }

  @Override public void deleteByAccount(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".Requests WHERE requestfrom = ? OR requestto = ?");
      statement.setInt(1, id);
      statement.setInt(2, id);
      statement.executeUpdate();
    }
  }

  @Override public List<RequestListing> getRequestByAccountId(int requestTo)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT l.title, l.category, l.price, l.duration, acc.name, r.itemid, r.requestfrom  FROM \"SEP2\".Listings l,  \"SEP2\".Requests r, \"SEP2\".Account acc \n WHERE l.id = r.itemid AND acc.accountid = r.requestFrom AND r.requestTo = ?");
      statement.setInt(1, requestTo);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<RequestListing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String title = resultSet.getString("title");
        String category = resultSet.getString("category");
        double price = resultSet.getDouble("price");
        String duration = resultSet.getString("duration");
        String requestFrom = resultSet.getString("name");
        int itemId = resultSet.getInt("itemid");
        int requestFromId = resultSet.getInt("requestfrom");

        RequestListing requestListing = new RequestListing(title, category, price, duration,requestFrom,itemId,requestFromId);
        result.add(requestListing);
      }
      return result;
    }
  }

  @Override public Request getRequest(int itemId, int requestFrom)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Requests WHERE itemId = ? AND requestFrom = ? ");
      statement.setInt(1, itemId);
      statement.setInt(2, requestFrom);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        int id = resultSet.getInt("id");
        int requestTo = resultSet.getInt("requestTo");
        return new Request(id, itemId, requestFrom, requestTo);
      }
      else
      {
        return null;
      }
    }
  }
}