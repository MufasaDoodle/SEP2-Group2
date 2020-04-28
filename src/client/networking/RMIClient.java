package client.networking;

import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobjects.Message;
import stuffs.Listing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

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
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer) registry.lookup("Server");
      server.registerClient(this);
      System.out.println("Connected to server");
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public boolean checkLogIn(String email, String password)
  {
    boolean temp = false;
    try
    {
      temp = server.checkLogIn(email, password);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      System.out.println("Could not contact server");
    }
    return temp;
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio)
  {
    try
    {
      return server.createAccount(name, email, password1, address, phoneNumber, bio);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public List<Listing> getListings() {

    try {
      return server.getListings();
    } catch (RemoteException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve listings");
    }
  }

  @Override
  public List<Listing> getSorting(String request, String title, String category, String location) {
    try
    {
      return server.getSorting(request, title, category, location);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("There was some problem");
    }
  }

  @Override public Listing getListingByID(int id)
  {
    try
    {
      return server.getListingByID(id);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Server could not be contacted");
    }
  }

  @Override public boolean createListing(String title, String descText, String price, String category, String location, String duration, String date)
  {
    try
    {
      return server.createListing(title, descText, price, category, location, duration, date);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber)
  {
    try
    {
      return server.createAccount(name, email, password1, address, phoneNumber);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public void unRegisterClient()
  {
    try
    {
      server.unRegisterClient(this);
    }
    catch (RemoteException e)
    {
      System.out.println("unRegisterClient error..");
    }
  }

  @Override public String broadCastMessage(String msg)
  {
    try
    {
      return server.broadCastMessage(msg);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server (broadCastMessage)...");
    }
  }

  @Override public List<Message> getMessage()
  {
    try
    {
      return server.getMessages();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server (getMessage)...");
    }
  }

  @Override public void update(Message msg)
  {
    support.firePropertyChange("NewMessage", null, msg);
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
