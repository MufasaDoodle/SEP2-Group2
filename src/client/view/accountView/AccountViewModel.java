package client.view.accountView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stuffs.Account;
import stuffs.Listing;

import java.util.List;

public class AccountViewModel
{
  private ClientModel clientModel;
  private StringProperty name, address, phone, bio, avgRate;
  private ObservableList<Listing> listings;

  public AccountViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    name = new SimpleStringProperty();
    address = new SimpleStringProperty();
    phone = new SimpleStringProperty();
    bio = new SimpleStringProperty();
    avgRate = new SimpleStringProperty();
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
}
