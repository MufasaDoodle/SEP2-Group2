package client.view.itemView;

import client.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import shared.transferobjects.Account;
import shared.transferobjects.FeedbackToItem;
import shared.transferobjects.Listing;
import shared.transferobjects.Request;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemViewModel
{
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private FeedbackModel feedbackModel;
  private ModeratorModel moderatorModel;
  private TransactionModel transactionModel;
  private StringProperty request, reply, owner, itemName, price, location, description;
  private StringProperty id;
  private StringProperty error;
  private StringProperty accountId;
  private StringProperty accountName;
  private StringProperty avgStarRating;
  private StringProperty result;
  ObservableList<FeedbackToItem> feedback;

  public ItemViewModel(MasterModelInterface masterModel, ListingsModel listingsModel, FeedbackModel feedbackModel, ModeratorModel moderatorModel, TransactionModel transactionModel)
  {
    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
    this.feedbackModel = feedbackModel;
    this.moderatorModel = moderatorModel;
    this.transactionModel = transactionModel;
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
    result = new SimpleStringProperty();
  }

  public StringProperty resultProperty()
  {
    return result;
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
      result.setValue("banned account");
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

    Request request = transactionModel.getRequest(itemId, requestFrom);

    if (request == null && requestFrom != requestTo)
    {
      transactionModel.createRequest(itemId, requestFrom, requestTo);
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
      alert1.setTitle("Rent the item");
      alert1.setHeaderText("Rent request is sent to the owner!");
      alert1.showAndWait();
      result.setValue("OK");
    }
    else if (requestFrom == requestTo)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Cannot rent your own item");
      alert.showAndWait();
      result.setValue("own item");
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Request already sent!");
      alert.showAndWait();
      result.setValue("request already sent");
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
      result.setValue("reported");
    }
    else
    {
      if (masterModel.getCurrentAccountID() != 1)
      {
        int reportFrom = masterModel.getCurrentAccountID();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        moderatorModel.createReport(reportFrom, itemId, 0, 0, dateFormat.format(date));
        result.setValue("OK");
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

      moderatorModel.createReport(reportFrom, 0, 0, feedbackId, dateFormat.format(date));
    }
  }

  public boolean getReportByItem()
  {
    return moderatorModel.getReportByItemId(masterModel.getCurrentItemID()) == null;
  }

  public boolean getReportByFeedbackId(int feedbackId)
  {
    return moderatorModel.getReportByFeedbackId(feedbackId) == null;
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