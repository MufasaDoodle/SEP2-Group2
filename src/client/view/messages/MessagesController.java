package client.view.messages;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import stuffs.ChatItem;

public class MessagesController implements ViewController
{

  @FXML private TableView<ChatItem> messagesTableView;
  @FXML private TableColumn<String, ChatItem> messagesColumn;
  private MessagesViewModel viewModel;
  private ViewHandler vh;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.vh = vh;
    viewModel = vmf.getMessagesViewModel();
    messagesColumn.setCellValueFactory(new PropertyValueFactory<>("chatterName"));
    //messagesColumn.setCellValueFactory(cell -> new SimpleObjectProperty(cell.getValue()));
    viewModel.loadMessages();
    messagesTableView.setItems((ObservableList<ChatItem>) viewModel.getChatItems());
  }

  public void onToChat(ActionEvent actionEvent)
  {
    int selectIndex = messagesTableView.getSelectionModel().getFocusedIndex();
    int theirAccountID = messagesTableView.getItems().get(selectIndex).getChatterID();
    viewModel.setChatterID(theirAccountID);
    vh.openChatScene();
  }

  public void onBack(ActionEvent actionEvent)
  {
    vh.openAccountScene();
  }
}
