package client.networking;

import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobjects.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient implements Client, ClientCallback
{
  private RMIServer server;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public RMIClient()
  {

  }

  public void startClient()
  {
    try
    {
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer) registry.lookup("Server");
      System.out.println("Connected to server");
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void update(Message message)
  {
    support.firePropertyChange("newMessage", null, message);
  }

  @Override public void createListing(String title, String descText, String price, String category, String location, String duration, String date)
  {
    try
    {
      server.createListing();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void createAccount(String name, String email, String password1, String address, String phoneNumber)
  {
    try
    {
      server.createAccount();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}
