package server.model;

import database.account.AccountDAO;
import database.account.AccountDAOImpl;
import database.creatingListings.ListingDAO;
import database.creatingListings.ListingDAOImpl;
import database.feedback.toaccount.FeedbackToAccountDAO;
import database.feedback.toaccount.FeedbackToAccountDAOImpl;
import database.feedback.toitem.FeedbackToItemDAO;
import database.feedback.toitem.FeedbackToItemDAOImpl;

import database.messages.MessagesDAO;
import database.messages.MessagesDAOImpl;
import shared.transferobjects.Message;

import database.requests.RequestDAO;
import database.requests.RequestDAOImpl;
import database.transactions.TransactionDAO;
import database.transactions.TransactionDAOImpl;
import stuffs.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServerModelImpl implements ServerModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private AccountDAO accountDAO;
  private ListingDAO listingDAO;
  private FeedbackToAccountDAO feedbackToAccountDAO;
  private FeedbackToItemDAO feedbackToItemDAO;
  private MessagesDAO messageDAO;
  private List<Message> messages;
  private List<Listing> listings;
  private RequestDAO requestDAO;
  private TransactionDAO transactionDAO;

  private List<Integer> deletedItemIds;
  private List<Integer> rentedItemIds;

  public ServerModelImpl()
  {
    try
    {
      accountDAO = AccountDAOImpl.getInstance();
      listingDAO = ListingDAOImpl.getInstance();
      feedbackToAccountDAO = FeedbackToAccountDAOImpl.getInstance();
      feedbackToItemDAO = FeedbackToItemDAOImpl.getInstance();

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

  @Override public List<Listing> getSorting(String request, String title,
      String category, String location) throws RemoteException
  {
    try
    {
      if (request.equals("title"))
        return listingDAO.readByTitle(title);
      else if (request.equals("category"))
        return listingDAO.readByCategory(category);
      else if (request.equals("location"))
        return listingDAO.readByLocation(location);
      else if (request.equals("titleCategory"))
        return listingDAO.titleCategory(title, category);
      else if (request.equals("titleLocation"))
        return listingDAO.titleLocation(title, location);
      else if (request.equals("categoryLocation"))
        return listingDAO.categoryLocation(category, location);
      else if (request.equals("titleCategoryLocation"))
        return listingDAO.titleCategoryLocation(title, category, location);
      else if (request.equals("oldNew"))
        return listingDAO.oldToNew();
      else if (request.equals("newOld"))
        return listingDAO.newToOld();
      else if (request.equals("ratingLowHigh"))
        return listingDAO.starRatingLowToHigh();
      else if (request.equals("ratingHighLow"))
        return listingDAO.starRatingHighToLow();
      else if (request.equals("priceLowHigh"))
        return listingDAO.priceLowToHigh();
      else if (request.equals("priceHighLow"))
        return listingDAO.priceHighToLow();
      else if (request.equals("titleOldNew"))
        return listingDAO.titleOldNew(title);
      else if (request.equals("titleNewOld"))
        return listingDAO.titleNewOld(title);
      else if (request.equals("titleRatingLowHigh"))
        return listingDAO.titleRatingLowToHigh(title);
      else if (request.equals("titleRatingHighLow"))
        return listingDAO.titleRatingHighToLow(title);
      else if (request.equals("titlePriceLowHigh"))
        return listingDAO.titlePriceLowToHigh(title);
      else if (request.equals("titlePriceHighLow"))
        return listingDAO.titlePriceHighToLow(title);
      else if (request.equals("categoryOldNew"))
        return listingDAO.categoryOldNew(category);
      else if (request.equals("categoryNewOld"))
        return listingDAO.categoryNewOld(category);
      else if (request.equals("categoryRatingLowHigh"))
        return listingDAO.categoryRatingLowToHigh(category);
      else if (request.equals("categoryRatingHighLow"))
        return listingDAO.categoryRatingHighToLow(category);
      else if (request.equals("categoryPriceLowHigh"))
        return listingDAO.categoryPriceLowToHigh(category);
      else if (request.equals("categoryPriceHighLow"))
        return listingDAO.categoryPriceHighToLow(category);
      else if (request.equals("locationOldNew"))
        return listingDAO.locationOldNew(location);
      else if (request.equals("locationNewOld"))
        return listingDAO.locationNewOld(location);
      else if (request.equals("locationRatingLowHigh"))
        return listingDAO.locationRatingLowToHigh(location);
      else if (request.equals("locationRatingHighLow"))
        return listingDAO.locationRatingHighToLow(location);
      else if (request.equals("locationPriceLowHigh"))
        return listingDAO.locationPriceLowToHigh(location);
      else if (request.equals("locationPriceHighLow"))
        return listingDAO.locationPriceHighToLow(location);
      else if (request.equals("titleCategoryOldNew"))
        return listingDAO.titleCategoryOldNew(title, category);
      else if (request.equals("titleCategoryNewOld"))
        return listingDAO.titleCategoryNewOld(title, category);
      else if (request.equals("titleCategoryRatingLowHigh"))
        return listingDAO.titleCategoryRatingLowToHigh(title, category);
      else if (request.equals("titleCategoryRatingHighLow"))
        return listingDAO.titleCategoryRatingHighToLow(title, category);
      else if (request.equals("titleCategoryPriceLowHigh"))
        return listingDAO.titleCategoryPriceLowToHigh(title, category);
      else if (request.equals("titleCategoryPriceHighLow"))
        return listingDAO.titleCategoryPriceHighToLow(title, category);
      else if (request.equals("titleLocationOldNew"))
        return listingDAO.titleLocationOldNew(title, location);
      else if (request.equals("titleLocationNewOld"))
        return listingDAO.titleLocationNewOld(title, location);
      else if (request.equals("titleLocationRatingLowHigh"))
        return listingDAO.titleLocationRatingLowToHigh(title, location);
      else if (request.equals("titleLocationRatingHighLow"))
        return listingDAO.titleLocationRatingHighToLow(title, location);
      else if (request.equals("titleLocationPriceLowHigh"))
        return listingDAO.titleLocationPriceLowToHigh(title, location);
      else if (request.equals("titleLocationPriceHighLow"))
        return listingDAO.titleLocationPriceHighToLow(title, location);
      else if (request.equals("categoryLocationOldNew"))
        return listingDAO.categoryLocationOldNew(category, location);
      else if (request.equals("categoryLocationNewOld"))
        return listingDAO.categoryLocationNewOld(category, location);
      else if (request.equals("categoryLocationRatingLowHigh"))
        return listingDAO.categoryLocationRatingLowToHigh(category, location);
      else if (request.equals("categoryLocationRatingHighLow"))
        return listingDAO.categoryLocationRatingHighToLow(category, location);
      else if (request.equals("categoryLocationPriceLowHigh"))
        return listingDAO.categoryLocationPriceLowToHigh(category, location);
      else if (request.equals("categoryLocationPriceHighLow"))
        return listingDAO.categoryLocationPriceHighToLow(category, location);
      else if (request.equals("titleCategoryLocationOldNew"))
        return listingDAO
            .titleCategoryLocationOldNew(title, category, location);
      else if (request.equals("titleCategoryLocationNewOld"))
        return listingDAO
            .titleCategoryLocationNewOld(title, category, location);
      else if (request.equals("titleCategoryLocationRatingLowHigh"))
        return listingDAO
            .titleCategoryLocationRatingLowToHigh(title, category, location);
      else if (request.equals("titleCategoryLocationRatingHighLow"))
        return listingDAO
            .titleCategoryLocationRatingHighToLow(title, category, location);
      else if (request.equals("titleCategoryLocationPriceLowHigh"))
        return listingDAO
            .titleCategoryLocationPriceLowToHigh(title, category, location);
      else if (request.equals("titleCategoryLocationPriceHighLow"))
        return listingDAO
            .titleCategoryLocationPriceHighToLow(title, category, location);
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

  @Override public boolean createListing(String title, String descText,
      String price, String category, String location, String duration,
      String date, int accountId, String promoted)
  {
    try
    {
      Listing temp = listingDAO.create(title, descText, category, location,
          Double.parseDouble(price), duration, Date.valueOf(date), accountId,promoted);
      // support.firePropertyChange("NewListing", null, temp);
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

  @Override public boolean createAccount(String name, String email,
      String password1, String address, String phoneNumber)
  {
    try
    {
      if (accountDAO.readByEmail(email) == null)
      {
        Account temp = accountDAO
            .createAccount(name, email, password1, address, phoneNumber);
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

  @Override public boolean checkLogIn(String email, String password)
  {
    try
    {
      Account temp = accountDAO.readByEmail(email);
      if (temp != null)
      {
        if (temp.getEmail().equals(email) && temp.getPassword()
            .equals(password))
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

  @Override public boolean createAccount(String name, String email,
      String password1, String address, String phoneNumber, String bio)
  {
    try
    {
      if (accountDAO.readByEmail(email) == null)
      {
        Account temp = accountDAO
            .createAccount(name, email, password1, address, phoneNumber, bio);
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

  @Override
  public String getAccountName(String email) throws RemoteException {
    try {
      return accountDAO.getAccountName(email);
    } catch (SQLException e) {
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

  @Override public void createRequest(int itemId, int requestFrom,
      int requestTo)
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

  @Override
  public boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName) throws RemoteException {
    try {
        FeedbackToItem temp = feedbackToItemDAO.createFeedback(starRating, feedback, itemId, accountId, accountName);
        if (temp != null) {
          return true;
        } else {
          return false;
        }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public List<FeedbackToItem> getFeedbackItems(int itemId) throws RemoteException {
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

  @Override
  public String getAvgStarRating(int itemId) {
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

  @Override
  public List<Integer> getRentedTo(int itemId){
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

  @Override public void createTransaction(int itemId, String date,
      int rentedToId, int rentedFromId)
  {
    try
    {
      transactionDAO
          .create(itemId, Date.valueOf(date), rentedToId, rentedFromId);
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

  @Override public List<TransactionListing> getTransactionByRentedTo(
      int rentedTo)
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

  @Override public List<TransactionListing> getTransactionByRentedFrom(
      int rentedFrom)
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

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}
