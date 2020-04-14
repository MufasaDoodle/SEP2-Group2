package shared.networking;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote
{
  void RegisterClient(ClientCallback client) throws RemoteException;
  void startServer() throws RemoteException, AlreadyBoundException;
  void createListing(String title, String descText, String price, String category, String location, String duration, String date) throws RemoteException;
  void createAccount(String name, String email, String password1, String address, String phoneNumber) throws RemoteException;
  boolean checkLogIn(String email, String password) throws RemoteException;
}