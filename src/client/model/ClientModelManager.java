package client.model;

import client.networking.Client;

import java.io.IOException;

public class ClientModelManager implements ClientModel
{
  private Client client;

  public ClientModelManager(Client client)
  {
    this.client = client;
  }


  @Override public String createAccount(String name, String email,
      String password1, String address, String phoneNumber)
  {
    if (!name.equals("") && !email.equals("") && !password1.equals("") && !address.equals("") && !phoneNumber.equals(""))
    {
      try
      {
        //contacts server to create an account and gets a confirmation message back (hence the return string)
        //We need the client side to create an account (networking)
        return client.createAccount(name,  email,
             password1,  address,  phoneNumber);
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
      }
    }
    return "";
  }

  @Override public String getAllAccounts()

  {
    return null;
  }
}
