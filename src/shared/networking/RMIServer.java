package shared.networking;

import shared.transferobjects.Message;
import stuffs.*;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface RMIServer extends Remote
{
  List<Listing> getSorting(String request, String title, String category, String location) throws RemoteException;
  List<Listing> getListings() throws RemoteException;
  Listing getListingByID(int id) throws RemoteException;
  void registerClient(ClientCallback client) throws RemoteException;
  void startServer() throws RemoteException, AlreadyBoundException;

  boolean createListing(String title, String descText, String price,
      String category, String location, String duration, String date,
      int accountId,String promoted) throws RemoteException;
  boolean createAccount(String name, String email, String password1,
      String address, String phoneNumber) throws RemoteException;


  boolean checkLogIn(String email, String password) throws RemoteException;
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio) throws RemoteException;
  String broadCastMessage(String msg, int fromAccount, int toAccount) throws RemoteException;
  List<Message> getMessages(int account1, int account2) throws RemoteException;
  List<Message> getAllMessagesFromAccount(int fromAccount) throws RemoteException;
  List<Message> getAllMessagesToAccount(int toAccount) throws RemoteException;
  List<Message> getAllMessagesInvolvingAccount(int account) throws RemoteException;
  void unRegisterClient(ClientCallback client) throws RemoteException;
  Account getAccountById(int id) throws RemoteException;
  int getAccountId(String email) throws RemoteException;
  String getAccountName(String email) throws RemoteException;
  List<Listing> getListingsByAccount(int accountId) throws RemoteException;

  boolean updateAccount(Account account) throws RemoteException;
  boolean isEmailTaken(String email) throws RemoteException;

  boolean updateListing(Listing listing) throws RemoteException;
  void deleteListing(int id) throws RemoteException;

  void addDeletedItemId(int itemId) throws RemoteException;
  List<Integer> getDeletedItemIds() throws RemoteException;

  //create feedback for items
  boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName) throws RemoteException;
  List<FeedbackToItem> getFeedbackItems(int itemId) throws RemoteException;
  FeedbackToItem getFeedbackById(int id) throws RemoteException;
  String getAvgStarRating(int itemId) throws RemoteException;
  List<Integer> getRentedTo(int itemId) throws RemoteException;

  void createRequest(int itemId, int requestFrom, int requestTo)
      throws RemoteException;
  void deleteRequest(int id) throws RemoteException;
  void deleteDecline(int itemId, int requestFromId) throws RemoteException;
  List<RequestListing> getRequestByAccountId(int requestTo) throws RemoteException;
  Request getRequest(int itemId, int requestFrom) throws RemoteException;

  void createTransaction(int itemId, String date, int rentedToId,
      int rentedFromId) throws RemoteException;
  Transaction getTransactionByItemId(int itemId) throws RemoteException;
  List<TransactionListing> getTransactionByRentedTo(int rentedTo) throws RemoteException;
  List<TransactionListing> getTransactionByRentedFrom(int rentedFrom) throws RemoteException;

  void createReport(int reportFrom, int reportedItemId,
      int reportedAccountId, int reportedItemFeedbackId,
        String date) throws  RemoteException;
  List<Report> getAllReports() throws RemoteException;
  void deleteReport(int id) throws RemoteException;
  void deleteTransaction(int id) throws RemoteException;
  void deleteAccount(int id) throws RemoteException;
  void deleteItemFeedback(int id) throws RemoteException;
  void deleteTransactionByAccount(int id) throws RemoteException;
  void deleteTransactionByItem(int id) throws RemoteException;
  void deleteFeedbackByItemId(int id) throws RemoteException;
  void deleteRequestByAccount(int id) throws RemoteException;
  void deleteItemByAccount(int id) throws RemoteException;
  void deleteReportByAccount(int id) throws RemoteException;
  void deleteReportByItem(int id) throws RemoteException;
  void deleteReportByItemFeedback(int id) throws RemoteException;
  void deleteMessageByAccount(int id)throws RemoteException;

  Report getReportByItemId(int id) throws RemoteException;
  Report getReportByFeedbackId(int id) throws RemoteException;
  Report getReportByAccountId(int id) throws RemoteException;
}