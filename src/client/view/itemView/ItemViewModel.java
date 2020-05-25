package client.view.itemView;

import client.model.ClientModel;
import client.model.FeedbackModel;
import client.model.ListingsModel;
import client.model.MasterModelInterface;
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
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private FeedbackModel feedbackModel;
  private StringProperty request, reply, owner, itemName, price, location, description;
  private StringProperty id;
  private StringProperty error;
  private StringProperty accountId;
  private StringProperty accountName;
  private StringProperty avgStarRating;
  ObservableList<FeedbackToItem> feedback;

  public ItemViewModel(ClientModel clientModel, MasterModelInterface masterModel, ListingsModel listingsModel, FeedbackModel feedbackModel)
  {
    this.clientModel = clientModel;
    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
    this.feedbackModel = feedbackModel;
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
    if (masterModel.getListingByID(itemId) == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
      alert.showAndWait();
    }
    else
    {
      for (int i = 0; i < feedbackModel.getRentedTo(itemId).size(); i++)
      {
        if (accountId == feedbackModel.getRentedTo(itemId).get(i))
        {
          if ((!(starRating.equals("")) && feedback.equals("")))
          {
            if (feedbackModel.createFeedbackItems(itemId, starRating, "No feedback", accountId, accountName))
            {
              error.setValue("Feedback created");
              return true;
            }
          }
          if (!(feedback.equals("")) && starRating.equals(""))
          {
            if (feedbackModel.createFeedbackItems(itemId, "No star rating", feedback, accountId, accountName))
            {
              error.setValue("Feedback created");
              return true;
            }
          }
          if (!(feedback.equals("") && starRating.equals("")))
          {
            if (feedbackModel.createFeedbackItems(itemId, starRating, feedback, accountId, accountName))
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
    if (masterModel.getCurrentAccountID() == 1)
    {
      List<FeedbackToItem> list = new ArrayList<>();
      list.add(masterModel.getFeedbackById(masterModel.getFeedbackId()));
      feedback = FXCollections.observableArrayList(list);
    }
    else
    {
      List<FeedbackToItem> list = feedbackModel.getFeedbackItems(itemId);
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
    Account tempCheck = masterModel.getAccountById(masterModel.getCurrentAccountID());
    if (tempCheck == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Your account is banned");
      alert.showAndWait();
    }
    if (getCurrentItemId() != 0 && tempCheck != null)
    {
      Listing temp = masterModel.getListingByID(masterModel.getCurrentItemID());
      owner.set(masterModel.getAccountById(temp.getAccountId()).getName());
      itemName.set(temp.getTitle());
      price.set(String.valueOf(temp.getPrice()));
      location.set(temp.getLocation());
      description.set(temp.getDescription());
      id.set(String.valueOf(temp.getId()));
      accountId.setValue(String.valueOf(masterModel.getCurrentAccountID()));
      accountName.setValue(masterModel.getCurrentAccountName());
      avgStarRating.set(feedbackModel.getAvgStarRating(temp.getId()));
    }
  }

  public int getCurrentItemId()
  {
    return masterModel.getCurrentItemID();
  }

  public List<Integer> getDeletedItemIds()
  {
    return listingsModel.getDeletedItemIds();
  }

  public void setWhereFromOpen(boolean whereFromOpen)
  {
    listingsModel.setFromListingViewOpen(whereFromOpen);
  }

  public void saveChatterID()
  {
    Listing listing = masterModel.getListingByID(masterModel.getCurrentItemID());
    if (listing == null)
    {
      masterModel.setCurrentChatterID(1);
    }
    else
    {
      int accID = masterModel.getListingByID(masterModel.getCurrentItemID()).getAccountId();
      if (!(masterModel.getCurrentAccountID() == accID) || masterModel.getAccountById(accID) != null || masterModel.getAccountById(masterModel.getCurrentAccountID()) != null)
      {
        masterModel.setCurrentChatterID(accID);
      }
    }
  }

  public void saveChatterName()
  {
    masterModel.saveChatterName();
  }

  public void rentItem()
  {
    int itemId = masterModel.getCurrentItemID();
    int requestFrom = masterModel.getCurrentAccountID();
    int requestTo = masterModel.getListingByID(masterModel.getCurrentItemID()).getAccountId();

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
    int itemId = masterModel.getCurrentItemID();
    if (masterModel.getListingByID(itemId) == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
    }
    else
    {
      return masterModel.getListingByID(itemId);
    }
    return null;
  }

  public void saveViewingAccountID()
  {
    Listing listing = masterModel.getListingByID(masterModel.getCurrentItemID());
    if (listing == null)
    {
      masterModel.setCurrentChatterID(1);
    }
    else
    {
      int accID = masterModel.getListingByID(masterModel.getCurrentItemID()).getAccountId();
      masterModel.setViewingAccountID(accID);
    }

  }

  public void reportItem()
  {
    int itemId = masterModel.getCurrentItemID();
    if (masterModel.getListingByID(itemId) == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
      alert.showAndWait();
    }
    else
    {
      if (masterModel.getCurrentAccountID() != 1)
      {
        int reportFrom = masterModel.getCurrentAccountID();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        clientModel.createReport(reportFrom, itemId, 0, 0, dateFormat.format(date));
      }
    }
  }

  public int getAccountId()
  {
    return masterModel.getCurrentAccountID();
  }

  public void reportFeedback(int feedbackId)
  {
    int itemId = masterModel.getCurrentItemID();
    if (masterModel.getListingByID(itemId) == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
      alert.showAndWait();
    }
    else
    {
      int reportFrom = masterModel.getCurrentAccountID();
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date();

      clientModel.createReport(reportFrom, 0, 0, feedbackId, dateFormat.format(date));
    }
  }

  public boolean getReportByItem()
  {
    return clientModel.getReportByItemId(masterModel.getCurrentItemID()) == null;
  }

  public boolean getReportByFeedbackId(int feedbackId)
  {
    return clientModel.getReportByFeedbackId(feedbackId) == null;
  }

  public FeedbackToItem getFeedback(int feedbackId)
  {
    return masterModel.getFeedbackById(feedbackId);
  }

  public Listing getListingById(int id)
  {
    if (masterModel.getListingByID(id) == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item is deleted");
    }
    else
    {
      return masterModel.getListingByID(id);
    }
    return null;
  }

  public boolean accountCheck()
  {
    return masterModel.accountCheck();
  }
}