package client.view.createaccount;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

  public void createAccount(String name, String email, String password1,
      String password2, String address, String phoneNumber)
  {
    if (!name.equals("") && !email.equals("") && !password1.equals("")
        && !password2.equals("") && !address.equals("") && !phoneNumber
        .equals(""))
    {
      if (password1.equals(password2))
      {
        //createAccount returns a confirmation message, and this was my way of setting it. Most definitely not how it should be done
        /*error.set(chatSystem.createAccount(username, password));
        System.out.println(chatSystem.getAllAccounts());*/

        clientModel.createAccount(name, email, password1, address, phoneNumber);
      }
      else
      {
        error.set("Passwords must match");
      }
    }
    else
    {
      error.set("All * fields must be filled");
    }
  }
}
