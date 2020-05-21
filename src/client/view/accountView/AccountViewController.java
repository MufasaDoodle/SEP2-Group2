package client.view.accountView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import stuffs.Listing;
import stuffs.Request;
import stuffs.RequestListing;
import stuffs.TransactionListing;

import java.util.Optional;

public class AccountViewController implements ViewController
{
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
  private @FXML Button seeMessagesBtn;

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

    setOwner();

    if (!viewModel.checkOwner(nameLabel.getText()))
    {
      editTab.setDisable(true);

      seeMessagesBtn.setDisable(true);
      seeMessagesBtn.setVisible(false);

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
    categoryTransaction
        .setCellValueFactory(new PropertyValueFactory<>("category"));
    priceTransaction.setCellValueFactory(new PropertyValueFactory<>("price"));
    dateTransaction.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
    durationTransaction
        .setCellValueFactory(new PropertyValueFactory<>("duration"));
    nameTransaction
        .setCellValueFactory(new PropertyValueFactory<>("accountName"));
    statusTransaction.setCellValueFactory(new PropertyValueFactory<>("status"));
  }

  private void setOwner()
  {
    viewModel.setOwner();
  }

  @FXML public void onBackButton()
  {
    if (viewModel.accountCheck())
    {
      if (viewModel.itemCheck())
      {
        if (!(viewModel.getDeletedItemIds()
            .contains(viewModel.getCurrentItemId())))
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
      else
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Item is not set");
        alert.showAndWait();
      }

    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }
  }

  public void onBackToListing()
  {
    if (viewModel.accountCheck())
    {
      vh.openSeeListingScene();
    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }

  }

  @FXML public void onSeeItem()
  {
    if (viewModel.accountCheck())
    {
      int selectIndex = listingTable.getSelectionModel().getFocusedIndex();
      if (selectIndex < 0)
      {
        Alert promote = new Alert(Alert.AlertType.WARNING);
        promote.setTitle("Warning");
        promote.setHeaderText("Choose an item");
        promote.showAndWait();
      }
      else
      {
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

    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }
  }

  @FXML public void onMessage()
  {
    if (viewModel.accountCheck())
    {
      vh.openChatScene();
    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }

  }

  @FXML public void onSeeMessages()
  {
    if (viewModel.accountCheck())
    {
      vh.openMessagesScene();
    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }

  }

  @FXML public void onEditItem()
  {
    if (viewModel.accountCheck())
    {
      int selectIndex = listingTable.getSelectionModel().getFocusedIndex();
      if (selectIndex < 0)
      {
        Alert promote = new Alert(Alert.AlertType.WARNING);
        promote.setTitle("Warning");
        promote.setHeaderText("Choose an item");
        promote.showAndWait();
      }
      else
      {
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
    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }
  }

  @FXML public void tabEvent()
  {
    if (editTab.isSelected())
    {
      viewModel.updateEditFields();
    }
  }

  @FXML public void onUpdate()
  {
    if (viewModel.accountCheck())
    {
      viewModel.updateAccountInfo(emailField.getText(), passField.getText(),
          passField2.getText(), addressField.getText(), numberField.getText(),
          bioField.getText());
    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }

  }

  public void onAccept()
  {
    if (viewModel.accountCheck())
    {
      int selectIndex = requestTable.getSelectionModel().getFocusedIndex();
      if (selectIndex < 0)
      {
        Alert promote = new Alert(Alert.AlertType.WARNING);
        promote.setTitle("Warning");
        promote.setHeaderText("Choose a request");
        promote.showAndWait();
      }
      else
      {
        int itemID = requestTable.getItems().get(selectIndex).getItemId();
        int rentedToId = requestTable.getItems().get(selectIndex)
            .getRequestFromId();

        Listing listing = viewModel.getListing(itemID);
        Request request = viewModel.getRequest(itemID, rentedToId);

        if (listing != null)
        {
          if (listing.getRented().equals("Available") && request != null)
          {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Rent the item");
            alert.setHeaderText("Do you want to accept this rent?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
              viewModel.acceptRent(itemID, rentedToId);
              viewModel
                  .updateListing(listing.getTitle(), listing.getDescription(),
                      listing.getCategory(), listing.getLocation(),
                      listing.getDuration(), listing.getPrice(), "Rented",
                      listing.getId(), listing.getAccountId(),
                      listing.getPromoted());
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
        else
        {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Warning");
          alert.setHeaderText("Request deleted");
          alert.showAndWait();
        }
      }
    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }

  }

  public void onDecline()
  {
    if (viewModel.accountCheck())
    {
      int selectIndex = requestTable.getSelectionModel().getFocusedIndex();
      if (selectIndex < 0)
      {
        Alert promote = new Alert(Alert.AlertType.WARNING);
        promote.setTitle("Warning");
        promote.setHeaderText("Choose a request");
        promote.showAndWait();
      }
      else
      {
        int itemID = requestTable.getItems().get(selectIndex).getItemId();
        int rentedToId = requestTable.getItems().get(selectIndex)
            .getRequestFromId();

        Listing listing = viewModel.getListing(itemID);
        Request request = viewModel.getRequest(itemID, rentedToId);

        if (listing != null)
        {
          if (listing.getRented().equals("Available") && request != null)
          {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Rent the item");
            alert.setHeaderText("Do you want to decline this rent?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
              viewModel.declineRent(itemID, rentedToId);
            }
          }
          else
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert
                .setHeaderText("Transaction is already created with this item");
            alert.showAndWait();
          }
        }
        else
        {
          Alert promote = new Alert(Alert.AlertType.WARNING);
          promote.setTitle("Warning");
          promote.setHeaderText("Request deleted");
          promote.showAndWait();
        }
      }

    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }

  }

  public void onReport()
  {
    if (viewModel.accountCheck())
    {
      if (viewModel.getAccountID() == 1)
      {
        vh.openModeratorScene();
      }
      else
      {
        if (viewModel.getReportByAccountId())
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Account report");
          alert.setHeaderText("Do you want to report this account?");
          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK)
          {
            viewModel.reportAccount();
            Alert promote = new Alert(Alert.AlertType.INFORMATION);
            promote.setTitle("Account reported");
            promote.setHeaderText("Successfully reported the account!");
            promote.showAndWait();
          }
        }
        else
        {
          Alert promote = new Alert(Alert.AlertType.WARNING);
          promote.setTitle("Warning");
          promote.setHeaderText("Account already reported");
          promote.showAndWait();
        }
      }
    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }
  }

}
