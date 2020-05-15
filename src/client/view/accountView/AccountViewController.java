package client.view.accountView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import stuffs.*;

import java.util.Optional;

public class AccountViewController implements ViewController
{
  private @FXML Label editErrorLabel;
  private @FXML Tab editTab;
  private @FXML Tab requestTab;
  private @FXML Tab rentalsTab;
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
  private @FXML Button editItemButton;

  @FXML private TableView<Listing> listingTable;
  @FXML private TableColumn<String, String> titleColumn;
  @FXML private TableColumn<String, String> categoryColumn;
  @FXML private TableColumn<String, String> descriptionColumn;
  @FXML private TableColumn<String, String> locationColumn;
  @FXML private TableColumn<String, String> durationColumn;
  @FXML private TableColumn<String, String> priceColumn;
  @FXML private TableColumn<String, String> dateColumn;

  @FXML private TableView<RequestListing> requestTable;
  @FXML private TableColumn<String, String> titleRequest;
  @FXML private TableColumn<String, String> categoryRequest;
  @FXML private TableColumn<String, String> priceRequest;
  @FXML private TableColumn<String, String> durationRequest;
  @FXML private TableColumn<String, String> requestFromRequest;

  @FXML private TableView<TransactionListing> transactionTable;
  @FXML private TableColumn<String, String> titleTransaction;
  @FXML private TableColumn<String, String> categoryTransaction;
  @FXML private TableColumn<String, String> priceTransaction;
  @FXML private TableColumn<String, String> dateTransaction;
  @FXML private TableColumn<String, String> durationTransaction;
  @FXML private TableColumn<String, String> nameTransaction;
  @FXML private TableColumn<String, String> statusTransaction;

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
    addressField.textProperty()
        .bindBidirectional(viewModel.addressEditProperty());
    numberField.textProperty()
        .bindBidirectional(viewModel.numberEditProperty());
    bioField.textProperty().bindBidirectional(viewModel.bioEditProperty());
    passField.textProperty().bindBidirectional(viewModel.pass1Property());
    passField2.textProperty().bindBidirectional(viewModel.pass2Property());
    editErrorLabel.textProperty().bind(viewModel.errorProperty());

    setOwner();

    if (!viewModel.checkOwner(nameLabel.getText()))
    {
      editTab.setDisable(true);
      requestTab.setDisable(true);
      rentalsTab.setDisable(true);
    }

    viewModel.listOfOwnerListings();
    listingTable.setItems(viewModel.getListings());
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    descriptionColumn
        .setCellValueFactory(new PropertyValueFactory<>("description"));
    locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    viewModel.listOfOwnerRequests();
    requestTable.setItems(viewModel.getRequests());
    titleRequest.setCellValueFactory(new PropertyValueFactory<>("title"));
    categoryRequest.setCellValueFactory(new PropertyValueFactory<>("category"));
    priceRequest.setCellValueFactory(new PropertyValueFactory<>("price"));
    durationRequest.setCellValueFactory(new PropertyValueFactory<>("duration"));
    requestFromRequest
        .setCellValueFactory(new PropertyValueFactory<>("requestFrom"));


    viewModel.listOfOwnerRentals();
    transactionTable.setItems(viewModel.getTransactions());
    titleTransaction.setCellValueFactory(new PropertyValueFactory<>("title"));
    categoryTransaction.setCellValueFactory(new PropertyValueFactory<>("category"));
    priceTransaction.setCellValueFactory(new PropertyValueFactory<>("price"));
    dateTransaction.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
    durationTransaction
        .setCellValueFactory(new PropertyValueFactory<>("duration"));
    nameTransaction
        .setCellValueFactory(new PropertyValueFactory<>("accountName"));
    statusTransaction
        .setCellValueFactory(new PropertyValueFactory<>("status"));
  }

  private void setOwner()
  {
    viewModel.setOwner();
  }

  public void onBackButton()
  {
    if (!(viewModel.getDeletedItemIds().contains(viewModel.getCurrentItemId())))
    {
      vh.openItemScene();
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item deleted");
      alert.setContentText("Item is not available!");
      alert.showAndWait();
      vh.openSeeListingScene();
    }

  }

  public void onSeeItem()
  {
    int selectIndex = listingTable.getSelectionModel().getFocusedIndex();
    int itemID = listingTable.getItems().get(selectIndex).getId();
    viewModel.setItem(itemID);

    if (!(viewModel.getDeletedItemIds().contains(itemID)))
    {
      vh.openItemScene();
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Item deleted");
      alert.setContentText("Item is not available!");
      alert.showAndWait();
      vh.openSeeListingScene();
    }
  }

  public void onEditItem()
  {
    int selectIndex = listingTable.getSelectionModel().getFocusedIndex();
    int itemID = listingTable.getItems().get(selectIndex).getId();
    viewModel.setItem(itemID);

    if (viewModel.checkOwner(nameLabel.getText()))
    {
      vh.openEditItemScene();
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Not owner");
      alert.setContentText("You are not the owner of this item!");
      alert.showAndWait();
    }
  }

  public void tabEvent()
  {
    if (editTab.isSelected())
    {
      viewModel.updateEditFields();
    }
  }

  public void onUpdate()
  {
    viewModel.updateAccountInfo(emailField.getText(), passField.getText(),
        passField2.getText(), addressField.getText(), numberField.getText(),
        bioField.getText());
  }

  public void onAccept()
  {
    int selectIndex = requestTable.getSelectionModel().getFocusedIndex();
    int itemID = requestTable.getItems().get(selectIndex).getItemId();
    int rentedToId = requestTable.getItems().get(selectIndex)
        .getRequestFromId();

    Transaction transaction = viewModel.getTransaction(itemID);
    Request request = viewModel.getRequest(itemID,rentedToId);

    if (transaction == null && request!=null)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Rent the item");
      alert.setHeaderText("Do you want to accept this rent?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK)
      {
        viewModel.acceptRent(itemID, rentedToId);
      }
    }
    else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("You already accepted this request");
      alert.showAndWait();
    }

  }

  public void onDecline()
  {
    int selectIndex = requestTable.getSelectionModel().getFocusedIndex();
    int itemID = requestTable.getItems().get(selectIndex).getItemId();
    int rentedToId = requestTable.getItems().get(selectIndex)
        .getRequestFromId();

    Transaction transaction = viewModel.getTransaction(itemID);
    Request request = viewModel.getRequest(itemID,rentedToId);

    if (transaction == null && request!=null)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Rent the item");
      alert.setHeaderText("Do you want to decline this rent?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK)
      {
        viewModel.declineRent(itemID, rentedToId);
      }
    }else
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Transaction is already created with this item");
      alert.showAndWait();
    }
  }

}
