package server.model;

import shared.util.Subject;

import java.rmi.RemoteException;

public interface ServerModel extends Subject
{
  void createListing() throws RemoteException;
  void createAccount() throws RemoteException;
}
