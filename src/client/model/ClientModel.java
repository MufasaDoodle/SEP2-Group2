package client.model;

import shared.transferobjects.Message;
import shared.util.Subject;
import stuffs.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientModel extends Subject
{
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