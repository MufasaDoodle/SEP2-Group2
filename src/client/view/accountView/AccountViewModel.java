package client.view.accountView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EmailCheck;
import stuffs.Account;
import stuffs.Listing;

import java.util.List;

public class AccountViewModel
{
  private ClientModel clientModel;
  private StringProperty name, address, phone, bio, avgRate, emailEdit, addressEdit, numberEdit, bioEdit, error, pass1, pass2;
  private ObservableList<Listing> listings;

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
    //Todo
    //Need the account id from an item
    int itemId = clientModel.getCurrentItemID();
    int accountId = clientModel.getListingByID(itemId).getAccountId();
    Account temp = clientModel.getAccountById(accountId);
    name.set(temp.getName());
    address.set(temp.getAddress());
    phone.set(temp.getPhoneNumber());
    bio.set(temp.getBio());
    //Need an avg rate for account
    //avgRate.set(String.valueOf(temp.));
  }

  ObservableList<Listing> getListings()
  {
    return listings;
  }

  public void listOfOwnerListings()
  {
    Listing temp = clientModel.getListingByID(clientModel.getCurrentItemID());
    List<Listing> list = clientModel.getListingsByAccount(clientModel.getAccountById(temp.getAccountId()).getId());
    listings = FXCollections.observableArrayList(list);
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
            error.set("Email already in use");
          }
          else
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

  public boolean checkOwner(String name)
  {
    if (clientModel.getAccountById(clientModel.getCurrentAccountID()).getName().equals(name))
    {
      return true;
    }
    return false;
  }
}
