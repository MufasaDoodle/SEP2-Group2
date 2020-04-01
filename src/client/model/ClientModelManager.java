package client.model;

import client.networking.Client;

public class ClientModelManager implements ClientModel
{
  private Client client;

  public ClientModelManager(Client client)
  {
    this.client = client;
  }
}
