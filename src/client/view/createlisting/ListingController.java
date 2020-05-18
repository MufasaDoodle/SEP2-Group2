package client.view.createlisting;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class ListingController implements ViewController
{
  @FXML private TextField titleField;
  @FXML private TextArea descriptionArea;
  @FXML private TextField priceField;
  @FXML private TextField locField;
  @FXML private TextField durationField;
  @FXML private ComboBox<String> categoryBox;
  @FXML private CheckBox promoteBox;

  private ListingViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getListingViewModel();
    categoryBox.getItems().add("Gardening");
    categoryBox.getItems().add("Gaming");
    categoryBox.getItems().add("Cooking");
    categoryBox.getItems().add("Electronics");
    categoryBox.getItems().add("Home appliances");
    categoryBox.getItems().add("Sports");
  }

  public void createListingBtn()
  {

    if (promoteBox.isSelected())
    {
      Alert promote = new Alert(Alert.AlertType.INFORMATION);
      promote.setTitle("Promoted item");
      promote.setHeaderText(
          "You chose to promote your item, it costs 20Kr/days");
      promote.showAndWait();
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Create listing");
    alert.setHeaderText("Do you want to add this new listing?");

    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK)
    {
      if (promoteBox.isSelected())
      {

        if (viewModel
            .createListing(titleField.getText(), descriptionArea.getText(),
                priceField.getText(),
                categoryBox.getSelectionModel().getSelectedItem(),
                locField.getText(), durationField.getText(), "*"))
        {
          titleField.setText("");
          descriptionArea.setText("");
          priceField.setText("");
          categoryBox.getSelectionModel().selectFirst();
          locField.setText("");
          durationField.setText("");
          promoteBox.setSelected(false);
        }
      }
      else
      {
        if (viewModel
            .createListing(titleField.getText(), descriptionArea.getText(),
                priceField.getText(),
                categoryBox.getSelectionModel().getSelectedItem(),
                locField.getText(), durationField.getText(), ""))
        {
          titleField.setText("");
          descriptionArea.setText("");
          priceField.setText("");
          categoryBox.getSelectionModel().selectFirst();
          locField.setText("");
          durationField.setText("");
          promoteBox.setSelected(false);
        }
      }
    }
  }

  public void backBtn()
  {
    vh.openLogInScene();
  }

  public void onChatView()
  {
    vh.openChatScene();
  }

  public void onSeeListing()
  {
    vh.openSeeListingScene();
  }
}
