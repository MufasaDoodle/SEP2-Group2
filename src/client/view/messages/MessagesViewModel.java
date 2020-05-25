package client.view.messages;

import client.model.ChatModel;
import client.model.ListingsModel;
import client.model.MasterModelInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stuffs.ChatItem;

import java.util.List;

public class MessagesViewModel
{
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private ChatModel chatModel;
  private ObservableList<ChatItem> chatItems;

  public MessagesViewModel(MasterModelInterface masterModel, ListingsModel listingsModel, ChatModel chatModel)
  {
    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
    this.chatModel = chatModel;
  }

  void loadMessages()
  {
    chatItems = FXCollections.observableArrayList();
    List<ChatItem> filteredItems = chatModel.getMessagesInvolving();

    this.chatItems.addAll(filteredItems);
  }

  public List<ChatItem> getChatItems()
  {
    return chatItems;
  }

  public void setChatterID(int theirAccountID)
  {
    masterModel.setCurrentChatterID(theirAccountID);
  }

  public boolean accountCheck(){
    return masterModel.accountCheck();
  }

  public boolean chatterCheck(){
    return masterModel.getAccountById(masterModel.getCurrentChatterID())
        != null;
  }

  public boolean isModeratorOpen(){
    return masterModel.getCurrentAccountID() == 1;
  }
}