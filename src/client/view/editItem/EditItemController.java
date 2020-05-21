package client.view.editItem;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
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
  private @FXML Button deleteButton;
  private @FXML CheckBox availableBox;
  private @FXML CheckBox promoteBox;

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

    if (viewModel.getItemAv())
    {
      availableBox.setSelected(true);
    }
    else
    {
      availableBox.setSelected(false);
    }
    if (viewModel.getPromoted())
    {
      promoteBox.setSelected(true);
    }
    else
    {
      promoteBox.setSelected(false);
    }
  }

  public void onBackButton()
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

  public void onUpdate()
  {
    if (viewModel.accountCheck())
    {
      if (availableBox.isSelected() && promoteBox.isSelected())
      {
        Alert promote = new Alert(Alert.AlertType.CONFIRMATION);
        promote.setTitle("Promoted item");
        promote.setHeaderText(
            "You chose to promote your item, it costs 20Kr/days!");
        promote.setContentText("Click OK if you accept it!");
        Optional<ButtonType> result = promote.showAndWait();

        if (result.get() == ButtonType.OK)
        {
          viewModel
              .updateListing(titleField.getText(), descriptionField.getText(),
                  categoryCombo.getSelectionModel().getSelectedItem(),
                  locationField.getText(), durationField.getText(),
                  Double.parseDouble(priceField.getText()), "Available", "*");
        }
      }
      else if (availableBox.isSelected() && !promoteBox.isSelected())
      {
        viewModel
            .updateListing(titleField.getText(), descriptionField.getText(),
                categoryCombo.getSelectionModel().getSelectedItem(),
                locationField.getText(), durationField.getText(),
                Double.parseDouble(priceField.getText()), "Available", "");
      }
      else if (!availableBox.isSelected() && !promoteBox.isSelected())
      {
        viewModel
            .updateListing(titleField.getText(), descriptionField.getText(),
                categoryCombo.getSelectionModel().getSelectedItem(),
                locationField.getText(), durationField.getText(),
                Double.parseDouble(priceField.getText()), "Rented", "");
      }
      else if (!availableBox.isSelected() && promoteBox.isSelected())
      {
        Alert promote = new Alert(Alert.AlertType.CONFIRMATION);
        promote.setTitle("Promoted item");
        promote.setHeaderText(
            "You chose to promote your item, it costs 20Kr/days!");
        promote.setContentText("Click OK if you accept it!");
        Optional<ButtonType> result = promote.showAndWait();

        if (result.get() == ButtonType.OK)
        {
          viewModel
              .updateListing(titleField.getText(), descriptionField.getText(),
                  categoryCombo.getSelectionModel().getSelectedItem(),
                  locationField.getText(), durationField.getText(),
                  Double.parseDouble(priceField.getText()), "Rented", "*");
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

  public void DeleteButton()
  {
    if (viewModel.accountCheck())
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete Item");
      alert.setHeaderText("Do you want to delete?");

      Optional<ButtonType> result = alert.showAndWait();

      if (result.get() == ButtonType.OK)
      {
        viewModel.addDeletedItemId();
        viewModel.deleteItemFeedback();
        viewModel.deleteItemTransaction();
        viewModel.deleteItemRequest();
        viewModel.deleteItemReport();
        viewModel.deleteItem();
        vh.openSeeListingScene();
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
