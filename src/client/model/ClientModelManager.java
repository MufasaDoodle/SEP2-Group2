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

  @Override public void createAccount(String name, String email, String password1, String address, String phoneNumber)
  {
    if (!name.equals("") && !email.equals("") && !password1.equals("") && !address.equals("") && !phoneNumber.equals(""))
    {
      //contacts server to create an account and gets a confirmation message back (hence the return string)
      System.out.println("Account created! (but not really)");
      client.createAccount(name, email, password1, address, phoneNumber);
    }
  }

  @Override public String getAllAccounts()

  {
    return null;
  }

  @Override public void createListing(String title, String descText, String price, String category, String location, String duration, String date)
  {
    System.out.println("Listing created! (but not really)");
    client.createListing(title, descText, price, category, location, duration, date);
  }
}
