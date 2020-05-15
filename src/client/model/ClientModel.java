package client.model;

import shared.transferobjects.Message;
import shared.util.Subject;
import stuffs.Account;
import stuffs.Listing;

import java.io.IOException;
import java.util.List;

public interface ClientModel extends Subject
{
  List<Listing> getListings();
  List<Listing> getSorting(String request, String title, String category, String location);
  Listing getListingByID(int id);
  boolean createAccount(String name,String email, String password1,String address,String phoneNumber);
  String getAllAccounts() throws IOException, ClassNotFoundException;
  boolean createListing(String title, String descText, String price, String category, String location, String duration, String date, int accountId);
  boolean checkLogIn(String email, String password);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio);
  String broadCastMessage(String msg);
  List<Message> getMessage();
  int getCurrentItemID();
  void setCurrentItemID(int itemID);
  //Todo
  String getItemName();
  Account getAccountById(int id);


  void setCurrentAccountID(String email);
  int getCurrentAccountID();
  List<Listing> getListingsByAccount(int accountId);

  boolean updateAccount(String email, String pass, String address, String number, String bio);
  boolean isEmailTaken(String email);

  boolean updateListing(String title, String description, String category, String location, double price, String duration);
  void deleteListing(int id);

  void addDeletedItemId(int itemId);
  List<Integer> getDeletedItemIds();

  void setFromListingViewOpen(boolean whereFrom);
  boolean getFromListingViewOpen();

  int getCurrentChatterID();
  void setCurrentChatterID(int currentChatterID);
  String getChatterName();
  void setChatterName(String chatterName);
  void saveChatterName();
}
