package client.view.listingView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import stuffs.Listing;

public class SeeListingController implements ViewController
{
  @FXML private Label errorLabel;
  @FXML private TextField titleField;
  @FXML private TextField locationField;
  @FXML private ComboBox<String> categoryBox;
  @FXML private ComboBox<String> sortingBox;
  @FXML private TableView<Listing> listingTable;
  @FXML private TableColumn<String, String> promoteColumn;
  @FXML private TableColumn<String, String> titleColumn;
  @FXML private TableColumn<String, String> categoryColumn;
  @FXML private TableColumn<String, String> descriptionColumn;
  @FXML private TableColumn<String, String> locationColumn;
  @FXML private TableColumn<String, String> durationColumn;
  @FXML private TableColumn<String, String> priceColumn;
  @FXML private TableColumn<String, String> dateColumn;
  @FXML private TableColumn<String, String> statusColumn;
  @FXML private CheckBox isAvailable;
  private ViewHandler vh;
  private SeeListingViewModel vm;
  private String request = "";

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.vm = vmf.getSeeListingViewModel();

    titleField.setText("");
    locationField.setText("");
    vm.listOfListings();
    listingTable.setItems(vm.getListings());
    promoteColumn.setCellValueFactory(new PropertyValueFactory<>("promoted"));
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    descriptionColumn
        .setCellValueFactory(new PropertyValueFactory<>("description"));
    locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    statusColumn.setCellValueFactory(new PropertyValueFactory<>("rented"));

    categoryBox.getItems().add("All");
    categoryBox.getItems().add("Gardening");
    categoryBox.getItems().add("Gaming");
    categoryBox.getItems().add("Cooking");
    categoryBox.getItems().add("Electronics");
    categoryBox.getItems().add("Home appliances");
    categoryBox.getItems().add("Sports");
    categoryBox.getSelectionModel().selectFirst();


    sortingBox.getItems().add("Price low to high");
    sortingBox.getItems().add("Price high to low");
   

