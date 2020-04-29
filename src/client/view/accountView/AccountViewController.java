package client.view.accountView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import client.view.login.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccountViewController implements ViewController
{
  private @FXML Label nameLabel;
  private @FXML Label addressLabel;
  private @FXML Label phoneLabel;
  private @FXML Label bioLabel;
  private @FXML Label avgRateLabel;




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

    viewModel.setOwner();
  }
}
