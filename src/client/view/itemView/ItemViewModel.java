package client.view.itemView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stuffs.Account;
import stuffs.Listing;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class ItemViewModel
{
  private ClientModel clientModel;
  private StringProperty request, reply, owner, itemName, price, location, rating, description;


  public ItemViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    //clientModel.addListener("NewFeedback", this::onNewFeedback);
    request = new SimpleStringProperty();
    reply = new SimpleStringProperty();
    owner = new SimpleStringProperty();
    itemName = new SimpleStringProperty();
    price = new SimpleStringProperty();
    location = new SimpleStringProperty();
    rating = new SimpleStringProperty();
    description = new SimpleStringProperty();
  }

  void leaveFeedback()
  {
    //Todo

  }

  StringProperty requestProperty()
  {
    return request;
  }

  StringProperty replyProperty()
  {
    return reply;
  }

  private void onNewFeedback(PropertyChangeEvent evt)
  {
    //Todo
  }

  void loadFeedback()
  {
    //Todo
  }

  public StringProperty ownerProperty()
  {
    return owner;
  }

  public StringProperty locationProperty()
  {
    return location;
  }

  public StringProperty priceProperty()
  {
    return price;
  }

  public StringProperty itemNameProperty()
  {
    return itemName;
  }

  public StringProperty ratingProperty()
  {
    return rating;
  }

  public StringProperty descriptionProperty()
  {
    return description;
  }

  public void setItem()
  {
    Listing temp = clientModel.getListingByID(clientModel.getCurrentItemID());
    owner.set(clientModel.getAccountById(temp.getAccountId()).getName());
    itemName.set(temp.getTitle());
    price.set(String.valueOf(temp.getPrice()));
    location.set(temp.getLocation());
    //set rating properly
    rating.set("0");
    description.set(temp.getDescription());
  }


}
