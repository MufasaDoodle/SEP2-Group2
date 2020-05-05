package client.model;

import client.networking.Client;
import shared.transferobjects.Message;
import stuffs.Account;
import stuffs.Listing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ClientModelManager implements ClientModel
{
  private Client client;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private String username;
  private String itemName;
  private int currentItemID;

  private int currentAccountID;

  public ClientModelManager(Client client)
  {
    this.client = client;
    client.startClient();
    client.addListener("NewMessage", this::onNewMessage);
    //client.addListener("NewListing", this::onNewListing);
  }

  @Override public void setCurrentAccountID(String email)
  {
    currentAccountID = client.getAccountId(email);
  }

  @Override public int getCurrentAccountID()
  {
    return currentAccountID;
  }

  @Override public List<Listing> getListingsByAccount(int accountId)
  {
    System.out.println("Owners's listings have been retrieved");
    return client.getListingsByAccount(accountId);
  }


  private void onNewMessage(PropertyChangeEvent propertyChangeEvent)
  {
    support.firePropertyChange(propertyChangeEvent);
  }
  private void onNewListing(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }
  @Override
  public List<Listing> getListings() {
    System.out.println("All listings have been retrieved");
    return client.getListings();
  }

  @Override
  public List<Listing> getSorting(String request, String title, String category, String location) {
    System.out.println("Listings have been retrieved");
    return client.getSorting(request, title, category, location);
  }

  @Override public Listing getListingByID(int id)
  {
    return client.getListingByID(id);
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

  @Override public boolean createListing(String title, String descText, String price, String category, String location, String duration, String date, int accountId)
  {
    System.out.println("Listing created!");
    accountId = getCurrentAccountID();
    return client.createListing(title, descText, price, category, location, duration, date, accountId );
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

  @Override public String broadCastMessage(String msg)
  {
    return client.broadCastMessage(msg);
  }

  @Override public List<Message> getMessage()
  {
    return client.getMessage();
  }

  @Override public int getCurrentItemID()
  {
    return currentItemID;
  }

  @Override public void setCurrentItemID(int itemID)
  {
    currentItemID = itemID;
  }

  @Override public Account getAccountById(int id)
  {
    return client.getAccountById(id);
  }

  @Override public void setUsername(String username)
  {
    this.username = username;

  }

  @Override public String getUsername()
  {
    return this.username;
  }

  @Override public String getItemName()
  {
    return this.itemName;
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
