package client.view.chatView;

import client.model.ChatModel;
import client.model.ListingsModel;
import client.model.MasterModelInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class ChatViewModel
{
  private MasterModelInterface masterModel;
  private ListingsModel listingsModel;
  private ChatModel chatModel;
  private ObservableList<String> messages;
  private StringProperty request, reply;

  public ChatViewModel(MasterModelInterface masterModel, ListingsModel listingsModel, ChatModel chatModel)
  {

    this.masterModel = masterModel;
    this.listingsModel = listingsModel;
    this.chatModel = chatModel;
    masterModel.addListener("NewMessage", this::onNewMessage);
    request = new SimpleStringProperty();
    reply = new SimpleStringProperty();
  }

  void send()
  {
    String input = request.get();
    if (input != null && !"".equals(input))
    {
      String result = chatModel.broadCastMessage(input);
      reply.set(result);
    }
  }

  StringProperty requestProperty()
  {
    return request;
  }

  StringProperty replyProperty()
  {
    return reply;
  }

  private void onNewMessage(PropertyChangeEvent evt)
  {
    Message message = (Message) evt.getNewValue();
    if (message.getToAccount() == masterModel.getCurrentAccountID() || message.getFromAccount() == masterModel.getCurrentAccountID())
    {
      addNewMessage((Message) evt.getNewValue());
    }
    //messages.add(((Message) evt.getNewValue()).getMessage());
  }

  private void addNewMessage(Message message)
  {
    //String yourName = clientModel.getAccountById(clientModel.getCurrentAccountID()).getName();
    //Use above if you want to have it use the user's name instead of writing "you:" in front of messages
    String theirName = chatModel.getChatterName();

    if (message.getFromAccount() == masterModel.getCurrentAccountID())
    {
      messages.add("You: " + message.getMessage());
    }
    else if (message.getFromAccount() == masterModel.getCurrentChatterID())
    {
      messages.add(theirName + ": " + message.getMessage());
    }
  }

  void loadMessages()
  {
    messages = FXCollections.observableArrayList();
    List<Message> messageList = chatModel.getMessage();
    for (Message message : messageList)
    {
      if (message != null)
      {
        addNewMessage(message);
      }
    }
  }

  public String getUsername()
  {
    return chatModel.getChatterName();
  }

  public String getItemName()
  {
    return listingsModel.getItemName();
  }

  public List<String> getMessage()
  {
    return messages;
  }

  public boolean accountCheck()
  {
    return masterModel.accountCheck();
  }

  public boolean chatterCheck()
  {
    return masterModel.getAccountById(masterModel.getCurrentChatterID()) != null;
  }

  public int getModerator()
  {
    return masterModel.getCurrentAccountID();
  }

}