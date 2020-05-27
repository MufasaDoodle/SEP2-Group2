package server.networking;

import server.model.ServerModel;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobjects.*;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMIServerImpl implements RMIServer
{
  private ServerModel serverModel;
  private Map<ClientCallback, PropertyChangeListener> listeners = new HashMap<>();

  public RMIServerImpl(ServerModel serverModel) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.serverModel = serverModel;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Server", this);
    System.out.println("Server started");
  }

  @Override public boolean createListing(String title, String descText,
      String price, String category, String location, String duration,
      String date, int accountId,String promoted)
  {
    try
    {
      return serverModel
          .createListing(title, descText, price, category, location, duration,
              date, accountId, promoted);
    }
    catch (RemoteException e)
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
      return serverModel
          .createAccount(name, email, password1, address, phoneNumber);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean checkLogIn(String email, String password)
  {
    try
    {
      if (serverModel.checkLogIn(email, password))
      {
        return true;
      }
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean createAccount(String name, String email,
      String password1, String address, String phoneNumber, String bio)
  {
    try
    {
      return serverModel
          .createAccount(name, email, password1, address, phoneNumber, bio);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public List<Listing> getSorting(String request, String title,
      String category, String location)
  {
    try
    {
      return serverModel.getSorting(request, title, category, location);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve soring");
    }
  }

  @Override public List<Listing> getListings()
  {
    try
    {
      return serverModel.getListings();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve listings");
    }
  }

  @Override public Listing getListingByID(int id)
  {
    try
    {
      return serverModel.getListingByID(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve listing");
    }
  }

  @Override public void registerClient(ClientCallback client)
  {
    PropertyChangeListener listener = null;
    PropertyChangeListener finalListener = listener;

    listener = evt -> {
      try
      {
        client.update((Message) evt.getNewValue());
      }
      catch (RemoteException e)
      {
        serverModel.removeListener("NewMessage", finalListener);
      }
    };
    serverModel.addListener("NewMessage", listener);
  }

  @Override public String broadCastMessage(String msg, int fromAccount, int toAccount)
  {
    try
    {
      return serverModel.broadCastMessage(msg, fromAccount, toAccount);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return "";
  }

  @Override public List<Message> getMessages(int account1, int account2)
  {
    try
    {
      return serverModel.getMessage(account1, account2);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return new ArrayList<Message>();
  }

  @Override public List<Message> getAllMessagesFromAccount(int fromAccount)
  {
    try
    {
      return serverModel.getAllMessagesFromAccount(fromAccount);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve messages");
    }
  }

  @Override public List<Message> getAllMessagesToAccount(int toAccount)
  {
    try
    {
      return serverModel.getAllMessagesToAccount(toAccount);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve messages");
    }
  }

  @Override public List<Message> getAllMessagesInvolvingAccount(int account)
  {
    try
    {
      return serverModel.getAllMessagesInvolvingAccount(account);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve messages");
    }
  }

  @Override public void unRegisterClient(ClientCallback client)
  {
    PropertyChangeListener listener = listeners.get(client);
    if (listener != null)
    {
      serverModel.removeListener("NewMessage", listener);
    }
    int stop = 0;
  }

  @Override public Account getAccountById(int id)
  {
    try
    {
      return serverModel.getAccountById(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve account");
    }
  }

  @Override public int getAccountId(String email)
  {
    try
    {
      return serverModel.getAccountId(email);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve account id");
    }
  }

  @Override
  public String getAccountName(String email) {
    try
    {
      return serverModel.getAccountName(email);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve account name");
    }
  }

  @Override public List<Listing> getListingsByAccount(int accountId)

  {
    try
    {
      return serverModel.getListingsByAccountId(accountId);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve items by account id");
    }
  }

  @Override public boolean updateAccount(Account account)
  {
    try
    {
      return serverModel.updateAccount(account);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean isEmailTaken(String email)
  {
    try
    {
      return serverModel.isEmailTaken(email);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return true;
  }

  @Override public boolean updateListing(Listing listing)
  {
    try
    {
      return serverModel.updateListing(listing);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public void deleteListing(int id)
  {
    try
    {
      serverModel.deleteListing(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addDeletedItemId(int itemId)
  {
    try
    {
      serverModel.addDeletedItemId(itemId);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<Integer> getDeletedItemIds()
  {
    try
    {
      return serverModel.getDeletedItemIds();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName) {
    try
    {
      return serverModel.createFeedbackItems(itemId, starRating, feedback, accountId, accountName);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public List<FeedbackToItem> getFeedbackItems(int itemId){
    try
    {
      return serverModel.getFeedbackItems(itemId);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not retrieve feedback for listings");
    }
  }

  @Override public FeedbackToItem getFeedbackById(int id) throws RemoteException
  {
    try
    {
      return serverModel.getFeedbackById(id);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not retrieve feedback");
    }
  }

  @Override
  public String getAvgStarRating(int itemId) {
    try
    {
      return serverModel.getAvgStarRating(itemId);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not retrieve average star rating");
    }
  }

  @Override
  public List<Integer> getRentedTo(int itemId) {
    try
    {
      return serverModel.getRentedTo(itemId);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not leave feedback because of rent");
    }
  }

  @Override public void createRequest(int itemId, int requestFrom,
      int requestTo)
  {
    try
    {
      serverModel.createRequest(itemId, requestFrom, requestTo);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteRequest(int id)
  {
    try
    {
      serverModel.deleteRequest(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteDecline(int itemId, int requestFromId)
      throws RemoteException
  {
    try
    {
      serverModel.deleteDecline(itemId, requestFromId);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<RequestListing> getRequestByAccountId(int requestTo)
  {
    try
    {
      return serverModel.getRequestByAccountId(requestTo);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Request getRequest(int itemId, int requestFrom)
  {
    try
    {
      return serverModel.getRequest(itemId, requestFrom);
    }
    catch (RemoteException e)
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
      serverModel.createTransaction(itemId, date, rentedToId, rentedFromId);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public Transaction getTransactionByItemId(int itemId)

  {
    try
    {
      return serverModel.getTransactionByItemId(itemId);
    }
    catch (RemoteException e)
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
      return serverModel.getTransactionByRentedTo(rentedTo);
    }
    catch (RemoteException e)
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
      return serverModel.getTransactionByRentedFrom(rentedFrom);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void createReport(int reportFrom, int reportedItemId,
      int reportedAccountId, int reportedItemFeedbackId,
      String date)
  {
    try
    {
      serverModel.createReport(reportFrom,reportedItemId,reportedAccountId,reportedItemFeedbackId,date);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public List<Report> getAllReports()
  {
    try
    {
      return serverModel.getAllReports();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void deleteReport(int id)
  {
    try
    {
      serverModel.deleteReport(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteTransaction(int id)
  {
    try
    {
      serverModel.deleteTransaction(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteAccount(int id)
  {
    try
    {
      serverModel.deleteAccount(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteItemFeedback(int id)
  {
    try
    {
      serverModel.deleteItemFeedback(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteTransactionByAccount(int id)
  {
    try
    {
      serverModel.deleteTransactionByAccount(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteTransactionByItem(int id)
  {
    try
    {
      serverModel.deleteTransactionByItem(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteFeedbackByItemId(int id)
  {
    try
    {
      serverModel.deleteFeedbackByItemId(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteRequestByAccount(int id)
  {
    try
    {
      serverModel.deleteRequestByAccount(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteItemByAccount(int id)
  {
    try
    {
      serverModel.deleteItemByAccount(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteReportByAccount(int id)
  {
    try
    {
      serverModel.deleteReportByAccount(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteReportByItem(int id)
  {
    try
    {
      serverModel.deleteReportByItem(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteReportByItemFeedback(int id)

  {
    try
    {
      serverModel.deleteReportByItemFeedback(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void deleteMessageByAccount(int id)
  {
    try
    {
      serverModel.deleteMessageByAccount(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public Report getReportByItemId(int id) throws RemoteException
  {
    try
    {
      return serverModel.getReportByItemId(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Report getReportByFeedbackId(int id) throws RemoteException
  {
    try
    {
      return serverModel.getReportByFeedbackId(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Report getReportByAccountId(int id) throws RemoteException
  {
    try
    {
      return serverModel.getReportByAccountId(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}