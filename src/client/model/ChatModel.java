package client.model;

import shared.transferobjects.Message;
import stuffs.ChatItem;

import java.util.List;

public interface ChatModel
{
  String broadCastMessage(String msg);
  List<Message> getMessage();
  List<ChatItem> getMessagesInvolving();
  String getChatterName();
  void setChatterName(String chatterName);
  boolean checkOwner();
  void setLocalAccountID();
}
