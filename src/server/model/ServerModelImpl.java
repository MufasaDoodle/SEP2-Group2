package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class ServerModelImpl implements ServerModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }

  @Override public void createListing()
  {
    System.out.println("Listing created");
  }

  @Override public void createAccount()
  {
    System.out.println("Account created");
  }
}
