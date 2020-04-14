package client.view.login;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel
{
  private ClientModel clientModel;
  private StringProperty error;

  public LoginViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    error = new SimpleStringProperty();
  }

  public boolean checkLogIn(String email, String password)
  {
    boolean temp = clientModel.checkLogIn(email, password);
    if (!temp)
    {
      error.set("Password or email is wrong");
    }
    return temp;
  }

  public StringProperty errorProperty()
  {
    return error;
  }
}
