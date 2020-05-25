package database.messages;

import shared.transferobjects.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAOImpl implements MessagesDAO
{

  private static MessagesDAOImpl instance;

  private MessagesDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized MessagesDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new MessagesDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/projectsep2", "group2", "password");
  }

  @Override public void saveMessage(Message message) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO \"SEP2\".message(text, fromaccount, toaccount) VALUES(?, ?, ?)");
      statement.setString(1, message.getMessage());
      statement.setInt(2, message.getFromAccount());
      statement.setInt(3, message.getToAccount());
      statement.executeUpdate();

      System.out.println("Message saved");
    }
  }

  @Override public List<Message> getAllMessagesFromAccount(int fromAccount) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".message WHERE fromaccount = ?");
      statement.setInt(1, fromAccount);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Message> result = new ArrayList<>();
      while (resultSet.next())
      {
        String text = resultSet.getString("text");
        int fromAccountID = resultSet.getInt("fromaccount");
        int toAccountID = resultSet.getInt("toaccount");
        Message message = new Message(text, fromAccountID, toAccountID);
        result.add(message);
      }
      return result;
    }
  }

  @Override public List<Message> getAllMessagesToAccount(int toAccount) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".message WHERE toaccount = ?");
      statement.setInt(1, toAccount);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Message> result = new ArrayList<>();
      while (resultSet.next())
      {
        String text = resultSet.getString("text");
        int fromAccountID = resultSet.getInt("fromaccount");
        int toAccountID = resultSet.getInt("toaccount");
        Message message = new Message(text, fromAccountID, toAccountID);
        result.add(message);
      }
      return result;
    }
  }

  @Override public List<Message> getAllMessagesInvolvingAccount(int account) throws SQLException
  {
    List<Message> result = getAllMessagesFromAccount(account);
    result.addAll(getAllMessagesToAccount(account));

    return result;
  }

  @Override public List<Message> getAllMessagesBetween(int account1, int account2) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".message WHERE (toaccount = ? AND fromaccount = ?) OR (toaccount = ? AND fromaccount = ? )");
      statement.setInt(1, account1);
      statement.setInt(2, account2);
      statement.setInt(3, account2);
      statement.setInt(4, account1);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Message> result = new ArrayList<>();
      while (resultSet.next())
      {
        String text = resultSet.getString("text");
        int fromAccountID = resultSet.getInt("fromaccount");
        int toAccountID = resultSet.getInt("toaccount");
        Message message = new Message(text, fromAccountID, toAccountID);
        result.add(message);
      }
      return result;
    }
  }

  @Override public void deleteByAccount(int accountId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".message WHERE fromaccount = ? OR toaccount = ?");
      statement.setInt(1, accountId);
      statement.setInt(2, accountId);
      statement.executeUpdate();
    }
  }
}