package client.model;

import client.networking.Client;
import javafx.scene.control.Alert;
import shared.transferobjects.Message;
import stuffs.*;
import client.model.ClientModel;

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
    //client.startClient();
    client.addListener("NewMessage", this::onNewMessage);
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



  @Override public void deleteRequestByAccount(int id)
  {
    System.out.println("Request deleted");
    client.deleteRequestByAccount(id);
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

  @Override public Account getModeratedAccount()
  {
    return moderatedAccount;
  }

  @Override public void setModeratedAccount(int accountId)
  {
    //moderatedAccount = getAccountById(accountId);
  }

  private void onNewMessage(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
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