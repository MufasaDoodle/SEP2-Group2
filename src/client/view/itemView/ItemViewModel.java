package client.view.itemView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import stuffs.Listing;
import stuffs.Request;
import stuffs.Transaction;
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
    if (getCurrentItemId() != 0)
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

  public int getCurrentItemId()
  {
    return clientModel.getCurrentItemID();
  }

  public List<Integer> getDeletedItemIds()
  {
    return clientModel.getDeletedItemIds();
  }

  public void setWhereFromOpen(boolean whereFromOpen)
  {
    clientModel.setFromListingViewOpen(whereFromOpen);
  }

  public void saveChatterID()
  {
    int accID = clientModel.getListingByID(clientModel.getCurrentItemID()).getAccountId();
    if (!(clientModel.getCurrentAccountID() == accID))
    {
      clientModel.setCurrentChatterID(accID);
    }
  }

  public void saveChatterName()
  {
    clientModel.saveChatterName();
    
  public void rentItem()
  {

    int itemId = clientModel.getCurrentItemID();
    int requestFrom = clientModel.getCurrentAccountID();
    int requestTo = clientModel.getListingByID(clientModel.getCurrentItemID())
        .getAccountId();

    Request request = clientModel.getRequest(itemId, requestFrom);

    if (request == null && requestFrom != requestTo)
    {
      clientModel.createRequest(itemId, requestFrom, requestTo);
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
      alert1.setTitle("Rent the item");
      alert1.setHeaderText("Rent request is sent to the owner!");
      alert1.showAndWait();
    }
    else if (requestFrom == requestTo)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Cannot rent your own item");
      alert.showAndWait();
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Request already sent!");
      alert.showAndWait();
    }
  }

  public Transaction getTransaction(int itemID){
    return clientModel.getTransactionByItemId(itemID);
  }

  public void saveViewingAccountID()
  {
    int accID = clientModel.getListingByID(clientModel.getCurrentItemID()).getAccountId();
    if (!(clientModel.getCurrentAccountID() == accID))
    {
      clientModel.setViewingAccountID(accID);
    }
  }
}
