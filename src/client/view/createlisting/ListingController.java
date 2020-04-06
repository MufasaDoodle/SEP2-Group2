package client.view.createlisting;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ListingController implements ViewController
{
  @FXML private Label listingErrorLabel;
  @FXML private TextField titleField;
  @FXML private TextArea descriptionArea;
  @FXML private TextField priceField;
  @FXML private TextField catField;
  @FXML private TextField locField;
  @FXML private TextField durationField;
  @FXML private TextField dateField;

  private ListingViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getListingViewModel();
    listingErrorLabel.textProperty().bind(viewModel.errorProperty());
  }

  public void createListingBtn(ActionEvent actionEvent)
  {
    viewModel.createListing(titleField.getText(), descriptionArea.getText(), priceField.getText(), catField.getText(), locField.getText(), durationField.getText(), dateField.getText());
  }

  public void backBtn(ActionEvent actionEvent)
  {
    viewModel.errorProperty().set("");
    vh.openAccountCreateScene();
  }
}
