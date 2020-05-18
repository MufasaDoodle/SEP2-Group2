package client.networking;

import shared.transferobjects.Message;
import shared.util.Subject;
import stuffs.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface Client extends Subject
{
  List<Listing> getListings();
  List<Listing> getSorting(String request, String title, String category,
      String location);
  Listing getListingByID(int id);
  boolean createListing(String title, String descText, String price,
      String category, String location, String duration, String date,
      int accountId,String promoted);
  boolean createAccount(String name, String email, String password1,
      String address, String phoneNumber);
  void startClient();
  boolean checkLogIn(String email, String password);
  boolean createAccount(String name, String email, String password1,
      String address, String phoneNumber, String bio);
  String broadCastMessage(String msg);
  List<Message> getMessage();
  void unRegisterClient();
  Account getAccountById(int id);
  int getAccountId(String email);
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

  void createTransaction(int itemId, String date, int rentedToId,
      int rentedFromId);
  Transaction getTransactionByItemId(int itemId) ;
  List<TransactionListing> getTransactionByRentedTo(int rentedTo) ;
  List<TransactionListing> getTransactionByRentedFrom(int rentedFrom);
}
