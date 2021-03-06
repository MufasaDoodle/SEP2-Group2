package client.networking;

import shared.transferobjects.*;
import shared.util.Subject;

import java.util.List;

public interface Client extends Subject
{
  List<Listing> getListings();
  List<Listing> getSorting(String request, String title, String category, String location);
  Listing getListingByID(int id);
  boolean createListing(String title, String descText, String price, String category, String location, String duration, String date, int accountId, String promoted);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber);
  void startClient();
  boolean checkLogIn(String email, String password);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio);
  String broadCastMessage(String msg, int fromAccount, int toAccount);
  List<Message> getMessage(int account1, int account2);
  List<Message> getAllMessagesInvolvingAccount(int account);

  void unRegisterClient();
  Account getAccountById(int id);
  int getAccountId(String email);
  String getAccountName(String email);
  List<Listing> getListingsByAccount(int accountId);

  boolean updateAccount(Account account);
  boolean isEmailTaken(String email);

  boolean updateListing(Listing listing);
  void deleteListing(int id);

  void addDeletedItemId(int itemId);
  List<Integer> getDeletedItemIds();

  void createRequest(int itemId, int requestFrom, int requestTo);
  void deleteRequest(int id);
  void deleteDecline(int itemId, int requestFromId);
  List<RequestListing> getRequestByAccountId(int requestTo);
  Request getRequest(int itemId, int requestFrom);

  //create feedback for items
  boolean createFeedbackItems(int itemId, String starRating, String feedback, int accountId, String accountName);
  List<FeedbackToItem> getFeedbackItems(int itemId);
  FeedbackToItem getFeedbackById(int id);
  String getAvgStarRating(int itemId);
  List<Integer> getRentedTo(int itemId);

  void createTransaction(int itemId, String date, int rentedToId, int rentedFromId);
  Transaction getTransactionByItemId(int itemId);
  List<TransactionListing> getTransactionByRentedTo(int rentedTo);
  List<TransactionListing> getTransactionByRentedFrom(int rentedFrom);

  void createReport(int reportFrom, int reportedItemId, int reportedAccountId, int reportedItemFeedbackId, String date);
  List<Report> getAllReports();
  void deleteReport(int id);
  void deleteTransaction(int id);
  void deleteAccount(int id);
  void deleteItemFeedback(int id);
  void deleteTransactionByAccount(int id);
  void deleteTransactionByItem(int id);
  void deleteFeedbackByItemId(int id);
  void deleteRequestByAccount(int id);
  void deleteItemByAccount(int id);
  void deleteReportByAccount(int id);
  void deleteReportByItem(int id);
  void deleteReportByItemFeedback(int id);
  void deleteMessageByAccount(int id);

  Report getReportByItemId(int id);
  Report getReportByFeedbackId(int id);
  Report getReportByAccountId(int id);
}