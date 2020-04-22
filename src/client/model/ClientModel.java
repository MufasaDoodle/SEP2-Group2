package client.model;

import shared.transferobjects.Message;
import shared.util.Subject;
import stuffs.Listing;

import java.io.IOException;
import java.util.List;

public interface ClientModel extends Subject
{
  List<Listing> getListings();
  List<Listing> getSorting(String request, String title, String category, String location);
  boolean createAccount(String name,String email, String password1,String address,String phoneNumber);
  String getAllAccounts() throws IOException, ClassNotFoundException;
  boolean createListing(String title, String descText, String price, String category, String location, String duration, String date);
  boolean checkLogIn(String email, String password);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio);
  String broadCastMessage(String msg);
  List<Message> getMessage();
  //Todo
  void setUsername(String username);
  String getUsername();
  String getItemName();

}
