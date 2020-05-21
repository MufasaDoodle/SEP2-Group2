package client.model;

import client.networking.Client;
import javafx.scene.control.Alert;
import shared.transferobjects.Message;
import stuffs.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientModelManager implements ClientModel
{
  private Client client;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private int currentItemID = 0;
  private String currentAccountName;
  private Account moderatedAccount;

  private int currentAccountID;
  private int viewingAccountID;
  private int feedbackId;

  private int currentChatterID;
  private String chatterName;
  private String itemName;

  private boolean fromListingViewOpen;
  private boolean fromModeratorOpen;

  public ClientModelManager(Client client)
  {
    this.client = client;
    client.startClient();
    client.addListener("NewMessage", this::onNewMessage);
  }

  @Override public boolean accountCheck()
  {
    Account account = getAccountById(getCurrentAccountID());
    return account != null;
  }

  @Override public void setFromListingViewOpen(boolean whereFrom)
  {
    fromListingViewOpen = whereFrom;
  }

  @Override public boolean getFromListingViewOpen()
  {
    return fromListingViewOpen;
  }

  @Override public void setModeratorOpen(boolean whereFrom)
  {
    fromModeratorOpen = whereFrom;
  }

  public int getFeedbackId()
  {
    return feedbackId;
  }

  public void setFeedbackId(int feedbackId)
  {
    this.feedbackId = feedbackId;
  }

  @Override public boolean getModeratorOpen()
  {
    return fromModeratorOpen;
  }

  @Override public void createRequest(int itemId, int requestFrom, int requestTo)
  {
    System.out.println("Request sent");
    client.createRequest(itemId, requestFrom, requestTo);
  }

  @Override public void deleteRequest(int id)
  {
    System.out.println("Request deleted");
    client.deleteRequest(id);
  }

  @Override public void deleteDecline(int itemId, int requestFromId)
  {
    System.out.println("Request deleted");
    client.deleteDecline(itemId, requestFromId);
  }

  @Override public List<RequestListing> getRequestByAccountId(int requestTo)
  {
    return client.getRequestByAccountId(requestTo);
  }

  @Override public Request getRequest(int itemId, int requestFrom)
  {
    return client.getRequest(itemId, requestFrom);
  }

  @Override public void createTransaction(int itemId, String date, int rentedToId, int rentedFromId)
  {
    System.out.println("Transaction created");
    client.createTransaction(itemId, date, rentedToId, rentedFromId);
  }

  @Override public Transaction getTransactionByItemId(int itemId)
  {
    return client.getTransactionByItemId(itemId);
  }

  @Override public List<TransactionListing> getTransactionByRentedTo(int rentedTo)
  {
    return client.getTransactionByRentedTo(rentedTo);
  }

  @Override public List<TransactionListing> getTransactionByRentedFrom(int rentedFrom)
  {
    return client.getTransactionByRentedFrom(rentedFrom);
  }

  @Override public void createReport(int reportFrom, int reportedItemId, int reportedAccountId, int reportedItemFeedbackId, String date)
  {
    client.createReport(reportFrom, reportedItemId, reportedAccountId, reportedItemFeedbackId, date);
  }

  @Override public List<Report> getAllReports()
  {
    System.out.println("All reports retrieved");
    return client.getAllReports();
  }

  @Override public void deleteReport(int id)
  {
    System.out.println("Report deleted");
    client.deleteReport(id);
  }

  @Override public void deleteTransaction(int id)
  {
    System.out.println("Transaction deleted");
    client.deleteTransaction(id);
  }

  @Override public void deleteAccount(int id)
  {
    System.out.println("Account deleted");
    client.deleteAccount(id);
  }

  @Override public void deleteItemFeedback(int id)
  {
    System.out.println("Item feedback deleted");
    client.deleteItemFeedback(id);
  }

  @Override public void deleteTransactionByAccount(int id)
  {
    System.out.println("Transaction deleted");
    client.deleteTransactionByAccount(id);
  }

  @Override public void deleteTransactionByItem(int id)
  {
    System.out.println("Transaction deleted");
    client.deleteTransactionByItem(id);
  }

  @Override public void deleteFeedbackByItemId(int id)
  {
    System.out.println("Feedback deleted");
    client.deleteFeedbackByItemId(id);
  }

  @Override public void deleteRequestByAccount(int id)
  {
    System.out.println("Request deleted");
    client.deleteRequestByAccount(id);
  }

  @Override public void deleteItemByAccount(int id)
  {
    System.out.println("Item deleted");
    client.deleteItemByAccount(id);
  }

  @Override public void deleteReportByAccount(int id)
  {
    System.out.println("Report deleted");
    client.deleteReportByAccount(id);
  }

  @Override public void deleteReportByItem(int id)
  {
    System.out.println("Report deleted");
    client.deleteReportByItem(id);
  }

  @Override public void deleteReportByItemFeedback(int id)
  {
    System.out.println("Report deleted");
    client.deleteReportByItemFeedback(id);
  }

  @Override public void deleteMessageByAccount(int id)
  {
    client.deleteMessageByAccount(id);
  }

  @Override public Report getReportByItemId(int id)
  {
    return client.getReportByItemId(id);
  }

  @Override public Report getReportByFeedbackId(int id)
  {
    return client.getReportByFeedbackId(id);
  }

  @Override public Report getReportByAccountId(int id)
  {
    return client.getReportByAccountId(id);
  }

  @Override public void setCurrentAccountID(String email)
  {
    currentAccountID = client.getAccountId(email);
  }

  @Override public int getCurrentAccountID()
  {
    return currentAccountID;
  }

  @Override public void setCurrentAccountName(String email)
  {
    currentAccountName = client.getAccountName(email);
  }

  @Override public String getCurrentAccountName()
  {
    return currentAccountName;
  }

  @Override public List<Listing> getListingsByAccount(int accountId)
  {
    System.out.println("Owners's listings have been retrieved");
    return client.getListingsByAccount(accountId);
  }

  @Override public boolean updateAccount(String email, String pass, String address, String number, String bio)
  {
    Account currentAccount = client.getAccountById(currentAccountID);
    Account updatedAccount = new Account(currentAccount.getId(), currentAccount.getName(), email, pass, address, number, bio);
    if (client.updateAccount(updatedAccount))
    {
      System.out.println("Account updated");
      return true;
    }
    return false;
  }

  @Override public boolean isEmailTaken(String email)
  {
    return client.isEmailTaken(email);
  }

  @Override public Account getModeratedAccount()
  {
    return moderatedAccount;
  }

  @Override public void setModeratedAccount(int accountId)
  {
    moderatedAccount = getAccountById(accountId);
  }

  @Override public boolean updateListing(String title, String description, String category, String location, double price, String duration, String rented, String promoted)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    Listing currentListing = client.getListingByID(currentItemID);
    Listing updatedListing = new Listing(title, description, category, location, price, duration, dateFormat.format(date), currentListing.getId(), currentListing.getAccountId(), rented, promoted);
    if (client.updateListing(updatedListing))
    {
      System.out.println("Listing updated");
      return true;
    }
    return false;
  }

  @Override public boolean updateListingRented(String title, String description, String category, String location, double price, String duration, String rented, int itemId, int accountId, String promoted)
  {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    Listing updatedListing = new Listing(title, description, category, location, price, duration, dateFormat.format(date), itemId, accountId, rented, promoted);
    if (client.updateListing(updatedListing))
    {
      System.out.println("Listing updated");
      return true;
    }
    return false;
  }

  @Override public void deleteListing(int id)
  {
    System.out.println("Listing deleted");
    client.deleteListing(id);
  }

  @Override public void addDeletedItemId(int itemId)
  {
    client.addDeletedItemId(itemId);
  }

  @Override public List<Integer> getDeletedItemIds()
  {
    return client.getDeletedItemIds();
  }

  @Override public boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName)
  {
    System.out.println("Feedback was created");
    return client.createFeedbackItems(itemId, starRating, feedback, accountId, accountName);
  }

  @Override public List<FeedbackToItem> getFeedbackItems(int itemId)
  {
    System.out.println("List of items feedback retrieved");
    return client.getFeedbackItems(itemId);
  }

  @Override public FeedbackToItem getFeedbackById(int id)
  {
    return client.getFeedbackById(id);
  }

  @Override public String getAvgStarRating(int itemId)
  {
    System.out.println("Average star retrieved");
    return client.getAvgStarRating(itemId);
  }

  @Override public List<Integer> getRentedTo(int itemId)
  {
    return client.getRentedTo(itemId);
  }

  private void onNewMessage(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }

  @Override public List<Listing> getListings()
  {
    System.out.println("All listings have been retrieved");
    return client.getListings();
  }

  @Override public List<Listing> getSorting(String request, String title, String category, String location)
  {
    System.out.println("Listings have been retrieved");
    return client.getSorting(request, title, category, location);
  }

  @Override public Listing getListingByID(int id)
  {
    return client.getListingByID(id);
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber)
  {
    System.out.println("Account created!");
    return client.createAccount(name, email, password1, address, phoneNumber);
  }

  @Override public String getAllAccounts()

  {
    return null;
  }

  @Override public boolean createListing(String title, String descText, String price, String category, String location, String duration, String date, int accountId, String promoted)
  {
    System.out.println("Listing created!");
    accountId = getCurrentAccountID();
    return client.createListing(title, descText, price, category, location, duration, date, accountId, promoted);
  }

  @Override public boolean checkLogIn(String email, String password)
  {
    return client.checkLogIn(email, password);
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio)
  {
    System.out.println("Account created! (but not really)");
    return client.createAccount(name, email, password1, address, phoneNumber, bio);
  }

  @Override public String broadCastMessage(String msg)
  {
    return client.broadCastMessage(msg, currentAccountID, currentChatterID);
  }

  @Override public List<Message> getMessage()
  {
    return client.getMessage(currentAccountID, currentChatterID);
  }

  @Override public List<ChatItem> getMessagesInvolving()
  {
    List<Message> messagesList = client.getAllMessagesInvolvingAccount(currentAccountID);
    List<ChatItem> chatItemList = new ArrayList<>();
    List<Integer> seenSenderIDs = new ArrayList<>();

    for (Message message : messagesList)
    {
      //checks if sender is not the user's own account, and if it hasn't already been added in the list, it adds them
      if (!(message.getFromAccount() == currentAccountID))
      {
        if (!seenSenderIDs.contains(message.getFromAccount()))
        {
          seenSenderIDs.add(message.getFromAccount());
          String chatterName = client.getAccountById(message.getFromAccount()).getName();
          chatItemList.add(new ChatItem(chatterName, message.getFromAccount()));
        }
      }

      //checks if receiver is not the user's own account, and so on
      else if (!(message.getToAccount() == currentAccountID))
      {
        if (!seenSenderIDs.contains(message.getToAccount()))
        {
          seenSenderIDs.add(message.getToAccount());
          String chatterName = client.getAccountById(message.getToAccount()).getName();
          chatItemList.add(new ChatItem(chatterName, message.getToAccount()));
        }
      }
    }

    return chatItemList;
  }

  @Override public int getCurrentItemID()
  {
    return currentItemID;
  }

  @Override public void setCurrentItemID(int itemID)
  {
    currentItemID = itemID;
    if (client.getListingByID(currentItemID) != null)
    {
      itemName = client.getListingByID(currentItemID).getTitle();
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item deleted");
      alert.showAndWait();
    }
  }

  @Override public Account getAccountById(int id)
  {
    return client.getAccountById(id);
  }

  @Override public String getItemName()
  {
    return this.itemName;
  }

  @Override public int getCurrentChatterID()
  {
    return currentChatterID;
  }

  @Override public void setCurrentChatterID(int currentChatterID)
  {
    if (!(getCurrentAccountID() == currentChatterID))
    {
      this.currentChatterID = currentChatterID;
    }
    else
    {
      this.currentChatterID = 1;
    }
    saveChatterName();
  }

  @Override public int getViewingAccountID()
  {
    return viewingAccountID;
  }

  @Override public void setViewingAccountID(int viewingAccountID)
  {
    this.viewingAccountID = viewingAccountID;
  }

  @Override public String getChatterName()
  {
    return chatterName;
  }

  @Override public void setChatterName(String chatterName)
  {
    this.chatterName = chatterName;
  }

  @Override public void saveChatterName()
  {
    if (client.getAccountById(currentChatterID) == null)
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is deleted");
    }
    else
    {
      setChatterName(client.getAccountById(currentChatterID).getName());
    }

  }

  @Override public boolean checkOwner()
  {
    if (currentAccountID == viewingAccountID)
    {
      return true;
    }
    return false;
  }

  @Override public void setLocalAccountID()
  {
    viewingAccountID = currentAccountID;
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
