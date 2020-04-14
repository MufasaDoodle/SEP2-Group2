package server.model;

import database.account.AccountDAO;
import database.account.AccountDAOImpl;
import database.creatingListings.ListingDAO;
import database.creatingListings.ListingDAOImpl;
import database.feedback.toaccount.FeedbackToAccountDAO;
import database.feedback.toaccount.FeedbackToAccountDAOImpl;
import database.feedback.toitem.FeedbackToItemDAO;
import database.feedback.toitem.FeedbackToItemDAOImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
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

  @Override public void createListing(String title, String descText, String price, String category, String location, String duration, String date)
  {
    try
    {
      listingDAO.create(title, descText, category, location, Double.parseDouble(price), duration, date);
      System.out.println("Listing created");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void createAccount(String name, String email, String password1, String address, String phoneNumber)
  {
    try
    {
      accountDAO.createAccount(name, email, password1, address, phoneNumber);
      System.out.println("Account created");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void checkLogIn(String email, String password)
  {
    //todo
  }
}
