package server.model;

import database.account.AccountDAO;
import database.account.AccountDAOImpl;
import database.creatingListings.ListingDAO;
import database.creatingListings.ListingDAOImpl;
import database.feedback.toaccount.FeedbackToAccountDAO;
import database.feedback.toaccount.FeedbackToAccountDAOImpl;
import database.feedback.toitem.FeedbackToItemDAO;
import database.feedback.toitem.FeedbackToItemDAOImpl;
import stuffs.Account;
import stuffs.Listing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.sql.SQLException;

public class ServerModelImpl implements ServerModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private AccountDAO accountDAO;
  private ListingDAO listingDAO;
  private FeedbackToAccountDAO feedbackToAccountDAO;
  private FeedbackToItemDAO feedbackToItemDAO;

  public ServerModelImpl()
  {
    try
    {
      accountDAO = AccountDAOImpl.getInstance();
      listingDAO = ListingDAOImpl.getInstance();
      feedbackToAccountDAO = FeedbackToAccountDAOImpl.getInstance();
      feedbackToItemDAO = FeedbackToItemDAOImpl.getInstance();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }

  @Override public boolean createListing(String title, String descText, String price, String category, String location, String duration, String date)
  {
    try
    {
      Listing temp = listingDAO.create(title, descText, category, location, Double.parseDouble(price), duration, Date.valueOf(date));
      if (temp != null)
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    } return false;
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber)
  {
    try
    {
      Account temp = accountDAO.createAccount(name, email, password1, address, phoneNumber);
      if (temp != null)
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean checkLogIn(String email, String password)
  {
    try
    {
      Account temp = accountDAO.readByEmail(email);
      if (temp != null)
      {
        if (temp.getEmail().equals(email) && temp.getPassword().equals(password))
        {
          return true;
        }
        else
        {
          return false;
        }
      }
    }
    catch (SQLException e)
    {
      System.out.println(e.getMessage());
      return false;
    }
    return false;
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio)
  {
    try
    {
      Account temp = accountDAO.createAccount(name, email, password1, address, phoneNumber, bio);
      if (temp != null)
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }
}
