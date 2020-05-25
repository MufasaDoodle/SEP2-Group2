package client.view.login;

import client.model.AccountModel;
import client.model.ClientModel;
import client.model.ListingsModel;
import client.model.MasterModelInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import shared.util.EmailCheck;

public class LoginViewModel
{
  private ClientModel clientModel;
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private AccountModel accountModel;
  private StringProperty error;

  public LoginViewModel(ClientModel clientModel, MasterModelInterface masterModel, ListingsModel listingsModel, AccountModel accountModel)
  {
    this.clientModel = clientModel;
    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
    this.accountModel = accountModel;
    error = new SimpleStringProperty();
  }
  public void setAccountId(String email){
    masterModel.setCurrentAccountID(email);
  }
  public void setAccountName(String email)
  {
    accountModel.setCurrentAccountName(email);
  }

  public boolean checkLogIn(String email, String password)
  {
    if (EmailCheck.isValid(email))
    {
      boolean temp = accountModel.checkLogIn(email, password);
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
