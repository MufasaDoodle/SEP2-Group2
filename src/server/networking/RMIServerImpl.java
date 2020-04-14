package server.networking;

import server.model.ServerModel;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobjects.Message;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerImpl implements RMIServer
{
  private ServerModel serverModel;

  public RMIServerImpl(ServerModel serverModel) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.serverModel = serverModel;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Server", this);
    System.out.println("Server started");
  }

  @Override public void createListing()
  {
    try
    {
      serverModel.createListing();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void createAccount() throws RemoteException
  {
    try
    {
      serverModel.createAccount();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void RegisterClient(ClientCallback client)
  {
    PropertyChangeListener listener = null;
    PropertyChangeListener finalListener = listener;

    listener = evt -> {
      try
      {
        client.update((Message) evt.getNewValue());
      }
      catch (RemoteException e)
      {
        serverModel.removeListener("newMessage", finalListener);
      }
    };
    serverModel.addListener("newMessage", listener);
  }
}