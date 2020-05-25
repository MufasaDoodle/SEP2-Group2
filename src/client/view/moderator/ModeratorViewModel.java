package client.view.moderator;

import client.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stuffs.FeedbackToItem;
import stuffs.Listing;
import stuffs.Report;

import java.util.List;

public class ModeratorViewModel
{
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private AccountModel accountModel;
  private FeedbackModel feedbackModel;
  private ChatModel chatModel;
  private ModeratorModel moderatorModel;
  private TransactionModel transactionModel;
  private ObservableList<Report> reports;

  public ModeratorViewModel(MasterModelInterface masterModel, ListingsModel listingsModel, AccountModel accountModel, FeedbackModel feedbackModel, ChatModel chatModel, ModeratorModel moderatorModel, TransactionModel transactionModel)
  {
    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
    this.accountModel = accountModel;
    this.feedbackModel = feedbackModel;
    this.chatModel = chatModel;
    this.moderatorModel = moderatorModel;
    this.transactionModel = transactionModel;
  }

  public void setReports()
  {
    List<Report> reportList = moderatorModel.getAllReports();
    reports = FXCollections.observableArrayList(reportList);
  }

  ObservableList<Report> getReports()
  {
    return reports;
  }

  public void setCurrentItemID(int itemID)
  {
    masterModel.setCurrentItemID(itemID);
  }

  public void setCurrentAccount(int accountId)
  {
    moderatorModel.setModeratorOpen(true);
    moderatorModel.setModeratedAccount(accountId);
  }

  public void setItemFeedback(int itemId)
  {
    masterModel.setCurrentItemID(itemId);
  }

  public void setFeedbackId(int feedbackId)
  {
    masterModel.setFeedbackId(feedbackId);
  }

  public FeedbackToItem getItemFeedback(int feedbackId)
  {
    return masterModel.getFeedbackById(feedbackId);
  }

  public void deleteReport(int id)
  {
    moderatorModel.deleteReport(id);
  }

  public void deleteItem(int id)
  {
    listingsModel.deleteListing(id);
  }

  public void deleteItemFeedback(int id)
  {
    feedbackModel.deleteItemFeedback(id);
  }

  public void deleteAccount(int id)
  {
    moderatorModel.deleteAccount(id);
  }

  public void deleteAccountTransaction(int id)
  {
    transactionModel.deleteTransactionByAccount(id);
  }

  public void deleteItemTransaction(int id)
  {
    transactionModel.deleteTransactionByItem(id);
  }

  public void deleteFeedbackBelongsToItem(int id)
  {
    feedbackModel.deleteFeedbackByItemId(id);
  }

  public void deleteRequestByItem(int id)
  {
    transactionModel.deleteRequest(id);
  }

  public void deleteRequestByAccount(int id)
  {
    transactionModel.deleteRequestByAccount(id);
  }

  public void deleteItemByAccount(int id)
  {
    listingsModel.deleteItemByAccount(id);
  }

  public void deleteReportByAccount(int id)
  {
    moderatorModel.deleteReportByAccount(id);
  }

  public void deleteReportByItem(int id)
  {
    moderatorModel.deleteReportByItem(id);
  }

  public void deleteReportByItemFeedback(int id)
  {
    moderatorModel.deleteReportByItemFeedback(id);
  }

  public void deleteMessageByItemAccount(int id)
  {
    moderatorModel.deleteMessageByAccount(id);
  }

  public List<Listing> getItemsByAccount(int accountId)
  {
    return accountModel.getListingsByAccount(accountId);
  }

  public List<FeedbackToItem> getItemFeedbackByItemDd(int itemId)
  {
    return feedbackModel.getFeedbackItems(itemId);
  }

  public void saveChatterId(int chatterId)
  {
    masterModel.setCurrentChatterID(chatterId);
  }

  public void addDeletedItemId(int itemId)
  {
    listingsModel.addDeletedItemId(itemId);
  }

  public void setAccountIDToLocalID()
  {
    chatModel.setLocalAccountID();
  }
}