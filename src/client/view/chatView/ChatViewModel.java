package client.view.chatView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobjects.Message;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class ChatViewModel
{
  private ClientModel clientModel;
  private ObservableList<String> messages;
  private StringProperty request, reply;

  public ChatViewModel(ClientModel clientModel)
  {

    this.clientModel = clientModel;
    clientModel.addListener("NewMessage", this::onNewMessage);
    request = new SimpleStringProperty();
    reply = new SimpleStringProperty();
  }

  void send()
  {
    String input = request.get();
    if (input != null && !"".equals(input))
    {
      String result = clientModel.broadCastMessage(input);
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
    addNewMessage((Message) evt.getNewValue());
    //messages.add(((Message) evt.getNewValue()).getMessage());
  }

  private void addNewMessage(Message message)
  {
    //String yourName = clientModel.getAccountById(clientModel.getCurrentAccountID()).getName();
    //Use above if you want to have it use the user's name instead of writing "you:" in front of messages
    String theirName = clientModel.getChatterName();

    if (message.getFromAccount() == clientModel.getCurrentAccountID())
    {
      messages.add("You: " + message.getMessage());
    }
    else if (message.getFromAccount() == clientModel.getCurrentChatterID())
    {
      messages.add(theirName + ": " + message.getMessage());
    }
  }

  void loadMessages()
  {
    messages = FXCollections.observableArrayList();
    List<Message> messageList = clientModel.getMessage();
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
    return clientModel.getChatterName();
  }

  public String getItemName()
  {
    return clientModel.getItemName();
  }

  public List<String> getMessage()
  {
    return messages;
  }

}



