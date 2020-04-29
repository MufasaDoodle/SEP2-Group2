package database.account;

import stuffs.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO
{
  private static AccountDAOImpl instance;

  private AccountDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized AccountDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new AccountDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/projectsep2", "group2", "password");
  }

  @Override public Account createAccount(String name, String email, String password, String address, String phoneNumber, String bio) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".account (name, email, password, address, telNumber, bio) " + "VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, name);
      statement.setString(2, email);
      statement.setString(3, password);
      statement.setString(4, address);
      statement.setString(5, phoneNumber);
      statement.setString(6, bio);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new Account(keys.getInt(1), name, email, password, address, phoneNumber, bio);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public Account createAccount(String name, String email, String password, String address, String phoneNumber) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO  \"SEP2\".account (name, email, password, address, telNumber) " + "VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, name);
      statement.setString(2, email);
      statement.setString(3, password);
      statement.setString(4, address);
      statement.setString(5, phoneNumber);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        System.out.println("Account created in database");
        return new Account(keys.getInt(1), name, email, password, address, phoneNumber);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public Account readById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Account WHERE accountId = ? ");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String telNumber = resultSet.getString("telNumber");
        String address = resultSet.getString("address");
        String bio = resultSet.getString("bio");

        return new Account(id, name, email, password, address, telNumber, bio);
      }
      else
      {
        return null;
      }
    }
  }

  @Override public Account readByEmail(String email) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Account WHERE email = ? ");
      statement.setString(1, email);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        int id = resultSet.getInt("accountId");
        String name = resultSet.getString("name");
        String localEmail = resultSet.getString("email");
        String password = resultSet.getString("password");
        String telNumber = resultSet.getString("telNumber");
        String address = resultSet.getString("address");
        String bio = resultSet.getString("bio");

        return new Account(id, name, localEmail, password, telNumber, address, bio);
      }
      else
      {
        return null;
      }
    }
  }

  @Override public List<Account> readByName(String searchString) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".Account WHERE name LIKE ? ");
      statement.setString(1, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Account> result = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("accountId");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String telNumber = resultSet.getString("telNumber");
        String address = resultSet.getString("address");
        String bio = resultSet.getString("bio");

        Account account = new Account(id, name, email, password, telNumber, address, bio);
        result.add(account);
      }
      return result;
    }
  }

  @Override public void update(Account account) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE \"SEP2\".Account SET name = ?, email = ?, password = ?, telNumber = ?, address = ?, bio = ? WHERE accountId=?");
      statement.setString(1, account.getName());
      statement.setString(2, account.getEmail());
      statement.setString(3, account.getPassword());
      statement.setString(4, account.getPhoneNumber());
      statement.setString(5, account.getAddress());
      statement.setString(6, account.getBio());
      statement.setInt(7, account.getId());
      statement.executeUpdate();
    }
  }

  @Override public void delete(Account account) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM \"SEP2\".Account WHERE accountId = ?");
      statement.setInt(1, account.getId());
      statement.executeUpdate();
    }
  }
}
