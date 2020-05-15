package client.view.accountView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import shared.util.EmailCheck;
import stuffs.Account;
import stuffs.Listing;

import java.util.List;
import java.util.Optional;

public class AccountViewModel
{
  private ClientModel clientModel;
  private StringProperty name, address, phone, bio, avgRate, emailEdit, addressEdit, numberEdit, bioEdit, error, pass1, pass2;
  private ObservableList<Listing> listings;
  private int accountID;

  public AccountViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    name = new SimpleStringProperty();
    address = new SimpleStringProperty();
    phone = new SimpleStringProperty();
    bio = new SimpleStringProperty();
    avgRate = new SimpleStringProperty();
    emailEdit = new SimpleStringProperty();
    addressEdit = new SimpleStringProperty();
    numberEdit = new SimpleStringProperty();
    bioEdit = new SimpleStringProperty();
    error = new SimpleStringProperty();
    pass1 = new SimpleStringProperty();
    pass2 = new SimpleStringProperty();
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public StringProperty addressProperty()
  {
    return address;
  }

  public StringProperty phoneProperty()
  {
    return phone;
  }

  public StringProperty bioProperty()
  {
    return bio;
  }

  public StringProperty avgRateProperty()
  {
    return avgRate;
  }

  public StringProperty emailEditProperty()
  {
    return emailEdit;
  }

  public StringProperty addressEditProperty()
  {
    return addressEdit;
  }

  public StringProperty numberEditProperty()
  {
    return numberEdit;
  }

  public StringProperty bioEditProperty()
  {
    return bioEdit;
  }

  public StringProperty errorProperty()
  {
    return error;
  }

  public StringProperty pass1Property()
  {
    return pass1;
  }

  public StringProperty pass2Property()
  {
    return pass2;
  }

  public void setOwner()
  {
    if (clientModel.getFromListingViewOpen())
    {
      Account temp = clientModel.getAccountById(clientModel.getCurrentAccountID());
      name.set(temp.getName());
      address.set(temp.getAddress());
      phone.set(temp.getPhoneNumber());
      bio.set(temp.getBio());
    }
    else if (!clientModel.getFromListingViewOpen())
    {
      int itemId = clientModel.getCurrentItemID();
      int accountId = clientModel.getListingByID(itemId).getAccountId();
      Account temp = clientModel.getAccountById(accountId);
      name.set(temp.getName());
      address.set(temp.getAddress());
      phone.set(temp.getPhoneNumber());
      bio.set(temp.getBio());
    }

    //Need an avg rate for account
    //avgRate.set(String.valueOf(temp.));
  }

  ObservableList<Listing> getListings()
  {
    return listings;
  }

  public void listOfOwnerListings()
  {
    if (clientModel.getFromListingViewOpen())
    {
      List<Listing> list = clientModel.getListingsByAccount(clientModel.getCurrentAccountID());
      listings = FXCollections.observableArrayList(list);
    }
    else if (!clientModel.getFromListingViewOpen())
    {
      Listing temp = clientModel.getListingByID(clientModel.getCurrentItemID());
      List<Listing> list = clientModel.getListingsByAccount(clientModel.getAccountById(temp.getAccountId()).getId());
      listings = FXCollections.observableArrayList(list);
    }
  }

  public void setItem(int itemID)
  {
    clientModel.setCurrentItemID(itemID);
  }

  public void updateEditFields()
  {
    Account account = clientModel.getAccountById(clientModel.getCurrentAccountID());
    emailEdit.set(account.getEmail());
    addressEdit.set(account.getAddress());
    numberEdit.set(account.getPhoneNumber());
    bioEdit.set(account.getBio());
    pass1.set(account.getPassword());
    pass2.set(account.getPassword());
  }

  public void updateAccountInfo(String email, String pass1, String pass2, String address, String number, String bio)
  {
    if (!email.equals("") && !pass1.equals("") && !pass2.equals("") && !address.equals("") && !number.equals("") && !bio.equals(""))
    {
      if (EmailCheck.isValid(email))
      {
        if (pass1.equals(pass2))
        {
          if (clientModel.isEmailTaken(email) && !clientModel.getAccountById(clientModel.getCurrentAccountID()).getEmail().equals(email))
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Email is already in use!");
            alert.setContentText("Please choose another!");
            alert.showAndWait();
          }
          else
          {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update Account");
            alert.setHeaderText("Do you want to update your account?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
              if (clientModel.updateAccount(email, pass1, address, number, bio))
              {
                error.set("Account updated");
                setOwner();
              }
              else
              {
                error.set("Account could not be updated, try again later");
              }
            }
          }
        }
        else
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Warning");
          alert.setHeaderText("Passwords do not match!");
          alert.setContentText("Please check your passwords!");
          alert.showAndWait();
        }
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Email is not valid!");
        alert.setContentText("Please check your email again!");
        alert.showAndWait();
      }
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("All * fields must be filled!");
      alert.setContentText("Please fill all * fields!");
      alert.showAndWait();
    }
  }

  public boolean checkOwner(String name)
  {
    if (clientModel.checkOwner())
    {
      return true;
    }
    return false;
    /*
    if (clientModel.getAccountById(clientModel.getCurrentAccountID()).getName().equals(name))
    {
      return true;
    }
    return false;

     */
  }

  public int getCurrentItemId()
  {
    return clientModel.getCurrentItemID();
  }

  public List<Integer> getDeletedItemIds()
  {
    return clientModel.getDeletedItemIds();
  }

  public int getAccountID()
  {
    return accountID;
  }

  public void setAccountID(int accountID)
  {
    this.accountID = accountID;
  }
}
