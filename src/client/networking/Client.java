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
  String broadCastMessage(String msg);
  List<Message> getMessage();
  void unRegisterClient();
  Account getAccountById(int id);
  int getAccountId(String email);
  List<Listing> getListingsByAccount(int accountId);

}
