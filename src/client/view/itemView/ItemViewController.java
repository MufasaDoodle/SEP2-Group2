package client.view.itemView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ItemViewController implements ViewController
{
  private @FXML Label ownerName;
  private @FXML Label itemName;
  private @FXML Label priceLabel;
  private @FXML Label locationLabel;
  private @FXML Label itemRateLabel;
  private @FXML Label itemRatingLabel;
  private @FXML Label errorLabel;

  //private @FXML  TableView<> tableView;
  //private @FXML  TableColumn<String, > inputColumn;

  private @FXML TextArea descriptionTextArea;
  private @FXML TextArea feedbackTextArea;

  private @FXML Button rate1;
  private @FXML Button rate2;
  private @FXML Button rate3;
  private @FXML Button rate4;
  private @FXML Button rate5;

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
    itemRatingLabel.textProperty().bind(viewModel.ratingProperty());
    descriptionTextArea.textProperty().bind(viewModel.descriptionProperty());
    viewModel.setItem();
    /*ownerName.textProperty().setValue("Owner: " + viewModel.getOwnerName());
    //Todo itemName ownerName
    itemName.textProperty().setValue("Item: " + viewModel.getItemName());
    viewModel.loadFeedback();
    price.textProperty().setValue("Price: " + viewModel.getPrice);
    location.textProperty().setValue("Location: " + viewModel.getLocation);
    itemRate.textProperty().setValue("Rate for item: " + viewModel.getRate);
    //inputColumn.setCellValueFactory(new PropertyValueFactory<>("feedback"));
    //tableView.setItems((ObservableList<Feedback>) viewModel.getFeedback());
    feedbackTextArea.textProperty().set("");
    viewModel.loadFeedback();*/
  }

  public void onLeaveFeedback()
  {
    if (!(feedbackTextArea.textProperty().get().equals("")))
    {
      viewModel.leaveFeedback();
      feedbackTextArea.clear();
    }
    else
    {
      errorLabel.textProperty().set("Please write something...");
    }
  }

  public void onRateButtons()
  {

    //Todo

  }

  public void onContactOwner()
  {
    if (!(viewModel.getDeletedItemIds().contains(viewModel.getCurrentItemId())))
    {
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

  public void onBackToListing()
  {
    vh.openSeeListingScene();
  }
}




