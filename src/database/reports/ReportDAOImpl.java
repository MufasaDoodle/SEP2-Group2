package database.reports;

import database.account.AccountDAOImpl;
import stuffs.Account;
import stuffs.Report;
import stuffs.Request;
import stuffs.RequestListing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles report details in the database
 * @author Group 2
 */
public class ReportDAOImpl implements ReportDAO
{
  private static ReportDAOImpl instance;

  private ReportDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized ReportDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new ReportDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/projectsep2", "group2", "password");
  }

  @Override public Report create(int reportFrom, int reportedItemId,
      int reportedAccountId, int reportedItemFeedbackId
      , Date date)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO  \"SEP2\".reports (reportfrom, reporteditemid, reportedaccountid, reportedItemfeedbackid, date) " + "VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, reportFrom);
      statement.setInt(2, reportedItemId);
      statement.setInt(3, reportedAccountId);
      statement.setInt(4, reportedItemFeedbackId);
      statement.setDate(5, date);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        System.out.println("Report created in database");
        return new Report(keys.getInt(1), reportFrom, reportedItemId, reportedAccountId, reportedItemFeedbackId, date.toString());
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public List<Report> getAll() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT *  FROM \"SEP2\".reports ");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Report> result = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        int reportfromid = resultSet.getInt("reportfrom");
        int itemid = resultSet.getInt("reporteditemid");
        int accountid = resultSet.getInt("reportedaccountid");
        int feedbackitemid = resultSet.getInt("reporteditemfeedbackid");
        Date date = resultSet.getDate("date");

        Report report = new Report(id,reportfromid,itemid,accountid,feedbackitemid,date.toString());
        result.add(report);
      }
      return result;
    }
  }

  @Override public void delete(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".reports WHERE id = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }

  @Override public void deleteByAccount(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".reports WHERE reportfrom = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }

  @Override public void deleteByItem(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".reports WHERE reporteditemid = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }

  @Override public void deleteByItemFeedback(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM \"SEP2\".reports WHERE reporteditemfeedbackid = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }

  @Override public Report getByItemId(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".reports WHERE reporteditemId = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        int idId = resultSet.getInt("id");
        int reportFrom = resultSet.getInt("reportfrom");
        int reportedAccountId = resultSet.getInt("reportedaccountid");
        int reportedFeedbackId = resultSet.getInt("reporteditemfeedbackid");
        Date date = resultSet.getDate("date");
        return new Report(idId, reportFrom, id, reportedAccountId,reportedFeedbackId, date.toString());
      }
      else
      {
        return null;
      }
    }
  }

  @Override public Report getByFeedbackId(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".reports WHERE reporteditemfeedbackid = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        int idId = resultSet.getInt("id");
        int reportFrom = resultSet.getInt("reportfrom");
        int reportedAccountId = resultSet.getInt("reportedaccountid");
        int itemId = resultSet.getInt("reporteditemid");
        Date date = resultSet.getDate("date");
        return new Report(idId, reportFrom, itemId, reportedAccountId,id, date.toString());
      }
      else
      {
        return null;
      }
    }
  }

  @Override public Report getByAccountId(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"SEP2\".reports WHERE reportedaccountid = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        int idId = resultSet.getInt("id");
        int reportFrom = resultSet.getInt("reportfrom");
        int reportedItemFeedback = resultSet.getInt("reporteditemfeedbackid");
        int itemId = resultSet.getInt("reporteditemid");
        Date date = resultSet.getDate("date");
        return new Report(idId, reportFrom, itemId, id,reportedItemFeedback, date.toString());
      }
      else
      {
        return null;
      }
    }
  }

}