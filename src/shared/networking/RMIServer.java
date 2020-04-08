package shared.networking;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote
{
  void RegisterClient(ClientCallback client) throws RemoteException;
  void startServer() throws RemoteException, AlreadyBoundException;
  void createListing() throws RemoteException;
  void createAccount() throws RemoteException;
}