package client.view.accountView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import stuffs.Listing;

public class AccountViewController implements ViewController
{
  private @FXML Label editErrorLabel;
  private @FXML Tab editTab;
  private @FXML TextField emailField;
  private @FXML PasswordField passField;
  private @FXML PasswordField passField2;
  private @FXML TextField addressField;
  private @FXML TextField numberField;
  private @FXML TextField bioField;
  private @FXML Label nameLabel;
  private @FXML Label addressLabel;
  private @FXML Label phoneLabel;
  private @FXML Label bioLabel;
  private @FXML Label avgRateLabel;

  @FXML private TableView<Listing> listingTable;
  @FXML private TableColumn<String, String> titleColumn;
  @FXML private TableColumn<String, String> categoryColumn;
  @FXML private TableColumn<String, String> descriptionColumn;
  @FXML private TableColumn<String, String> locationColumn;
  @FXML private TableColumn<String, String> durationColumn;
  @FXML private TableColumn<String, String> priceColumn;
  @FXML private TableColumn<String, String> dateColumn;

  private AccountViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getAccountViewModel();

    nameLabel.textProperty().bind(viewModel.nameProperty());
    addressLabel.textProperty().bind(viewModel.addressProperty());
    phoneLabel.textProperty().bind(viewModel.phoneProperty());
    bioLabel.textProperty().bind(viewModel.bioProperty());
    avgRateLabel.textProperty().bind(viewModel.avgRateProperty());
    emailField.textProperty().bindBidirectional(viewModel.emailEditProperty());
    addressField.textProperty().bindBidirectional(viewModel.addressEditProperty());
    numberField.textProperty().bindBidirectional(viewModel.numberEditProperty());
    bioField.textProperty().bindBidirectional(viewModel.bioEditProperty());
    passField.textProperty().bindBidirectional(viewModel.pass1Property());
    passField2.textProperty().bindBidirectional(viewModel.pass2Property());
    editErrorLabel.textProperty().bind(viewModel.errorProperty());

    setOwner();

    if (!viewModel.checkOwner(nameLabel.getText()))
    {
      editTab.setDisable(true);
    }

    viewModel.listOfOwnerListings();
    listingTable.setItems(viewModel.getListings());
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
  }

  private void setOwner()
  {
    viewModel.setOwner();
  }

  public void onBackButton()
  {
    vh.openItemScene();
  }

  public void onSeeItem(ActionEvent actionEvent)
  {
    int selectIndex = listingTable.getSelectionModel().getFocusedIndex();
    int itemID = listingTable.getItems().get(selectIndex).getId();
    viewModel.setItem(itemID);
    vh.openItemScene();
  }

  public void tabEvent(Event event)
  {
    if (editTab.isSelected())
    {
      viewModel.updateEditFields();
    }
  }

  public void onUpdate(ActionEvent actionEvent)
  {
    viewModel.updateAccountInfo(emailField.getText(), passField.getText(), passField2.getText(), addressField.getText(), numberField.getText(), bioField.getText());
  }

  /*
  @FXML void event(Event ev)
  {
    if (tabName.isSelected())
    {
      System.out.println("Tab is Selected");
      //Do stuff here
    }
  }

   */
}
