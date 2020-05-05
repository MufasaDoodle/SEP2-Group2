package client.view.login;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.util.EmailCheck;

public class LoginViewModel
{
  private ClientModel clientModel;
  private StringProperty error;

  public LoginViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    error = new SimpleStringProperty();
  }
  public void setAccountId(String email){
    clientModel.setCurrentAccountID(email);
  }

  public boolean checkLogIn(String email, String password)
  {
    if (EmailCheck.isValid(email))
    {
      boolean temp = clientModel.checkLogIn(email, password);
      if (!temp)
      {
        error.set("Password or email is wrong");
      }
      return temp;
    }
    else
    {
      error.set("Email is not valid");
      return false;
    }
  }

  public StringProperty errorProperty()
  {
    return error;
  }
}
