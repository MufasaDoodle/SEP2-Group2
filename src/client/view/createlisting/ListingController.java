package client.view.createlisting;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ListingController implements ViewController
{
  @FXML private Label listingErrorLabel;
  @FXML private TextField titleField;
  @FXML private TextArea descriptionArea;
  @FXML private TextField priceField;
  @FXML private TextField locField;
  @FXML private TextField durationField;
  @FXML private ComboBox<String> categoryBox;

  private ListingViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getListingViewModel();
    listingErrorLabel.textProperty().bind(viewModel.errorProperty());
    categoryBox.getItems().add("Gardening");
    categoryBox.getItems().add("Gaming");
    categoryBox.getItems().add("Cooking");
    categoryBox.getItems().add("Electronics");
    categoryBox.getItems().add("Home appliances");
    categoryBox.getItems().add("Sports");
  }

  public void createListingBtn(ActionEvent actionEvent)
  {
    if (viewModel.createListing(titleField.getText(), descriptionArea.getText(), priceField.getText(),categoryBox.getSelectionModel().getSelectedItem(), locField.getText(), durationField.getText()))
    {
      titleField.setText("");
      descriptionArea.setText("");
      priceField.setText("");
      categoryBox.getSelectionModel().selectFirst();
      locField.setText("");
      durationField.setText("");
    }
  }

  public void backBtn(ActionEvent actionEvent)
  {
    viewModel.errorProperty().set("");
    vh.openLogInScene();
  }

  public void onChatView(ActionEvent actionEvent)
  {
    vh.openChatScene();
  }
  public void onSeeListing()
  {
    vh.openSeeListingScene();
  }
}