    sortingBox.getSelectionModel().selectFirst();
  }

  public void onCreateListing()
  {
    if (!vm.accountCheck())
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Account is banned");
      alert.showAndWait();
    }
    else
    {
      vh.openCreateListingScene();
    }
  }

  public void onSearchButton()
  {
    if (!(titleField.getText().equals("")) && (categoryBox.getSelectionModel()
        .getSelectedItem().equals("All")) && (locationField.getText()
        .equals("")) && !(isAvailable.isSelected()))
    {
      vm.listOfChoice("title", titleField.getText(), null, null);
      listingTable.setItems(vm.getListings());
      request = "title";
    }
    else if (!(titleField.getText().equals("")) && (categoryBox.getSelectionModel()
        .getSelectedItem().equals("All")) && (locationField.getText()
        .equals("")) && isAvailable.isSelected())
    {
      vm.listOfChoice("availableTitle", titleField.getText(), null, null);
      listingTable.setItems(vm.getListings());
      request = "availableTitle";
    }
    else if ((titleField.getText().equals("")) && (categoryBox
        .getSelectionModel().getSelectedItem().equals("All")) && (locationField
        .getText().equals("")) && !(isAvailable.isSelected()))
    {
      vm.listOfListings();
      listingTable.setItems(vm.getListings());
      request = "all";
    }
    else if (titleField.getText().equals("") && !(categoryBox
        .getSelectionModel().getSelectedItem().equals("All")) && (locationField
        .getText().equals("")) && !(isAvailable.isSelected()))
    {
      vm.listOfChoice("category", null,
          categoryBox.getSelectionModel().getSelectedItem(), null);
      listingTable.setItems(vm.getListings());
      request = "category";
    }
    else if (titleField.getText().equals("") && !(categoryBox
        .getSelectionModel().getSelectedItem().equals("All")) && (locationField
        .getText().equals("")) && isAvailable.isSelected())
    {
      vm.listOfChoice("availableCategory", null,
          categoryBox.getSelectionModel().getSelectedItem(), null);
      listingTable.setItems(vm.getListings());
      request = "availableCategory";
    }
    else if (titleField.getText().equals("") && categoryBox.getSelectionModel()
        .getSelectedItem().equals("All") && !(locationField.getText()
        .equals("")) && !(isAvailable.isSelected()))
    {
      vm.listOfChoice("location", null, null, locationField.getText());
      listingTable.setItems(vm.getListings());
      request = "location";
    }
    else if (titleField.getText().equals("") && categoryBox.getSelectionModel()
        .getSelectedItem().equals("All") && !(locationField.getText()
        .equals("")) && isAvailable.isSelected())
    {
      vm.listOfChoice("availableLocation", null, null, locationField.getText());
      listingTable.setItems(vm.getListings());
      request = "availableLocation";
    }
    else if (titleField.getText().equals("") && categoryBox.getSelectionModel()
        .getSelectedItem().equals("All") && locationField.getText()
        .equals("") && isAvailable.isSelected())
    {
      vm.listOfChoice("available", null, null, null);
      listingTable.setItems(vm.getListings());
      request = "available";
    }
    else if (
        !(titleField.getText().equals("") && categoryBox.getSelectionModel()
            .getSelectedItem().equals("All")) && locationField.getText()
            .equals("") && !(isAvailable.isSelected()))
    {
      vm.listOfChoice("titleCategory", titleField.getText(),
          categoryBox.getSelectionModel().getSelectedItem(), null);
      listingTable.setItems(vm.getListings());
      request = "titleCategory";
    }
    else if (
        !(titleField.getText().equals("") && categoryBox.getSelectionModel()
            .getSelectedItem().equals("All")) && locationField.getText()
            .equals("") && isAvailable.isSelected())
    {
      vm.listOfChoice("availableTitleCategory", titleField.getText(),
          categoryBox.getSelectionModel().getSelectedItem(), null);
      listingTable.setItems(vm.getListings());
      request = "availableTitleCategory";
    }
    else if (!(titleField.getText().equals("")) && categoryBox
        .getSelectionModel().getSelectedItem().equals("All") && !(locationField
        .getText().equals("")) && !(isAvailable.isSelected()))
    {
      vm.listOfChoice("titleLocation", titleField.getText(), null,
          locationField.getText());
      listingTable.setItems(vm.getListings());
      request = "titleLocation";
    }
    else if (!(titleField.getText().equals("")) && categoryBox
        .getSelectionModel().getSelectedItem().equals("All") && !(locationField
        .getText().equals("")) && isAvailable.isSelected())
    {
      vm.listOfChoice("availableTitleLocation", titleField.getText(), null,
          locationField.getText());
      listingTable.setItems(vm.getListings());
      request = "availableTitleLocation";
    }
    else if (!(categoryBox.getSelectionModel().getSelectedItem().equals("All")
        && locationField.getText().equals("")) && titleField.getText()
        .equals("") && !(isAvailable.isSelected()))
    {
      vm.listOfChoice("categoryLocation", null,
          categoryBox.getSelectionModel().getSelectedItem(),
          locationField.getText());
      listingTable.setItems(vm.getListings());
      request = "categoryLocation";
    }
    else if (!(categoryBox.getSelectionModel().getSelectedItem().equals("All")
        && locationField.getText().equals("")) && titleField.getText()
        .equals("") && isAvailable.isSelected())
    {
      vm.listOfChoice("availableCategoryLocation", null,
          categoryBox.getSelectionModel().getSelectedItem(),
          locationField.getText());
      listingTable.setItems(vm.getListings());
      request = "availableCategoryLocation";
    }
    else if (!(titleField.getText().equals("") && categoryBox
        .getSelectionModel().getSelectedItem().equals("All") && locationField
        .getText().equals("")) && !(isAvailable.isSelected()))
    {
      vm.listOfChoice("titleCategoryLocation", titleField.getText(),
          categoryBox.getSelectionModel().getSelectedItem(),
          locationField.getText());
      listingTable.setItems(vm.getListings());
      request = "titleCategoryLocation";
    }
    else if (!(titleField.getText().equals("") && categoryBox
        .getSelectionModel().getSelectedItem().equals("All") && locationField
        .getText().equals("")) && isAvailable.isSelected())
    {
      vm.listOfChoice("availableTitleCategoryLocation", titleField.getText(),
          categoryBox.getSelectionModel().getSelectedItem(),
          locationField.getText());
      listingTable.setItems(vm.getListings());
      request = "availableTitleCategoryLocation";
    }
  }

  public void onSortButton() {
    String priceLowHigh = "Price low to high";
    String priceHighLow = "Price high to low";
    if (request.equals("all")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("priceLowHigh", null, null, null);
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("priceHighLow", null, null, null);
        listingTable.setItems(vm.getListings());
      }
    }
    else if(request.equals("available"))
    {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("availablePriceLowHigh", null, null, null);
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("availablePriceHighLow", null, null, null);
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("title")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("titlePriceLowHigh", titleField.getText(), null, null);
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("titlePriceHighLow", titleField.getText(), null, null);
        listingTable.setItems(vm.getListings());
      }
    }
    else if(request.equals("availableTitle"))
    {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("availableTitlePriceLowHigh", titleField.getText(), null, null);
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("availableTitlePriceHighLow", titleField.getText(), null, null);
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("category")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("categoryPriceLowHigh", null,
            categoryBox.getSelectionModel().getSelectedItem(), null);
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("categoryPriceHighLow", null,
            categoryBox.getSelectionModel().getSelectedItem(), null);
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("availableCategory")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("availableCategoryPriceLowHigh", null,
            categoryBox.getSelectionModel().getSelectedItem(), null);
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("availableCategoryPriceHighLow", null,
            categoryBox.getSelectionModel().getSelectedItem(), null);
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("location")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("locationPriceLowHigh", null, null,
            locationField.getText());
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("locationPriceHighLow", null, null,
            locationField.getText());
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("availableLocation")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("availableLocationPriceLowHigh", null, null,
            locationField.getText());
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("availableLocationPriceHighLow", null, null,
            locationField.getText());
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("titleCategory")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("titleCategoryPriceLowHigh", titleField.getText(),
            categoryBox.getSelectionModel().getSelectedItem(), null);
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("titleCategoryPriceHighLow", titleField.getText(),
            categoryBox.getSelectionModel().getSelectedItem(), null);
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("availableTitleCategory")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("availableTitleCategoryPriceLowHigh", titleField.getText(),
            categoryBox.getSelectionModel().getSelectedItem(), null);
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("availableTitleCategoryPriceHighLow", titleField.getText(),
            categoryBox.getSelectionModel().getSelectedItem(), null);
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("titleLocation")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("titleLocationPriceLowHigh", titleField.getText(), null,
            locationField.getText());
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("titleLocationPriceHighLow", titleField.getText(), null,
            locationField.getText());
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("availableTitleLocation")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("availableTitleLocationPriceLowHigh", titleField.getText(), null,
            locationField.getText());
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("availableTitleLocationPriceHighLow", titleField.getText(), null,
            locationField.getText());
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("categoryLocation")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("categoryLocationPriceLowHigh", null,
            categoryBox.getSelectionModel().getSelectedItem(),
            locationField.getText());
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("categoryLocationPriceHighLow", null,
            categoryBox.getSelectionModel().getSelectedItem(),
            locationField.getText());
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("availableCategoryLocation")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("availableCategoryLocationPriceLowHigh", null,
            categoryBox.getSelectionModel().getSelectedItem(),
            locationField.getText());
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("availableCategoryLocationPriceHighLow", null,
            categoryBox.getSelectionModel().getSelectedItem(),
            locationField.getText());
        listingTable.setItems(vm.getListings());
      }
    }
    else if (request.equals("titleCategoryLocation")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("titleCategoryLocationPriceLowHigh",
            titleField.getText(),
            categoryBox.getSelectionModel().getSelectedItem(),
            locationField.getText());
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("titleCategoryLocationPriceHighLow",
            titleField.getText(),
            categoryBox.getSelectionModel().getSelectedItem(),
            locationField.getText());
        listingTable.setItems(vm.getListings());
      }
    }else if (request.equals("availableTitleCategoryLocation")) {
      if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceLowHigh)) {
        vm.listOfChoice("availableTitleCategoryLocationPriceLowHigh",
            titleField.getText(),
            categoryBox.getSelectionModel().getSelectedItem(),
            locationField.getText());
        listingTable.setItems(vm.getListings());
      } else if (sortingBox.getSelectionModel().getSelectedItem()
          .equals(priceHighLow)) {
        vm.listOfChoice("availableTitleCategoryLocationPriceHighLow",
            titleField.getText(),
            categoryBox.getSelectionModel().getSelectedItem(),
            locationField.getText());
        listingTable.setItems(vm.getListings());
      }
    }

  }

  public void onSeeItem()
  {
    if (vm.accountCheck())
    {
      int selectIndex = listingTable.getSelectionModel().getFocusedIndex();
      int itemID = listingTable.getItems().get(selectIndex).getId();

      if (vm.checkBannedAccount(itemID) == null)
      {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Item is deleted");
      }
      else
      {
        vm.setItem(itemID);
        if (!(vm.getDeletedItemIds().contains(itemID)))
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

  public void onMyAccount()
  {
    if (vm.accountCheck())
    {
      vm.setWhereFromOpen(true);
      vm.setAccountIDToLocalID();
      vh.openAccountScene();
    }
    else
    {
      Alert promote = new Alert(Alert.AlertType.WARNING);
      promote.setTitle("Warning");
      promote.setHeaderText("Account is banned");
      promote.showAndWait();
    }

  }

  public void logoffBtn()
  {
    vh.openLogInScene();
  }

}