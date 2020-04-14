package client.model;

import client.networking.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

public class ClientModelManager implements ClientModel
{
  private Client client;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public ClientModelManager(Client client)
  {
    this.client = client;
    client.startClient();
    client.addListener("newMessage", this::onNewMessage);
  }

  private void onNewMessage(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber)
  {
    System.out.println("Account created! (but not really)");
    return client.createAccount(name, email, password1, address, phoneNumber);
  }

  @Override public String getAllAccounts()

  {
    return null;
  }

  @Override public boolean createListing(String title, String descText, String price, String category, String location, String duration, String date)
  {
    System.out.println("Listing created! (but not really)");
    return client.createListing(title, descText, price, category, location, duration, date);
  }

  @Override public boolean checkLogIn(String email, String password)
  {
    return client.checkLogIn(email, password);
  }

  @Override public boolean createAccount(String name, String email, String password1, String address, String phoneNumber, String bio)
  {
    System.out.println("Account created! (but not really)");
    return client.createAccount(name, email, password1, address, phoneNumber, bio);
  }
}
