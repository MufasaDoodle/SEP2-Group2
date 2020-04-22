package client.view.chatView;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import shared.transferobjects.Message;

public class ChatViewController implements ViewController
{
  private ChatViewModel viewModel;
  private ViewHandler vh;

  @FXML private TableView<Message> tableView;
  @FXML private TableColumn<String, Message> inputColumn;

  @FXML private TextArea messageArea;

  @FXML private Label ownerName;
  @FXML private Label itemName;
  @FXML private Label errorLabel;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    this.viewModel = vmf.getChatViewModel();
    ownerName.textProperty().setValue("Owner: " + viewModel.getUsername());
    //Todo itemName ownerName
    itemName.textProperty().setValue("Item: " + viewModel.getItemName());
    System.out.println("OwnerName: " + viewModel.getUsername());
    System.out.println("ItemName: " + viewModel.getItemName());
    viewModel.loadMessages();
    inputColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
    messageArea.textProperty().bindBidirectional(viewModel.requestProperty());
    tableView.setItems((ObservableList<Message>) viewModel.getMessage());
    messageArea.textProperty().set("");

  }

  public void onSendButton()
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

  public void onSend(KeyEvent e)
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

  public void onBackButton()
  {
    //Todo
    vh.openItemScene();
  }
}
