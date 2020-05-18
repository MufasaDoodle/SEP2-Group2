package client.view.login;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
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
  public void setAccountName(String email)
  {
    clientModel.setCurrentAccountName(email);
  }

  public boolean checkLogIn(String email, String password)
  {
    if (EmailCheck.isValid(email))
    {
      boolean temp = clientModel.checkLogIn(email, password);
      if (!temp)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Email or password is wrong!");
        alert.setContentText("Please check your email or password!");
        alert.showAndWait();
      }
      return temp;
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Email is not valid!");
      alert.setContentText("Please check your email again!");
      alert.showAndWait();
      return false;
    }
  }

  public StringProperty errorProperty()
  {
    return error;
  }
}
