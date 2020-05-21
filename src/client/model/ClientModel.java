package client.model;

import shared.transferobjects.Message;
import shared.util.Subject;
import stuffs.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientModel extends Subject
{
  //we need these for all
  Account getAccountById(int id);


  //listings
  List<Listing> getListings();
  List<Listing> getSorting(String request, String title, String category, String location);
  Listing getListingByID(int id);
  boolean createListing(String title, String descText, String price, String category, String location, String duration, String date, int accountId, String promoted);
  int getCurrentItemID();
  void setCurrentItemID(int itemID);
  String getItemName();
  boolean updateListing(String title, String description, String category, String location, double price, String duration, String rented, String promoted);
  boolean updateListingRented(String title, String description, String category, String location, double price, String duration, String rented, int itemId, int accountId, String promoted);
  void deleteListing(int id);
  void addDeletedItemId(int itemId);
  List<Integer> getDeletedItemIds();
  void setFromListingViewOpen(boolean whereFrom);
  boolean getFromListingViewOpen();
  void deleteItemByAccount(int id);

  //accounts
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber);
  String getAllAccounts() throws IOException, ClassNotFoundException;
  boolean checkLogIn(String email, String password);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio);
  void setCurrentAccountID(String email);
  int getCurrentAccountID();
  void setCurrentAccountName(String email);
  String getCurrentAccountName();
  List<Listing> getListingsByAccount(int accountId);
  boolean updateAccount(String email, String pass, String address, String number, String bio);
  boolean isEmailTaken(String email);
  boolean accountCheck();

  //feedback
  boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName);
  List<FeedbackToItem> getFeedbackItems(int itemId);
  FeedbackToItem getFeedbackById(int id);
  void setFeedbackId(int feedbackId);
  int getFeedbackId();
  String getAvgStarRating(int itemId);
  List<Integer> getRentedTo(int itemId);
  void deleteItemFeedback(int id);
  void deleteFeedbackByItemId(int id);

  //chat
  String broadCastMessage(String msg);
  List<Message> getMessage();
  List<ChatItem> getMessagesInvolving();
  int getCurrentChatterID();
  void setCurrentChatterID(int currentChatterID);
  int getViewingAccountID();
  void setViewingAccountID(int viewingAccountID);
  String getChatterName();
  void setChatterName(String chatterName);
  void saveChatterName();
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
