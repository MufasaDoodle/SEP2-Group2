package server.model;

import database.account.AccountDAO;
import database.account.AccountDAOImpl;
import database.creatingListings.ListingDAO;
import database.creatingListings.ListingDAOImpl;
import database.feedback.FeedbackToItemDAO;
import database.feedback.FeedbackToItemDAOImpl;

import database.messages.MessagesDAO;
import database.messages.MessagesDAOImpl;
import database.reports.ReportDAO;
import database.reports.ReportDAOImpl;
import shared.transferobjects.*;

import database.requests.RequestDAO;
import database.requests.RequestDAOImpl;
import database.transactions.TransactionDAO;
import database.transactions.TransactionDAOImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Group 2
 */
public class ServerModelImpl implements ServerModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private AccountDAO accountDAO;
  private ListingDAO listingDAO;
  private FeedbackToItemDAO feedbackToItemDAO;
  private MessagesDAO messageDAO;
  private List<Message> messages;
  private List<Listing> listings;
  private RequestDAO requestDAO;
  private TransactionDAO transactionDAO;
  private ReportDAO reportDAO;

  private List<Integer> deletedItemIds;
  private List<Integer> rentedItemIds;

  public ServerModelImpl()
  {
    try
    {
      accountDAO = AccountDAOImpl.getInstance();
      listingDAO = ListingDAOImpl.getInstance();
      feedbackToItemDAO = FeedbackToItemDAOImpl.getInstance();
      reportDAO = ReportDAOImpl.getInstance();
      messageDAO = MessagesDAOImpl.getInstance();
      requestDAO = RequestDAOImpl.getInstance();

      messages = new ArrayList<>();
      deletedItemIds = new ArrayList<>();
      rentedItemIds = new ArrayList<>();
      listings = new ArrayList<>();
      transactionDAO = TransactionDAOImpl.getInstance();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * A method that returns sorted listings.
   * Depending on the sorting the user has set, either by title, title and category, category and location etc. this method selects the specific listings from the database
   * @param request - the type of the requested sorting
   * @param title - title for the listing
   * @param category - category of the listing
   * @param location - location of the listing
   * @return Returns a list of listing depending on the sorting parameters
   */
  @Override public List<Listing> getSorting(String request, String title, String category, String location) throws RemoteException
  {
    try
    {
      if (request.equals("title"))
        return listingDAO.readByTitle(title);
      else if(request.equals("availableTitle"))
        return listingDAO.availableTitle(title);
      else if (request.equals("category"))
        return listingDAO.readByCategory(category);
      else if(request.equals("availableCategory"))
        return listingDAO.availableCategory(category);
      else if (request.equals("location"))
        return listingDAO.readByLocation(location);
      else if(request.equals("availableLocation"))
        return listingDAO.availableLocation(location);
      else if(request.equals("available"))
        return listingDAO.isAvailable();
      else if (request.equals("titleCategory"))
        return listingDAO.titleCategory(title, category);
      else if(request.equals("availableTitleCategory"))
        return listingDAO.availableTitleCategory(title, category);
      else if (request.equals("titleLocation"))
        return listingDAO.titleLocation(title, location);
      else if(request.equals("availableTitleLocation"))
        return listingDAO.availableTitleLocation(title, location);
      else if (request.equals("categoryLocation"))
        return listingDAO.categoryLocation(category, location);
      else if(request.equals("availableCategoryLocation"))
        return listingDAO.availableCategoryLocation(category, location);
      else if (request.equals("titleCategoryLocation"))
        return listingDAO.titleCategoryLocation(title, category, location);
      else if(request.equals("availableTitleCategoryLocation"))
        return listingDAO.availableTitleCategoryLocation(title, category, location);
      else if (request.equals("priceLowHigh"))
        return listingDAO.priceLowToHigh();
      else if (request.equals("priceHighLow"))
        return listingDAO.priceHighToLow();
      else if(request.equals("availablePriceLowHigh"))
        return listingDAO.availablePriceLowToHigh();
      else if(request.equals("availablePriceHighLow"))
        return listingDAO.availablePriceHighToLow();
      else if (request.equals("titlePriceLowHigh"))
        return listingDAO.titlePriceLowToHigh(title);
      else if (request.equals("titlePriceHighLow"))
        return listingDAO.titlePriceHighToLow(title);
      else if(request.equals("availableTitlePriceLowHigh"))
        return listingDAO.availableTitlePriceLowToHigh(title);
      else if(request.equals("availableTitlePriceHighLow"))
        return listingDAO.availableTitlePriceHighToLow(title);
      else if (request.equals("categoryPriceLowHigh"))
        return listingDAO.categoryPriceLowToHigh(category);
      else if (request.equals("categoryPriceHighLow"))
        return listingDAO.categoryPriceHighToLow(category);
      else if(request.equals("availableCategoryPriceLowHigh"))
        return listingDAO.availableCategoryPriceLowToHigh(category);
      else if(request.equals("availableCategoryPriceHighLow"))
        return listingDAO.availableCategoryPriceHighToLow(category);
      else if (request.equals("locationPriceLowHigh"))
        return listingDAO.locationPriceLowToHigh(location);
      else if (request.equals("locationPriceHighLow"))
        return listingDAO.locationPriceHighToLow(location);
      else if(request.equals("availableLocationPriceLowHigh"))
        return listingDAO.availableLocationPriceLowToHigh(location);
      else if(request.equals("availableLocationPriceHighLow"))
        return listingDAO.availableLocationPriceHighToLow(location);
      else if (request.equals("titleCategoryPriceLowHigh"))
        return listingDAO.titleCategoryPriceLowToHigh(title, category);
      else if (request.equals("titleCategoryPriceHighLow"))
        return listingDAO.titleCategoryPriceHighToLow(title, category);
      else if(request.equals("availableTitleCategoryPriceLowHigh"))
        return listingDAO.availableTitleCategoryPriceLowToHigh(title, category);
      else if(request.equals("availableTitleCategoryPriceHighLow"))
        return listingDAO.availableTitleCategoryPriceHighToLow(title, category);
      else if (request.equals("titleLocationPriceLowHigh"))
        return listingDAO.titleLocationPriceLowToHigh(title, location);
      else if (request.equals("titleLocationPriceHighLow"))
        return listingDAO.titleLocationPriceHighToLow(title, location);
      else if(request.equals("availableTitleLocationPriceLowHigh"))
        return listingDAO.availableTitleLocationPriceLowToHigh(title, location);
      else if(request.equals("availableTitleLocationPriceHighLow"))
        return listingDAO.availableTitleLocationPriceHighToLow(title, location);
      else if (request.equals("categoryLocationPriceLowHigh"))
        return listingDAO.categoryLocationPriceLowToHigh(category, location);
      else if (request.equals("categoryLocationPriceHighLow"))
        return listingDAO.categoryLocationPriceHighToLow(category, location);
      else if(request.equals("availableCategoryLocationPriceLowHigh"))
        return listingDAO.availableCategoryLocationPriceLowToHigh(category, location);
      else if(request.equals("availableCategoryLocationPriceHighLow"))
        return listingDAO.availableCategoryLocationPriceHighToLow(category, location);
      else if (request.equals("titleCategoryLocationPriceLowHigh"))
        return listingDAO.titleCategoryLocationPriceLowToHigh(title, category, location);
      else if (request.equals("titleCategoryLocationPriceHighLow"))
        return listingDAO.titleCategoryLocationPriceHighToLow(title, category, location);
      else if(request.equals("availableTitleCategoryLocationPriceLowHigh"))
        return listingDAO.availableTitleCategoryLocationPriceLowToHigh(title, category, location);
      else if(request.equals("availableTitleCategoryLocationPriceHighLow"))
        return listingDAO.availableTitleCategoryLocationPriceHighToLow(title, category, location);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new RuntimeException("There was some problem");
    }
    return null;
  }

  @Override public List<Listing> getListings()
  {
    try
    {
      return listingDAO.getAll();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve listings");
    }
  }

  @Override public Listing getListingByID(int id) throws RemoteException
  {
    try
    {
      return listingDAO.readById(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve listings");
    }
  }

  @Override public boolean createListing(String title, String descText, String price, String category, String location, String duration, String date, int accountId, String promoted)
  {
    try
    {
      Listing temp = listingDAO.create(title, descText, category, location, Double.parseDouble(price), duration, Date.valueOf(date), accountId, promoted);
      if (temp != null)
      {
        support.firePropertyChange("NewListing", null, temp);
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

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber)
  {
    try
    {
      if (accountDAO.readByEmail(email) == null)
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
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Checks if the entered email and password match together and with any account created
   * @param email - email for the account
   * @param password - password for the account
   */
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
      if (accountDAO.readByEmail(email) == null)
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
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public List<Message> getMessage(int account1, int account2)
  {
    try
    {
      return messageDAO.getAllMessagesBetween(account1, account2);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Error contacting database");
    }
  }

  /**
   * Sends a notification to the two chatters whenever a new message is being sent
   * @param msg - the sent message
   * @param fromAccount - the sender of the message
   * @param toAccount - the person the message should go to
   * @return the sent message
   */
  @Override public String broadCastMessage(String msg, int fromAccount, int toAccount)
  {
    Message message = new Message(msg, fromAccount, toAccount);
    messages.add(message);
    try
    {
      messageDAO.saveMessage(message);
      support.firePropertyChange("NewMessage", null, message);
      return message.getMessage();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Error contacting database");
    }
  }

  @Override public List<Message> getAllMessagesFromAccount(int fromAccount)
  {
    try
    {
      return messageDAO.getAllMessagesFromAccount(fromAccount);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Error contacting database");
    }
  }

  @Override public List<Message> getAllMessagesToAccount(int toAccount)
  {
    try
    {
      return messageDAO.getAllMessagesToAccount(toAccount);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Error contacting database");
    }
  }

  @Override public List<Message> getAllMessagesInvolvingAccount(int account)
  {
    try
    {
      return messageDAO.getAllMessagesInvolvingAccount(account);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Error contacting database");
    }
  }

  @Override public Account getAccountById(int id)
  {
    try
    {
      return accountDAO.readById(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public int getAccountId(String email)
  {
    try
    {
      return accountDAO.getAccountId(email);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return 0;
  }

  @Override public String getAccountName(String email) throws RemoteException
  {
    try
    {
      return accountDAO.getAccountName(email);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return "";
  }

  @Override public List<Listing> getListingsByAccountId(int accountId)
  {
    try
    {
      return listingDAO.readByAccountId(accountId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public boolean updateAccount(Account account)
  {
    try
    {
      accountDAO.update(account);
      return true;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean isEmailTaken(String email)
  {
    try
    {
      Account temp = accountDAO.readByEmail(email);
      if (temp == null)
      {
        return false;
      }
      return true;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return true;
  }

  @Override public boolean updateListing(Listing listing)
  {
    try
    {
      listingDAO.update(listing);
      return true;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public void deleteListing(int id)
  {
    try
    {
      listingDAO.delete(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addDeletedItemId(int itemId)
  {
    try
    {
      deletedItemIds.add(itemId);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<Integer> getDeletedItemIds()
  {
    try
    {
      return deletedItemIds;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void createRequest(int itemId, int requestFrom, int requestTo)
  {
    try
    {
      requestDAO.create(itemId, requestFrom, requestTo);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteRequest(int id)
  {
    try
    {
      requestDAO.delete(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteDecline(int itemId, int requestFromId)
  {
    try
    {
      requestDAO.deleteDecline(itemId, requestFromId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<RequestListing> getRequestByAccountId(int requestTo)

  {
    try
    {
      return requestDAO.getRequestByAccountId(requestTo);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Request getRequest(int itemId, int requestFrom)
  {
    try
    {
      return requestDAO.getRequest(itemId, requestFrom);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName)
  {
    try
    {
      FeedbackToItem temp = feedbackToItemDAO.createFeedback(starRating, feedback, itemId, accountId, accountName);
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

  @Override public List<FeedbackToItem> getFeedbackItems(int itemId)
  {
    try
    {
      return feedbackToItemDAO.getFeedback(itemId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public FeedbackToItem getFeedbackById(int id)
  {
    try
    {
      return feedbackToItemDAO.getFeedbackById(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public String getAvgStarRating(int itemId)
  {
    try
    {
      return feedbackToItemDAO.getAvgStarRating(itemId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return "";
  }

  @Override public List<Integer> getRentedTo(int itemId)
  {
    try
    {
      return transactionDAO.getRentedToId(itemId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void createTransaction(int itemId, String date, int rentedToId, int rentedFromId)
  {
    try
    {
      transactionDAO.create(itemId, Date.valueOf(date), rentedToId, rentedFromId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public Transaction getTransactionByItemId(int itemId)
  {
    try
    {
      return transactionDAO.getTransactionByItemId(itemId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public List<TransactionListing> getTransactionByRentedTo(int rentedTo)
  {
    try
    {
      return transactionDAO.getTransactionByRentedTo(rentedTo);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public List<TransactionListing> getTransactionByRentedFrom(int rentedFrom)
  {
    try
    {
      return transactionDAO.getTransactionByRentedFrom(rentedFrom);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void createReport(int reportFrom, int reportedItemId, int reportedAccountId, int reportedItemFeedbackId, String date)
  {
    {
      try
      {
        reportDAO.create(reportFrom, reportedItemId, reportedAccountId, reportedItemFeedbackId, Date.valueOf(date));
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public List<Report> getAllReports()
  {
    try
    {
      return reportDAO.getAll();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void deleteReport(int id)
  {
    try
    {
      reportDAO.delete(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteTransaction(int id)
  {
    try
    {
      transactionDAO.delete(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteAccount(int id)
  {
    try
    {
      accountDAO.delete(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteItemFeedback(int id)
  {
    try
    {
      feedbackToItemDAO.delete(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteTransactionByAccount(int id)
  {
    try
    {
      transactionDAO.deleteByAccount(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteTransactionByItem(int id)
  {
    try
    {
      transactionDAO.deleteByItem(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteFeedbackByItemId(int id)
  {
    try
    {
      feedbackToItemDAO.deleteByItemId(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteRequestByAccount(int id)
  {
    try
    {
      requestDAO.deleteByAccount(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteItemByAccount(int id)
  {
    try
    {
      listingDAO.deleteByAccount(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteReportByAccount(int id)
  {
    try
    {
      reportDAO.deleteByAccount(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteReportByItem(int id)
  {
    try
    {
      reportDAO.deleteByItem(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteReportByItemFeedback(int id)
  {
    try
    {
      reportDAO.deleteByItemFeedback(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteMessageByAccount(int accountId)

  {
    try
    {
      messageDAO.deleteByAccount(accountId);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public Report getReportByItemId(int id)
  {
    try
    {
      return reportDAO.getByItemId(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Report getReportByFeedbackId(int id) throws RemoteException
  {
    try
    {
      return reportDAO.getByFeedbackId(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Report getReportByAccountId(int id) throws RemoteException
  {
    try
    {
      return reportDAO.getByAccountId(id);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}