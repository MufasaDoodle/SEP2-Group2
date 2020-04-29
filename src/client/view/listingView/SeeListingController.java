package client.view.listingView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import stuffs.Listing;

public class SeeListingController implements ViewController {
    @FXML private Label errorLabel;
    @FXML private TextField titleField;
    @FXML private TextField locationField;
    @FXML private ComboBox<String> categoryBox;
    @FXML private ComboBox sortingBox;
    @FXML private TableView<Listing> listingTable;
    @FXML private TableColumn<String, String> titleColumn;
    @FXML private TableColumn<String, String> categoryColumn;
    @FXML private TableColumn<String, String> descriptionColumn;
    @FXML private TableColumn<String, String> locationColumn;
    @FXML private TableColumn<String, String> durationColumn;
    @FXML private TableColumn<String, String> priceColumn;
    @FXML private TableColumn<String, String> dateColumn;
    private ViewHandler vh;
    private ViewModelFactory vmf;
    private SeeListingViewModel vm;
    private String request = "";

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.vm = vmf.getSeeListingViewModel();

        titleField.setText("");
        locationField.setText("");
        vm.listOfListings();
        listingTable.setItems(vm.getListings());
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        categoryBox.getItems().add("All");
        categoryBox.getItems().add("Gardening");
        categoryBox.getItems().add("Gaming");
        categoryBox.getItems().add("Cooking");
        categoryBox.getItems().add("Electronics");
        categoryBox.getItems().add("Home appliances");
        categoryBox.getItems().add("Sports");
        categoryBox.getSelectionModel().selectFirst();

        sortingBox.getItems().add("New to old");
        sortingBox.getItems().add("Old to new");
        sortingBox.getItems().add("Price low to high");
        sortingBox.getItems().add("Price high to low");
        sortingBox.getItems().add("Star rating  high to low");
        sortingBox.getItems().add("Star rating  low to high");

