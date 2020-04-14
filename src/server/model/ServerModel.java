package server.model;

import shared.util.Subject;

import java.rmi.RemoteException;

public interface ServerModel extends Subject
{
  void createListing(String title, String descText, String price, String category, String location, String duration, String date) throws RemoteException;
  void createAccount(String name, String email, String password1, String address, String phoneNumber) throws RemoteException;
  boolean checkLogIn(String email, String password) throws RemoteException;
}
