package client.view.itemView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import stuffs.FeedbackToItem;
import stuffs.Listing;
import stuffs.Request;
import stuffs.Transaction;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class ItemViewModel
{
  private ClientModel clientModel;
  private StringProperty request, reply, owner, itemName, price, location, description;
  private StringProperty id;
  private StringProperty error;
  private StringProperty accountId;
  private StringProperty accountName;
  private StringProperty avgStarRating;
  ObservableList<FeedbackToItem> feedback;

  public ItemViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    clientModel.addListener("NewFeedback", this::onNewFeedback);
    request = new SimpleStringProperty();
    reply = new SimpleStringProperty();
    owner = new SimpleStringProperty();
    itemName = new SimpleStringProperty();
    price = new SimpleStringProperty();
    location = new SimpleStringProperty();
    description = new SimpleStringProperty();
    id = new SimpleStringProperty();
    error = new SimpleStringProperty();
    accountId = new SimpleStringProperty();
    accountName = new SimpleStringProperty();
    avgStarRating = new SimpleStringProperty();
  }

  private void onNewFeedback(PropertyChangeEvent event) {
    FeedbackToItem feedbacky = (FeedbackToItem) event.getNewValue();
    if(feedbacky != null)
    {
      feedback.add((FeedbackToItem) event.getNewValue());
    }
  }

  public boolean leaveFeedback(String starRating, String feedback, int itemId, int accountId, String accountName)
  {
    for (int i = 0; i < clientModel.getRentedTo(itemId).size(); i++)
    {
      if (accountId == clientModel.getRentedTo(itemId).get(i))
      {
          if (clientModel.createFeedbackItems(itemId, starRating, feedback, accountId, accountName))
          {
            error.setValue("Feedback created");
            return true;
          }
        }
        else
        {
          error.setValue("You must rent the item before!");
          return false;
        }
        break;
      }
    return false;
  }

  public void listOfFeedback(int itemId)
  {

    List<FeedbackToItem> list = clientModel.getFeedbackItems(itemId);
    feedback = FXCollections.observableArrayList();
    for (FeedbackToItem feedbacky : list) {
      if(feedbacky != null)
      {
        feedback.add(feedbacky);
      }
    }

  }

  ObservableList<FeedbackToItem> getFeedbackItems()
  {
    return feedback;
  }

  StringProperty requestProperty()
  {
    return request;
  }

  StringProperty replyProperty()
  {
    return reply;
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

  public StringProperty descriptionProperty()
  {
    return description;
  }

  StringProperty idProperty()
  {
    return id;
  }

  public StringProperty errorProperty()
  {
    return error;
  }

  public StringProperty accountIdProperty()
  {
    return accountId;
  }

  public StringProperty accountNameProperty()
  {
    return accountName;
  }

  public StringProperty avgStarRatingProperty()
  {
    return avgStarRating;
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
      description.set(temp.getDescription());
      id.set(String.valueOf(temp.getId()));
      accountId.setValue(String.valueOf(clientModel.getCurrentAccountID()));
      accountName.setValue(clientModel.getCurrentAccountName());
      avgStarRating.set(clientModel.getAvgStarRating(temp.getId()));
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
  }

  public void rentItem()
  {

    int itemId = clientModel.getCurrentItemID();
    int requestFrom = clientModel.getCurrentAccountID();
    int requestTo = clientModel.getListingByID(clientModel.getCurrentItemID()).getAccountId();

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

  public Transaction getTransaction(int itemID)
  {
    return clientModel.getTransactionByItemId(itemID);
  }

  public Listing getListing()
  {
    int itemId = clientModel.getCurrentItemID();
    return clientModel.getListingByID(itemId);
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
