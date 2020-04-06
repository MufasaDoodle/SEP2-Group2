package client.model;

import client.networking.Client;

public class ClientModelManager implements ClientModel
{
  private Client client;

  public ClientModelManager(Client client)
  {
    this.client = client;
  }

  @Override public void createListing(String title, String descText, String price, String category, String location, String duration, String date)
  {
    System.out.println("Listing created! (but not really)");
    client.createListing(title, descText, price, category, location, duration, date);
  }
}
