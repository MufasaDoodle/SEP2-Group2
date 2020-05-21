package client.view.messages;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.view.ViewController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    messagesColumn
        .setCellValueFactory(new PropertyValueFactory<>("chatterName"));
    //messagesColumn.setCellValueFactory(cell -> new SimpleObjectProperty(cell.getValue()));
    viewModel.loadMessages();
    messagesTableView
        .setItems((ObservableList<ChatItem>) viewModel.getChatItems());
  }

  public void onToChat()
  {
    if (viewModel.accountCheck())
    {
      int selectIndex = messagesTableView.getSelectionModel().getFocusedIndex();
      if (messagesTableView.getItems().get(selectIndex) == null)
      {
        Alert promote = new Alert(Alert.AlertType.WARNING);
        promote.setTitle("Warning");
        promote.setHeaderText("Account is deleted");
        promote.showAndWait();
      }
      else
      {
        int theirAccountID = messagesTableView.getItems().get(selectIndex)
            .getChatterID();
        viewModel.setChatterID(theirAccountID);
        vh.openChatScene();
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

  public void onBack()
  {
    if (viewModel.accountCheck())
    {
      if (viewModel.isModeratorOpen())
      {
        vh.openModeratorScene();
      }
      else
      {
        vh.openAccountScene();
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
