package client.view.itemView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import stuffs.*;

import java.beans.PropertyChangeEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

  public boolean leaveFeedback(String starRating, String feedback, int itemId, int accountId, String accountName)
  {
    if (clientModel.getListingByID(itemId) == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
      alert.showAndWait();
    }
    else
    {
      for (int i = 0; i < clientModel.getRentedTo(itemId).size(); i++)
      {
        if (accountId == clientModel.getRentedTo(itemId).get(i))
        {
          if ((!(starRating.equals("")) && feedback.equals("")))
          {
            if (clientModel.createFeedbackItems(itemId, starRating, "No feedback", accountId, accountName))
            {
              error.setValue("Feedback created");
              return true;
            }
          }
          if (!(feedback.equals("")) && starRating.equals(""))
          {
            if (clientModel.createFeedbackItems(itemId, "No star rating", feedback, accountId, accountName))
            {
              error.setValue("Feedback created");
              return true;
            }
          }
          if (!(feedback.equals("") && starRating.equals("")))
          {
            if (clientModel.createFeedbackItems(itemId, starRating, feedback, accountId, accountName))
            {
              error.setValue("Feedback created");
              return true;
            }
          }
          else
          {
            error.setValue("All fields must be filled");
            return false;
          }
          break;
        }
      }
    }
    return false;
  }

  public void listOfFeedback(int itemId)
  {
    if (clientModel.getCurrentAccountID() == 1)
    {
      List<FeedbackToItem> list = new ArrayList<>();
      list.add(clientModel.getFeedbackById(clientModel.getFeedbackId()));
      feedback = FXCollections.observableArrayList(list);
    }
    else
    {
      List<FeedbackToItem> list = clientModel.getFeedbackItems(itemId);
      feedback = FXCollections.observableArrayList(list);
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
    Account tempCheck = clientModel.getAccountById(clientModel.getCurrentAccountID());
    if (tempCheck == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Your account is banned");
      alert.showAndWait();
    }
    if (getCurrentItemId() != 0 && tempCheck != null)
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
    Listing listing = clientModel.getListingByID(clientModel.getCurrentItemID());
    if (listing == null)
    {
      clientModel.setCurrentChatterID(1);
    }
    else
    {
      int accID = clientModel.getListingByID(clientModel.getCurrentItemID()).getAccountId();
      if (!(clientModel.getCurrentAccountID() == accID) || clientModel.getAccountById(accID) != null || clientModel.getAccountById(clientModel.getCurrentAccountID()) != null)
      {
        clientModel.setCurrentChatterID(accID);
      }
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

  public Listing getListing()
  {
    int itemId = clientModel.getCurrentItemID();
    if (clientModel.getListingByID(itemId) == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
    }
    else
    {
      return clientModel.getListingByID(itemId);
    }
    return null;
  }

  public void saveViewingAccountID()
  {
    Listing listing = clientModel.getListingByID(clientModel.getCurrentItemID());
    if (listing == null)
    {
      clientModel.setCurrentChatterID(1);
    }
    else
    {
      int accID = clientModel.getListingByID(clientModel.getCurrentItemID()).getAccountId();
      clientModel.setViewingAccountID(accID);
    }

  }

  public void reportItem()
  {
    int itemId = clientModel.getCurrentItemID();
    if (clientModel.getListingByID(itemId) == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
      alert.showAndWait();
    }
    else
    {
      if (clientModel.getCurrentAccountID() != 1)
      {
        int reportFrom = clientModel.getCurrentAccountID();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        clientModel.createReport(reportFrom, itemId, 0, 0, dateFormat.format(date));
      }
    }
  }

  public int getAccountId()
  {
    return clientModel.getCurrentAccountID();
  }

  public void reportFeedback(int feedbackId)
  {
    int itemId = clientModel.getCurrentItemID();
    if (clientModel.getListingByID(itemId) == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
      alert.showAndWait();
    }
    else
    {
      int reportFrom = clientModel.getCurrentAccountID();
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date();

      clientModel.createReport(reportFrom, 0, 0, feedbackId, dateFormat.format(date));
    }
  }

  public boolean getReportByItem()
  {
    return clientModel.getReportByItemId(clientModel.getCurrentItemID()) == null;
  }

  public boolean getReportByFeedbackId(int feedbackId)
  {
    return clientModel.getReportByFeedbackId(feedbackId) == null;
  }

  public FeedbackToItem getFeedback(int feedbackId)
  {
    return clientModel.getFeedbackById(feedbackId);
  }

  public Listing getListingById(int id)
  {
    if (clientModel.getListingByID(id) == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
    }
    else
    {
      return clientModel.getListingByID(id);
    }
    return null;
  }

  public boolean accountCheck()
  {
    return clientModel.accountCheck();
  }
}