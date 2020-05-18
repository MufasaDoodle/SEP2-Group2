package client.view.messages;

import client.model.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stuffs.ChatItem;

import java.util.List;

public class MessagesViewModel
{
  private ClientModel clientModel;
  private ObservableList<ChatItem> chatItems;

  public MessagesViewModel(ClientModel clientModel)
  {
    this.clientModel = clientModel;
  }

  void loadMessages()
  {
    chatItems = FXCollections.observableArrayList();
    List<ChatItem> filteredItems = clientModel.getMessagesInvolving();

    this.chatItems.addAll(filteredItems);
  }

  public List<ChatItem> getChatItems()
  {
    return chatItems;
  }

  public void setChatterID(int theirAccountID)
  {
    clientModel.setCurrentChatterID(theirAccountID);
  }
}
