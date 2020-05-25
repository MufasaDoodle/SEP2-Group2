package client.model;

import shared.transferobjects.Message;
import shared.util.Subject;
import stuffs.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientModel extends Subject
{
  //accounts
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber);
  String getAllAccounts() throws IOException, ClassNotFoundException;
  boolean checkLogIn(String email, String password);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio);
  void setCurrentAccountName(String email);
  List<Listing> getListingsByAccount(int accountId);
  boolean updateAccount(String email, String pass, String address, String number, String bio);
  boolean isEmailTaken(String email);

  //feedback
  boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName);
  List<FeedbackToItem> getFeedbackItems(int itemId);
  String getAvgStarRating(int itemId);
  List<Integer> getRentedTo(int itemId);
  void deleteItemFeedback(int id);
  void deleteFeedbackByItemId(int id);

  //chat
  String broadCastMessage(String msg);
  List<Message> getMessage();
  List<ChatItem> getMessagesInvolving();
  String getChatterName();
  void setChatterName(String chatterName);

  boolean checkOwner();
  void setLocalAccountID();

  //moderator
  Account getModeratedAccount();
  void setModeratedAccount(int accountId);
  void setModeratorOpen(boolean whereFrom);
  boolean getModeratorOpen();
  void createReport(int reportFrom, int reportedItemId, int reportedAccountId, int reportedItemFeedbackId, String date);
  List<Report> getAllReports();
  void deleteReport(int id);
  void deleteAccount(int id);
  void deleteMessageByAccount(int id);
  Report getReportByItemId(int id);
  Report getReportByFeedbackId(int id);
  Report getReportByAccountId(int id);
  void deleteReportByAccount(int id);
  void deleteReportByItem(int id);
  void deleteReportByItemFeedback(int id);

  //transactions
  void createRequest(int itemId, int requestFrom, int requestTo);
  void deleteRequest(int id);
  void deleteDecline(int itemId, int requestFromId);
  List<RequestListing> getRequestByAccountId(int requestTo);
  Request getRequest(int itemId, int requestFrom);
  void createTransaction(int itemId, String date, int rentedToId, int rentedFromId);
  Transaction getTransactionByItemId(int itemId);
  List<TransactionListing> getTransactionByRentedTo(int rentedTo);
  List<TransactionListing> getTransactionByRentedFrom(int rentedFrom);
  void deleteTransaction(int id);
  void deleteTransactionByAccount(int id);
  void deleteTransactionByItem(int id);
  void deleteRequestByAccount(int id);
}