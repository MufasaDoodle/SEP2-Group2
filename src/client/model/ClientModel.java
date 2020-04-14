package client.model;

import java.io.IOException;

public interface ClientModel
{
  boolean createAccount(String name,String email, String password1,String address,String phoneNumber);
  String getAllAccounts() throws IOException, ClassNotFoundException;
  boolean createListing(String title, String descText, String price, String category, String location, String duration, String date);
  boolean checkLogIn(String email, String password);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio);
}