        sortingBox.getSelectionModel().selectFirst();
    }
    public void onCreateListing()
    {
        vh.openCreateListingScene();
    }
    public void onSearchButton()
    {
        if(!(titleField.getText().equals("")) && (categoryBox.getSelectionModel().getSelectedItem().equals("All")) && (locationField.getText().equals("")))
        {
            vm.listOfChoice("title", titleField.getText(), null, null);
            listingTable.setItems(vm.getListings());
            request = "title";
        }
        else if((titleField.getText().equals("")) && (categoryBox.getSelectionModel().getSelectedItem().equals("All")) && (locationField.getText().equals("")))
        {
            vm.listOfListings();
            listingTable.setItems(vm.getListings());
            request = "all";
        }
        else if(titleField.getText().equals("") && !(categoryBox.getSelectionModel().getSelectedItem().equals("All")) && (locationField.getText().equals("")))
        {
            vm.listOfChoice("category", null, categoryBox.getSelectionModel().getSelectedItem(), null);
            listingTable.setItems(vm.getListings());
            request = "category";
        }
        else if(titleField.getText().equals("") && categoryBox.getSelectionModel().getSelectedItem().equals("All") && !(locationField.getText().equals("")))
        {
            vm.listOfChoice("location", null, null, locationField.getText());
            listingTable.setItems(vm.getListings());
            request = "location";
        }
        else if(!(titleField.getText().equals("") && categoryBox.getSelectionModel().getSelectedItem().equals("All")) && locationField.getText().equals(""))
        {
            vm.listOfChoice("titleCategory", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), null);
            listingTable.setItems(vm.getListings());
            request = "titleCategory";
        }
        else if(!(titleField.getText().equals("")) && categoryBox.getSelectionModel().getSelectedItem().equals("All") && !(locationField.getText().equals("")))
        {
            vm.listOfChoice("titleLocation", titleField.getText(), null, locationField.getText());
            listingTable.setItems(vm.getListings());
            request = "titleLocation";
        }
        else if(!(categoryBox.getSelectionModel().getSelectedItem().equals("All") && locationField.getText().equals("")) && titleField.getText().equals(""))
        {
            vm.listOfChoice("categoryLocation", null, categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
            listingTable.setItems(vm.getListings());
            request = "categoryLocation";
        }
        else if(!(titleField.getText().equals("") && categoryBox.getSelectionModel().getSelectedItem().equals("All") && locationField.getText().equals("")))
        {
            vm.listOfChoice("titleCategoryLocation", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
            listingTable.setItems(vm.getListings());
            request = "titleCategoryLocation";
        }
    }
    public void onSortButton()
    {
        String newOld = "New to old";
        String oldNew = "Old to new";
        String ratingLowHigh = "Star rating low to high";
        String ratingHighLow = "Star rating high to low";
        String priceLowHigh = "Price low to high";
        String priceHighLow = "Price high to low";
        if(request.equals("all"))
        {
            if(sortingBox.getSelectionModel().getSelectedItem().equals(newOld))
            {
                vm.listOfChoice("newOld", null, null, null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(oldNew))
            {
                vm.listOfChoice("oldNew", null, null, null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceLowHigh))
            {
                vm.listOfChoice("priceLowHigh", null, null, null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceHighLow))
            {
                vm.listOfChoice("priceHighLow", null, null, null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingHighLow))
            {
                vm.listOfChoice("ratingHighLow", null, null, null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingLowHigh))
            {
                vm.listOfChoice("ratingLowHigh", null, null, null);
                listingTable.setItems(vm.getListings());
            }
        }
        else if(request.equals("title"))
        {
            if(sortingBox.getSelectionModel().getSelectedItem().equals(newOld))
            {
                vm.listOfChoice("titleNewOld", titleField.getText(), null, null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(oldNew))
            {
                vm.listOfChoice("titleOldNew", titleField.getText(), null, null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceLowHigh))
            {
                vm.listOfChoice("titlePriceLowHigh", titleField.getText(), null, null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceHighLow))
            {
                vm.listOfChoice("titlePriceHighLow", titleField.getText() , null, null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingHighLow))
            {
                vm.listOfChoice("titleRatingHighLow", titleField.getText(), null, null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingLowHigh))
            {
                vm.listOfChoice("titleRatingLowHigh", titleField.getText(), null, null);
                listingTable.setItems(vm.getListings());
            }
        }
        else if(request.equals("category"))
        {
            if(sortingBox.getSelectionModel().getSelectedItem().equals(newOld))
            {
                vm.listOfChoice("categoryNewOld", null, categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(oldNew))
            {
                vm.listOfChoice("categoryOldNew", null, categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceLowHigh))
            {
                vm.listOfChoice("categoryPriceLowHigh", null, categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceHighLow))
            {
                vm.listOfChoice("categoryPriceHighLow", null, categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingHighLow))
            {
                vm.listOfChoice("categoryRatingHighLow", null, categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingLowHigh))
            {
                vm.listOfChoice("categoryRatingLowHigh", null, categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
        }
        else if(request.equals("location"))
        {
            if(sortingBox.getSelectionModel().getSelectedItem().equals(newOld))
            {
                vm.listOfChoice("locationNewOld", null, null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(oldNew))
            {
                vm.listOfChoice("locationOldNew", null, null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceLowHigh))
            {
                vm.listOfChoice("locationPriceLowHigh", null, null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceHighLow))
            {
                vm.listOfChoice("locationPiceHighLow", null, null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingHighLow))
            {
                vm.listOfChoice("locationRatingHighLow", null, null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingLowHigh))
            {
                vm.listOfChoice("locationRatingLowHigh", null, null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
        }
        else if(request.equals("titleCategory"))
        {
            if(sortingBox.getSelectionModel().getSelectedItem().equals(newOld))
            {
                vm.listOfChoice("titleCategoryNewOld", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(oldNew))
            {
                vm.listOfChoice("titleCategoryOldNew", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceLowHigh))
            {
                vm.listOfChoice("titleCategoryPriceLowHigh", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceHighLow))
            {
                vm.listOfChoice("titleCategoryPriceHighLow", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingHighLow))
            {
                vm.listOfChoice("titleCategoryRatingHighLow", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingLowHigh))
            {
                vm.listOfChoice("titleCategoryRatingLowHigh", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), null);
                listingTable.setItems(vm.getListings());
            }
        }
        else if(request.equals("titleLocation"))
        {
            if(sortingBox.getSelectionModel().getSelectedItem().equals(newOld))
            {
                vm.listOfChoice("titleLocationNewOld", titleField.getText(), null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(oldNew))
            {
                vm.listOfChoice("titleLocationOldNew", titleField.getText(), null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceLowHigh))
            {
                vm.listOfChoice("titleLocationPriceLowHigh", titleField.getText(), null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceHighLow))
            {
                vm.listOfChoice("titleLocationPriceHighLow", titleField.getText(), null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingHighLow))
            {
                vm.listOfChoice("titleLocationRatingHighLow", titleField.getText(), null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingLowHigh))
            {
                vm.listOfChoice("titleLocationRatingLowHigh", titleField.getText(), null, locationField.getText());
                listingTable.setItems(vm.getListings());
            }
        }
        else if(request.equals("categoryLocation"))
        {
            if(sortingBox.getSelectionModel().getSelectedItem().equals(newOld))
            {
                vm.listOfChoice("categoryLocationNewOld", null, categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(oldNew))
            {
                vm.listOfChoice("categoryLocationOldNew", null, categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceLowHigh))
            {
                vm.listOfChoice("categoryLocationPriceLowHigh", null, categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceHighLow))
            {
                vm.listOfChoice("categoryLocationPriceHighLow", null, categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingHighLow))
            {
                vm.listOfChoice("categoryLocationRatingHighLow", null, categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingLowHigh))
            {
                vm.listOfChoice("categoryLocationRatingLowHigh", null, categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
        }
        else if(request.equals("titleCategoryLocation"))
        {
            if(sortingBox.getSelectionModel().getSelectedItem().equals(newOld))
            {
                vm.listOfChoice("titleCategoryLocationNewOld", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(oldNew))
            {
                vm.listOfChoice("titleCategoryLocationOldNew", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceLowHigh))
            {
                vm.listOfChoice("titleCategoryLocationPriceLowHigh", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(priceHighLow))
            {
                vm.listOfChoice("titleCategoryLocationPriceHighLow", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingHighLow))
            {
                vm.listOfChoice("titleCategoryLocationRatingHighLow", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
            else if(sortingBox.getSelectionModel().getSelectedItem().equals(ratingLowHigh))
            {
                vm.listOfChoice("titleCategoryLocationRatingLowHigh", titleField.getText(), categoryBox.getSelectionModel().getSelectedItem(), locationField.getText());
                listingTable.setItems(vm.getListings());
            }
        }
    }

    public void onSeeItem(ActionEvent actionEvent)
    {
        int selectIndex = listingTable.getSelectionModel().getFocusedIndex();
        int itemID = listingTable.getItems().get(selectIndex).getId();
        vm.setItem(itemID);
        vh.openItemScene();
    }
}
