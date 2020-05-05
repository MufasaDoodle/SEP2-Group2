package client.view.accountView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import stuffs.Listing;

public class AccountViewController implements ViewController
{
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

    setOwner();

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
  }

  private void setOwner(){
    viewModel.setOwner();
  }

  public void onBackButton()
  {
    setOwner();
    viewModel.listOfOwnerListings();
    listingTable.setItems(viewModel.getListings());
    vh.openItemScene();
  }

  public void onSeeItem(ActionEvent actionEvent)
  {
    int selectIndex = listingTable.getSelectionModel().getFocusedIndex();
    int itemID = listingTable.getItems().get(selectIndex).getId();
    viewModel.setItem(itemID);
    vh.openItemScene();
  }
}
