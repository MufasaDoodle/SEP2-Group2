package client.view.moderator;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transferobjects.FeedbackToItem;
import shared.transferobjects.Report;

import java.util.Optional;

public class ModeratorViewController implements ViewController
{
  @FXML private TableView<Report> reportTable;
  @FXML private TableColumn<String, String> reportFromColumn;
  @FXML private TableColumn<String, String> itemIDColumn;
  @FXML private TableColumn<String, String> accountColumn;
  @FXML private TableColumn<String, String> feedbackItemColumn;
  @FXML private TableColumn<String, String> dateColumn;

  private ModeratorViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getModeratorViewModel();

    viewModel.setReports();
    reportTable.setItems(viewModel.getReports());
    reportFromColumn.setCellValueFactory(new PropertyValueFactory<>("reportFrom"));
    itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("reportedItemId"));
    accountColumn.setCellValueFactory(new PropertyValueFactory<>("reportedAccountId"));
    feedbackItemColumn.setCellValueFactory(new PropertyValueFactory<>("reportedItemFeedbackId"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

  }

  public void onSeeReport()
  {
    int selectIndex = reportTable.getSelectionModel().getFocusedIndex();
    if (selectIndex < 0)
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Choose a report");
      promote.showAndWait();
    }
    else
    {
      int itemId = reportTable.getItems().get(selectIndex).getReportedItemId();
      int accountId = reportTable.getItems().get(selectIndex).getReportedAccountId();
      int feedbackItemId = reportTable.getItems().get(selectIndex).getReportedItemFeedbackId();

      if (itemId != 0)
      {
        viewModel.setCurrentItemID(itemId);
        vh.openItemScene();
      }
      else if (accountId != 0)
      {
        viewModel.setCurrentAccount(accountId);
        vh.openAccountScene();
      }
      else if (feedbackItemId != 0)
      {
        FeedbackToItem feedbackToItem = viewModel.getItemFeedback(feedbackItemId);
        viewModel.setItemFeedback(feedbackToItem.getItemId());
        viewModel.setFeedbackId(feedbackItemId);
        vh.openItemScene();
        viewModel.setReports();
        reportTable.setItems(viewModel.getReports());
      }
    }
  }

  public void onArchive()
  {
    int selectIndex = reportTable.getSelectionModel().getFocusedIndex();
    if (selectIndex < 0)
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Choose a report");
      promote.showAndWait();
    }
    else
    {
      int reportId = reportTable.getItems().get(selectIndex).getId();
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Archive");
      alert.setHeaderText("Do you want to archive?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK)
      {
        viewModel.deleteReport(reportId);
        viewModel.setReports();
        reportTable.setItems(viewModel.getReports());
        Alert promote = new Alert(Alert.AlertType.INFORMATION);
        promote.setTitle("Archived");
        promote.setHeaderText("Successfully archived!");
        promote.showAndWait();
      }
    }

  }

  public void onDelete()
  {
    int selectIndex = reportTable.getSelectionModel().getFocusedIndex();
    if (selectIndex < 0)
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Choose a report");
      promote.showAndWait();
    }
    else
    {
      int reportId = reportTable.getItems().get(selectIndex).getId();
      int itemId = reportTable.getItems().get(selectIndex).getReportedItemId();
      int accountId = reportTable.getItems().get(selectIndex).getReportedAccountId();
      int feedbackItemId = reportTable.getItems().get(selectIndex).getReportedItemFeedbackId();

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete");
      alert.setHeaderText("Do you want to delete?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK)
      {
        if (itemId != 0)
        {
          for (int i = 0; i < viewModel.getItemFeedbackByItemDd(itemId).size(); i++)
          {
            viewModel.deleteReportByItemFeedback(viewModel.getItemFeedbackByItemDd(itemId).get(i).getId());
          }
          viewModel.deleteFeedbackBelongsToItem(itemId);
          viewModel.deleteItemTransaction(itemId);
          viewModel.deleteRequestByItem(itemId);
          viewModel.deleteReportByItem(itemId);
          viewModel.addDeletedItemId(itemId);
          viewModel.deleteItem(itemId);
          viewModel.deleteReport(reportId);
        }
        else if (accountId != 0)
        {
          for (int i = 0; i < viewModel.getItemsByAccount(accountId).size(); i++)
          {
            viewModel.deleteFeedbackBelongsToItem(viewModel.getItemsByAccount(accountId).get(i).getId());
            viewModel.deleteRequestByItem(viewModel.getItemsByAccount(accountId).get(i).getId());
            viewModel.deleteItemTransaction(viewModel.getItemsByAccount(accountId).get(i).getId());
            viewModel.deleteReportByItem(viewModel.getItemsByAccount(accountId).get(i).getId());
            for (int j = 0; i < viewModel.getItemFeedbackByItemDd(viewModel.getItemsByAccount(accountId).get(i).getId()).size(); i++)
            {
              viewModel.deleteReportByItemFeedback(viewModel.getItemFeedbackByItemDd(viewModel.getItemsByAccount(accountId).get(i).getId()).get(j).getId());
            }
            viewModel.addDeletedItemId(viewModel.getItemsByAccount(accountId).get(i).getId());
          }
          viewModel.deleteMessageByItemAccount(accountId);
          viewModel.deleteAccountTransaction(accountId);
          viewModel.deleteRequestByAccount(accountId);
          viewModel.deleteItemByAccount(accountId);
          viewModel.deleteReportByAccount(accountId);
          viewModel.deleteAccount(accountId);
          viewModel.deleteReport(reportId);
        }
        else if (feedbackItemId != 0)
        {
          viewModel.deleteItemFeedback(feedbackItemId);
          viewModel.deleteReport(reportId);
        }
        viewModel.setReports();
        reportTable.setItems(viewModel.getReports());
        Alert promote = new Alert(Alert.AlertType.INFORMATION);
        promote.setTitle("Deleted");
        promote.setHeaderText("Successfully deleted!");
        promote.showAndWait();
      }
    }
  }

  public void onChat()
  {
    int selectIndex = reportTable.getSelectionModel().getFocusedIndex();
    if (selectIndex < 0)
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Choose a report");
      promote.showAndWait();
    }
    else
    {
      int accountID = reportTable.getItems().get(selectIndex).getReportFrom();
      viewModel.saveChatterId(accountID);
      vh.openChatScene();
      viewModel.setReports();
      reportTable.setItems(viewModel.getReports());
    }
  }

  public void onMyAccount()
  {
    viewModel.setAccountIDToLocalID();
    vh.openMessagesScene();
    viewModel.setReports();
    reportTable.setItems(viewModel.getReports());
  }

}