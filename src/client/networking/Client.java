package client.networking;

import shared.util.Subject;

public interface Client extends Subject
{
  void createListing(String title, String descText, String price, String category, String location, String duration, String date);
  void createAccount(String name, String email, String password1, String address, String phoneNumber);
  void startClient();
}
