package client.model;

import shared.transferobjects.Listing;

import java.io.IOException;
import java.util.List;

public interface AccountModel
{
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber);
  String getAllAccounts() throws IOException, ClassNotFoundException;
  boolean checkLogIn(String email, String password);
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio);
  void setCurrentAccountName(String email);
  List<Listing> getListingsByAccount(int accountId);
  boolean updateAccount(String email, String pass, String address, String number, String bio);
  boolean isEmailTaken(String email);
}
