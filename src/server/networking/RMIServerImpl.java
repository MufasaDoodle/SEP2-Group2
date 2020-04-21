package server.networking;

import server.model.ServerModel;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobjects.Message;
import stuffs.Listing;

import javax.lang.model.util.SimpleElementVisitor7;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMIServerImpl implements RMIServer
{
  private ServerModel serverModel;
  private Map<ClientCallback, PropertyChangeListener> listeners = new HashMap<>();


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

  @Override public boolean createListing(String title, String descText, String price, String category, String location, String duration, String date)
  {
    try
    {
      return serverModel.createListing(title, descText, price, category, location, duration, date);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber) throws RemoteException
  {
    try
    {
      return serverModel.createAccount(name, email, password1, address, phoneNumber);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean checkLogIn(String email, String password)
  {
    try
    {
      if (serverModel.checkLogIn(email, password))
      {
        return true;
      }
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio)
  {
    try
    {
      return serverModel.createAccount(name, email, password1, address, phoneNumber, bio);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public List<Listing> getSorting(String request, String title, String category, String location) throws RemoteException {
    return serverModel.getSorting(request, title, category, location);
  }

  @Override
  public List<Listing> getListings() throws RemoteException {
    try
    {
      return serverModel.getListings();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve listings");
    }
  }


  @Override public void registerClient(ClientCallback client)
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
        serverModel.removeListener("NewMessage", finalListener);
      }
    };
    serverModel.addListener("NewMessage", listener);
  }

  @Override public String broadCastMessage(String msg)
  {
    try
    {
      return serverModel.broadCastMessage(msg);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return "";
  }

  @Override public List<Message> getMessages()
  {
    try
    {
      return serverModel.getMessage();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return new ArrayList<Message>();
  }

  @Override public void unRegisterClient(ClientCallback client)
  {
    PropertyChangeListener listener = listeners.get(client);
    if (listener != null)
    {
      serverModel.removeListener("NewMessage", listener);
    }
    int stop = 0;
  }

}
