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
  @FXML private TextField titleLabel;
  @FXML private TextArea descriptionArea;
  @FXML private TextField priceLabel;
  @FXML private TextField catLabel;
  @FXML private TextField locLabel;
  @FXML private TextField durationLabel;
  @FXML private TextField dateLabel;

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
    viewModel.createListing(titleLabel.getText(), descriptionArea.getText(), priceLabel.getText(), catLabel.getText(), locLabel.getText(), durationLabel.getText(), dateLabel.getText());
  }

  public void backBtn(ActionEvent actionEvent)
  {
    //vh.openSomeScene();
  }
}
