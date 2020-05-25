package client.model;

import stuffs.Request;
import stuffs.RequestListing;
import stuffs.Transaction;
import stuffs.TransactionListing;

import java.util.List;

public interface TransactionModel
{
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
