package client.networking;

import shared.util.Subject;

public interface Client extends Subject
{
  boolean createListing(String title, String descText, String price, String category, String location, String duration, String date);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber);
  void startClient();
  boolean checkLogIn(String email, String password);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio);
}
