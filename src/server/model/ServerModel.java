package server.model;

import shared.networking.Sorting;
import shared.transferobjects.Message;
import shared.util.Subject;
import stuffs.Account;
import stuffs.Listing;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ServerModel extends Subject
{
  List<Listing> getSorting(String request, String title, String category, String location) throws RemoteException;
  List<Listing> getListings() throws RemoteException;
  Listing getListingByID(int id) throws RemoteException;
  boolean createListing(String title, String descText, String price, String category, String location, String duration, String date) throws RemoteException;
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber) throws RemoteException;
  boolean checkLogIn(String email, String password) throws RemoteException;
  boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio) throws RemoteException;
  List<Message> getMessage() throws RemoteException;
  String broadCastMessage(String msg) throws RemoteException;
  Account getAccountById(int id) throws RemoteException;
}
