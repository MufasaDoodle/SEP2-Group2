package client.view.itemView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import stuffs.FeedbackToItem;
import stuffs.Transaction;

import java.util.Optional;

public class ItemViewController implements ViewController
{
  private @FXML Label ownerName;
  private @FXML Label itemName;
  private @FXML Label priceLabel;
  private @FXML Label locationLabel;
  private @FXML Label itemRatingLabel;
  private @FXML Label errorLabel;
  private @FXML Label idLabel;
  private @FXML Label accountIdLabel;
  private @FXML Label accountNameLabel;

  private @FXML  TableView<FeedbackToItem> feedbackTable;
  private @FXML  TableColumn<String,String> accountNameColumn;
  private @FXML  TableColumn<String,String> starRatingColumn;
  private @FXML  TableColumn<String,String> feedbackColumn;


  private @FXML TextArea descriptionTextArea;
  private @FXML TextArea feedbackTextArea;

  private @FXML Button rate1;
  private @FXML Button rate2;
  private @FXML Button rate3;
  private @FXML Button rate4;
  private @FXML Button rate5;
  private String buttonRate= "";



  private ItemViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getItemViewModel();
    ownerName.textProperty().bind(viewModel.ownerProperty());
    itemName.textProperty().bind(viewModel.itemNameProperty());
    priceLabel.textProperty().bind(viewModel.priceProperty());
    locationLabel.textProperty().bind(viewModel.locationProperty());
    itemRatingLabel.textProperty().bind(viewModel.avgStarRatingProperty());
    descriptionTextArea.textProperty().bind(viewModel.descriptionProperty());
    idLabel.textProperty().bind(viewModel.idProperty());
    errorLabel.textProperty().bind(viewModel.errorProperty());
    accountIdLabel.textProperty().bindBidirectional(viewModel.accountIdProperty());
    accountNameLabel.textProperty().bindBidirectional(viewModel.accountNameProperty());
    viewModel.setItem();
    idLabel.setVisible(false);
    accountNameLabel.setVisible(false);
    accountIdLabel.setVisible(false);
    feedbackTextArea.setText("");
}

  public void onLeaveFeedback() {
    if (!(feedbackTextArea.getText().equals("")) && buttonRate.equals("")) {
        viewModel.leaveFeedback("No star rating", feedbackTextArea.getText(), Integer.parseInt(idLabel.getText()), Integer.parseInt(accountIdLabel.getText()), accountNameLabel.getText());
        feedbackTextArea.setText("");
        buttonRate = "";
      }
    else if(feedbackTextArea.getText().equals("") && !buttonRate.equals("")) {
      viewModel.leaveFeedback(buttonRate, "No feedback", Integer.parseInt(idLabel.getText()), Integer.parseInt(accountIdLabel.getText()), accountNameLabel.getText());
        feedbackTextArea.setText("");
        buttonRate = "";
      }
    else if(!(feedbackTextArea.getText().equals("") && buttonRate.equals(""))) {
      viewModel.leaveFeedback(buttonRate, feedbackTextArea.getText(), Integer.parseInt(idLabel.getText()), Integer.parseInt(accountIdLabel.getText()), accountNameLabel.getText());
      {
        feedbackTextArea.setText("");
        buttonRate = "";
      }
    }
    /*else {
        feedbackTextArea.setText("");
        buttonRate = "";
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No renting for this listing");
        alert.setHeaderText("Leaving feedback not allowed");
        alert.setContentText("You must rent an item before leaving feedback for it!");
        alert.showAndWait();
      }*/
      feedbackTextArea.setText("");
      buttonRate = "";
    }
  public void onRateButtons(ActionEvent actionEvent)
  {
    if(actionEvent.getSource() == rate1)
    {
      buttonRate = "1";
    }
    else if(actionEvent.getSource() == rate2)
    {
      buttonRate = "2";
    }
    else if(actionEvent.getSource() == rate3)
    {
      buttonRate = "3";
    }      else if(actionEvent.getSource() == rate4)
    {
      buttonRate = "4";
    }      else if(actionEvent.getSource() == rate5)
    {
      buttonRate = "5";
    }
  }

  public void onContactOwner()
  {
    if (!(viewModel.getDeletedItemIds().contains(viewModel.getCurrentItemId())))
    {
      viewModel.setWhereFromOpen(false);
      viewModel.saveChatterID();
      viewModel.saveViewingAccountID();
      viewModel.saveChatterName();
      vh.openAccountScene();
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item deleted");
      alert.setContentText("Item is not available!");
      alert.showAndWait();
    }
  }

  public void onFeedbackTab()
  {
    viewModel.listOfFeedback(Integer.parseInt(idLabel.getText()));
    feedbackTable.setItems(viewModel.getFeedbackItems());
    accountNameColumn.setCellValueFactory(new PropertyValueFactory<>("accountName"));
    starRatingColumn.setCellValueFactory(new PropertyValueFactory<>("startRating"));
    feedbackColumn.setCellValueFactory(new PropertyValueFactory<>("writtenFeedback"));

     }
  public void onBackToListing()
  {
    vh.openSeeListingScene();
  }

  public void onRentItem()
  {
    Transaction transaction = viewModel
        .getTransaction(viewModel.getCurrentItemId());

    if (viewModel.getListing().getRented().equals("Available"))
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Rent the item");
      alert.setHeaderText("Do you want to rent this item?");

      Optional<ButtonType> result = alert.showAndWait();

      if (result.get() == ButtonType.OK)
      {
        viewModel.rentItem();
      }
    }else if(viewModel.getListing().getRented().equals("Rented"))
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item rented");
      alert.setContentText("Item is already rented, contact the owner for more information!");
      alert.showAndWait();
    }

  }
}




