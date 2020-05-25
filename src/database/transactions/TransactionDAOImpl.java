package database.transactions;

import stuffs.RequestListing;
import stuffs.Transaction;
import stuffs.TransactionListing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO
{
  private static TransactionDAOImpl instance;

  private TransactionDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized TransactionDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new TransactionDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/projectsep2", "group2", "password");
  }

  @Override public Transaction create(int itemId, Date date, int rentedToId, int rentedFromId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".Transactions(itemId, date, rentedtoid, rentedfromid) VALUES(?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, itemId);
      statement.setDate(2, date);
      statement.setInt(3, rentedToId);
      statement.setInt(4, rentedFromId);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        System.out.println("Request created in database");
        return new Transaction(keys.getInt(1), itemId, date.toString(), rentedToId, rentedToId);
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
          .prepareStatement("DELETE FROM \"SEP2\".transactions WHERE id = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }

  @Override public void deleteByAccount(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".transactions WHERE rentedtoid = ? OR rentedfromid = ?");
      statement.setInt(1, id);
      statement.setInt(2, id);
      statement.executeUpdate();
    }
  }

  @Override public void deleteByItem(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".transactions WHERE itemid = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }

  @Override public List<TransactionListing> getTransactionByRentedTo(int rentedTo) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT  l.title, l.category, l.price, t.date, l.duration, acc.name FROM \"SEP2\".Listings l,  \"SEP2\".Transactions t, \"SEP2\".Account acc WHERE l.id = t.itemid AND acc.accountid = t.rentedfromid AND t.rentedtoid = ?");
      statement.setInt(1, rentedTo);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<TransactionListing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String title = resultSet.getString("title");
        String category = resultSet.getString("category");
        double price = resultSet.getDouble("price");
        String date = resultSet.getString("date");
        String duration = resultSet.getString("duration");
        String accountName = resultSet.getString("name");
        String status = "From";

        TransactionListing transactionListing = new TransactionListing(title, category, price, date, duration, accountName, status);
        result.add(transactionListing);
      }
      return result;
    }
  }

  @Override public List<TransactionListing> getTransactionByRentedFrom(int rentedFrom) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT  l.title, l.category, l.price, t.date, l.duration, acc.name FROM \"SEP2\".Listings l,  \"SEP2\".Transactions t, \"SEP2\".Account acc WHERE l.id = t.itemid AND acc.accountid = t.rentedtoid AND t.rentedfromid = ?");
      statement.setInt(1, rentedFrom);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<TransactionListing> result = new ArrayList<>();
      while (resultSet.next())
      {
        String title = resultSet.getString("title");
        String category = resultSet.getString("category");
        double price = resultSet.getDouble("price");
        String date = resultSet.getString("date");
        String duration = resultSet.getString("duration");
        String name = resultSet.getString("name");
        String status = "To";

        TransactionListing transactionListing = new TransactionListing(title, category, price, date, duration, name, status);
        result.add(transactionListing);
      }
      return result;
    }
  }

  @Override public Transaction getTransactionByItemId(int itemId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".transactions WHERE itemid = ?");
      statement.setInt(1, itemId);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String date = resultSet.getString("date");
        int rentedTo = resultSet.getInt("rentedtoid");
        int rentedFrom = resultSet.getInt("rentedfromid");

        return new Transaction(id, itemId, date, rentedTo, rentedFrom);
      }
      else
      {
        return null;
      }
    }
  }

  @Override public List<Integer> getRentedToId(int itemId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".transactions WHERE itemid = ?");
      statement.setInt(1, itemId);
      ResultSet resultSet = statement.executeQuery();
      List<Integer> result = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String date = resultSet.getString("date");
        int rentedTo = resultSet.getInt("rentedtoid");
        int rentedFrom = resultSet.getInt("rentedfromid");

        result.add(rentedTo);
      }
      return result;
    }
  }
}