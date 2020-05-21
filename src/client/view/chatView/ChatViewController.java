package client.view.chatView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class ChatViewController implements ViewController
{
  private ChatViewModel viewModel;
  private ViewHandler vh;

  @FXML private TableView<String> tableView;
  @FXML private TableColumn<String, String> inputColumn;

  @FXML private TextArea messageArea;

  @FXML private Label ownerName;
  @FXML private Label itemName;
  @FXML private Label errorLabel;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getChatViewModel();
    ownerName.textProperty().setValue("User: " + viewModel.getUsername());
    //Todo itemName ownerName
    itemName.textProperty().setValue("Item: " + viewModel.getItemName());
    System.out.println("OwnerName: " + viewModel.getUsername());
    System.out.println("ItemName: " + viewModel.getItemName());
    viewModel.loadMessages();
    inputColumn
        .setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue()));
    messageArea.textProperty().bindBidirectional(viewModel.requestProperty());
    tableView.setItems((ObservableList<String>) viewModel.getMessage());
    messageArea.textProperty().set("");
  }

  public void onSendButton()
  {
    if (viewModel.accountCheck() && viewModel.chatterCheck())
    {
      if (!(messageArea.textProperty().get().equals("")))
      {
        viewModel.send();
        messageArea.clear();
      }
      else
      {
        errorLabel.textProperty().set("Please write something...");
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

  public void onSend(KeyEvent e)
  {
    if (viewModel.accountCheck())
    {
      if (e.getCode().toString().equals("ENTER"))
      {
        if (!(messageArea.textProperty().get().equals("")))
        {
          viewModel.send();
          messageArea.clear();
        }
        else
        {
          errorLabel.textProperty().set("Please write something...");
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

  public void onBackButton()
  {
    if (viewModel.accountCheck())
    {
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
}
