package client.model;

import shared.transferobjects.Message;
import shared.transferobjects.ChatItem;

import java.util.ArrayList;
import java.util.List;

public class ChatModelManager implements ChatModel
{
  private DataModel dataModel;
  private MasterModelInterface masterModel;

  public ChatModelManager(DataModel dataModel, MasterModelInterface masterModel)
  {
    this.dataModel = dataModel;
    this.masterModel = masterModel;
  }

  @Override public String broadCastMessage(String msg)
  {
    return dataModel.getClient().broadCastMessage(msg, dataModel.getCurrentAccountID(), dataModel.getCurrentChatterID());
  }

  @Override public List<Message> getMessage()
  {
    return dataModel.getClient().getMessage(dataModel.getCurrentAccountID(), dataModel.getCurrentChatterID());
  }

  @Override public List<ChatItem> getMessagesInvolving()
  {
    List<Message> messagesList = dataModel.getClient().getAllMessagesInvolvingAccount(dataModel.getCurrentAccountID());
    List<ChatItem> chatItemList = new ArrayList<>();
    List<Integer> seenSenderIDs = new ArrayList<>();

    for (Message message : messagesList)
    {
      //checks if sender is not the user's own account, and if it hasn't already been added in the list, it adds them
      if (!(message.getFromAccount() == dataModel.getCurrentAccountID()))
      {
        if (!seenSenderIDs.contains(message.getFromAccount()))
        {
          seenSenderIDs.add(message.getFromAccount());
          String chatterName = dataModel.getClient().getAccountById(message.getFromAccount()).getName();
          chatItemList.add(new ChatItem(chatterName, message.getFromAccount()));
        }
      }

      //checks if receiver is not the user's own account, and so on
      else if (!(message.getToAccount() == dataModel.getCurrentAccountID()))
      {
        if (!seenSenderIDs.contains(message.getToAccount()))
        {
          seenSenderIDs.add(message.getToAccount());
          String chatterName = dataModel.getClient().getAccountById(message.getToAccount()).getName();
          chatItemList.add(new ChatItem(chatterName, message.getToAccount()));
        }
      }
    }

    return chatItemList;
  }

  @Override public String getChatterName()
  {
    return dataModel.getChatterName();
  }

  @Override public void setChatterName(String chatterName)
  {
    dataModel.setChatterName(chatterName);
  }

  @Override public boolean checkOwner()
  {
    if (dataModel.getCurrentAccountID() == dataModel.getViewingAccountID())
    {
      return true;
    }
    return false;
  }

  @Override public void setLocalAccountID()
  {
    dataModel.setViewingAccountID(dataModel.getCurrentAccountID());
  }
}
