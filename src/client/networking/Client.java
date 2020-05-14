package client.networking;

import shared.transferobjects.Message;
import shared.util.Subject;
import stuffs.Account;
import stuffs.Listing;
import java.util.List;

public interface Client extends Subject
{
  List<Listing> getListings();
  List<Listing> getSorting(String request, String title, String category, String location);
  Listing getListingByID(int id);
  boolean createListing(String title, String descText, String price, String category, String location, String duration, String date, int accountId);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber);
  void startClient();
  boolean checkLogIn(String email, String password);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio);
  String broadCastMessage(String msg, int fromAccount, int toAccount);
  List<Message> getMessage(int account1, int account2);
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
}
