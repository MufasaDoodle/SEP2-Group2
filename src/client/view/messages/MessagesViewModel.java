package client.view.messages;

import client.model.ClientModel;
import client.model.ListingsModel;
import client.model.MasterModelInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stuffs.ChatItem;

import java.util.List;

public class MessagesViewModel
{
  private ClientModel clientModel;
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private ObservableList<ChatItem> chatItems;

  public MessagesViewModel(ClientModel clientModel, MasterModelInterface masterModel, ListingsModel listingsModel)
  {
    this.clientModel = clientModel;
    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
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