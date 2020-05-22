package client.view.moderator;

import client.model.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stuffs.FeedbackToItem;
import stuffs.Listing;
import stuffs.Report;

import java.util.List;

public class ModeratorViewModel
{
  private ClientModel clientModel;
  private ObservableList<Report> reports;

  public ModeratorViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
  }

  public void setReports()
  {
    List<Report> reportList = clientModel.getAllReports();
    reports = FXCollections.observableArrayList(reportList);
  }

  ObservableList<Report> getReports()
  {
    return reports;
  }

  public void setCurrentItemID(int itemID)
  {
    clientModel.setCurrentItemID(itemID);
  }

  public void setCurrentAccount(int accountId)
  {
    clientModel.setModeratorOpen(true);
    clientModel.setModeratedAccount(accountId);
  }

  public void setItemFeedback(int itemId)
  {
    clientModel.setCurrentItemID(itemId);
  }

  public void setFeedbackId(int feedbackId)
  {
    clientModel.setFeedbackId(feedbackId);
  }

  public FeedbackToItem getItemFeedback(int feedbackId)
  {
    return clientModel.getFeedbackById(feedbackId);
  }

  public void deleteReport(int id)
  {
    clientModel.deleteReport(id);
  }

  public void deleteItem(int id)
  {
    clientModel.deleteListing(id);
  }

  public void deleteItemFeedback(int id)
  {
    clientModel.deleteItemFeedback(id);
  }

  public void deleteAccount(int id)
  {
    clientModel.deleteAccount(id);
  }

  public void deleteAccountTransaction(int id)
  {
    clientModel.deleteTransactionByAccount(id);
  }

  public void deleteItemTransaction(int id)
  {
    clientModel.deleteTransactionByItem(id);
  }

  public void deleteFeedbackBelongsToItem(int id)
  {
    clientModel.deleteFeedbackByItemId(id);
  }

  public void deleteRequestByItem(int id)
  {
    clientModel.deleteRequest(id);
  }

  public void deleteRequestByAccount(int id)
  {
    clientModel.deleteRequestByAccount(id);
  }

  public void deleteItemByAccount(int id)
  {
    clientModel.deleteItemByAccount(id);
  }

  public void deleteReportByAccount(int id)
  {
    clientModel.deleteReportByAccount(id);
  }

  public void deleteReportByItem(int id)
  {
    clientModel.deleteReportByItem(id);
  }

  public void deleteReportByItemFeedback(int id)
  {
    clientModel.deleteReportByItemFeedback(id);
  }

  public void deleteMessageByItemAccount(int id)
  {
    clientModel.deleteMessageByAccount(id);
  }

  public List<Listing> getItemsByAccount(int accountId)
  {
    return clientModel.getListingsByAccount(accountId);
  }

  public List<FeedbackToItem> getItemFeedbackByItemDd(int itemId)
  {
    return clientModel.getFeedbackItems(itemId);
  }

  public void saveChatterId(int chatterId)
  {
    clientModel.setCurrentChatterID(chatterId);
  }

  public void addDeletedItemId(int itemId)
  {
    clientModel.addDeletedItemId(itemId);
  }

  public void setAccountIDToLocalID()
  {
    clientModel.setLocalAccountID();
  }
}