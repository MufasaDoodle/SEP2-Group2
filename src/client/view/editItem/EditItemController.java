package client.view.editItem;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class EditItemController implements ViewController
{
  private @FXML TextField titleField;
  private @FXML TextField descriptionField;
  private @FXML ComboBox<String> categoryCombo;
  private @FXML TextField locationField;
  private @FXML TextField priceField;
  private @FXML TextField durationField;
  private @FXML Label errorLabel;
  private @FXML Button deleteButton;

  private EditItemViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getEditItemViewModel();

    viewModel.updateEditFields();

    categoryCombo.getItems().add("Gardening");
    categoryCombo.getItems().add("Gaming");
    categoryCombo.getItems().add("Cooking");
    categoryCombo.getItems().add("Electronics");
    categoryCombo.getItems().add("Home appliances");
    categoryCombo.getItems().add("Sports");

    titleField.textProperty().bindBidirectional(viewModel.titleProperty());
    descriptionField.textProperty()
        .bindBidirectional(viewModel.descriptionProperty());
    categoryCombo.getSelectionModel().select(viewModel.getItem().getCategory());
    locationField.textProperty()
        .bindBidirectional(viewModel.locationProperty());
    priceField.textProperty().bindBidirectional((viewModel.priceProperty()));
    durationField.textProperty()
        .bindBidirectional(viewModel.durationProperty());
  }

  public void onBackButton()
  {
    vh.openSeeListingScene();
  }

  public void onUpdate(ActionEvent actionEvent)
  {
    viewModel.updateListing(titleField.getText(), descriptionField.getText(),
        categoryCombo.getSelectionModel().getSelectedItem(),
        locationField.getText(), durationField.getText(),
        Double.parseDouble(priceField.getText()));
  }



  public void DeleteButton()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Item");
    alert.setHeaderText("Do you want to delete?");

    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK){
      viewModel.deleteItem();
      vh.openSeeListingScene();
    }
  }

}
