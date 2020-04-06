package client.model;

import java.io.IOException;

public interface ClientModel
{
  String createAccount(String name,String email, String password1,String address,String phoneNumber);
  String getAllAccounts() throws IOException, ClassNotFoundException;
  void createListing(String title, String descText, String price, String category, String location, String duration, String date);
}
