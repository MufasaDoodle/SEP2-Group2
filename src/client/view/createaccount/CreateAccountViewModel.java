package client.view.createaccount;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.util.EmailCheck;

public class CreateAccountViewModel
{
  private ClientModel clientModel;
  private StringProperty error;

  public CreateAccountViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    error = new SimpleStringProperty();
  }

  StringProperty errorProperty()
  {
    return error;
  }

  public void createAccount(String name, String email, String password1, String password2, String address, String phoneNumber)
  {
    if (!name.equals("") && !email.equals("") && !password1.equals("") && !password2.equals("") && !address.equals("") && !phoneNumber.equals(""))
    {
      if (EmailCheck.isValid(email))
      {
        if (password1.equals(password2))
        {
          //createAccount returns a confirmation message, and this was my way of setting it. Most definitely not how it should be done
          /*error.set(chatSystem.createAccount(username, password));
          System.out.println(chatSystem.getAllAccounts());*/

          if (clientModel.createAccount(name, email, password1, address, phoneNumber))
          {
            error.set("Account created");
          }
          else
          {
            error.set("Could not contact server");
          }
        }
        else
        {
          error.set("Passwords must match");
        }
      }
      else
      {
        error.set("Invalid email");
      }
    }
    else
    {
      error.set("All * fields must be filled");
    }
  }

  public void createAccount(String name, String email, String password1, String password2, String address, String phoneNumber, String bio)
  {
    if (!name.equals("") && !email.equals("") && !password1.equals("") && !password2.equals("") && !address.equals("") && !phoneNumber.equals("") && !bio.equals(""))
    {
      if (EmailCheck.isValid(email))
      {
        if (password1.equals(password2))
        {
          //createAccount returns a confirmation message, and this was my way of setting it. Most definitely not how it should be done
          /*error.set(chatSystem.createAccount(username, password));
          System.out.println(chatSystem.getAllAccounts());*/

          if (clientModel.createAccount(name, email, password1, address, phoneNumber, bio))
          {
            error.set("Account created");
          }
          else
          {
            error.set("Email already in use");
          }
        }
        else
        {
          error.set("Passwords must match");
        }
      }
      else
      {
        error.set("Invalid email");
      }
    }
    else
    {
      error.set("All * fields must be filled");
    }
  }
}
