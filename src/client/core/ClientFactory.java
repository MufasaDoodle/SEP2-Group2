package client.core;

import client.networking.Client;

public class ClientFactory
{
  private Client client;

  public Client getClient()
  {
    if (client == null)
    {
      //TODO
      //client = new SocketClient();
    }
    return client;
  }
}
