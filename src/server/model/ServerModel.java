package server.model;

import shared.transferobjects.Message;
import shared.util.Subject;
import stuffs.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface ServerModel extends Subject
{
  List<Listing> getSorting(String request, String title, String category,
      String location) throws RemoteException;
  List<Listing> getListings() throws RemoteException;
  Listing getListingByID(int id) throws RemoteException;
  boolean createListing(String title, String descText, String price,
      String category, String location, String duration, String date,
      int accountId) throws RemoteException;
  boolean createAccount(String name, String email, String password1,
      String address, String phoneNumber) throws RemoteException;
  boolean checkLogIn(String email, String password) throws RemoteException;
  
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio) throws RemoteException;

  List<Message> getMessage(int account1, int account2) throws RemoteException;
  String broadCastMessage(String msg, int fromAccount, int toAccount) throws RemoteException;
  List<Message> getAllMessagesFromAccount(int fromAccount) throws RemoteException;
  List<Message> getAllMessagesToAccount(int toAccount) throws RemoteException;
  List<Message> getAllMessagesInvolvingAccount(int account) throws RemoteException;

  Account getAccountById(int id) throws RemoteException;

  int getAccountId(String email) throws RemoteException;
  String getAccountName(String email) throws RemoteException;
  List<Listing> getListingsByAccountId(int accountId) throws RemoteException;
  boolean updateAccount(Account account) throws RemoteException;
  boolean isEmailTaken(String email) throws RemoteException;

  boolean updateListing(Listing listing) throws RemoteException;
  void deleteListing(int id) throws RemoteException;

  void addDeletedItemId(int itemId) throws RemoteException;
  List<Integer> getDeletedItemIds() throws RemoteException;
  void addRentedItemId(int itemId) throws RemoteException;
  List<Integer> getRentedItemIds() throws RemoteException;

  void createRequest(int itemId, int requestFrom, int requestTo)
      throws RemoteException;
  void deleteRequest(int id) throws RemoteException;
  void deleteDecline(int itemId, int requestFromId) throws RemoteException;
  List<RequestListing> getRequestByAccountId(int requestTo)
      throws RemoteException;
  Request getRequest(int itemId, int requestFrom) throws RemoteException;

//Feedback for items
  boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName) throws RemoteException;
  List<FeedbackToItem> getFeedbackItems(int itemId) throws RemoteException;
  String getAvgStarRating(int itemId) throws RemoteException;
  List<Integer> getRentedTo(int itemId) throws RemoteException;


  void createTransaction(int itemId, String date, int rentedToId,
      int rentedFromId) throws RemoteException;
  Transaction getTransactionByItemId(int itemId) throws RemoteException;
  List<TransactionListing> getTransactionByRentedTo(int rentedTo) throws RemoteException;
  List<TransactionListing> getTransactionByRentedFrom(int rentedFrom) throws RemoteException;


}
