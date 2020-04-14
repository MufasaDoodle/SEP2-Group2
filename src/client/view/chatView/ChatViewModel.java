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
  private ObservableList<Message> messages;
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
    messages.add((Message) evt.getNewValue());
  }

  void loadMessages()
  {
    List<Message> messageList = clientModel.getMessage();
    messages = FXCollections.observableArrayList(messageList);
  }

  public String getUsername()
  {
    return clientModel.getUsername();
  }

  public String getItemName()
  {
    return clientModel.getItemName();
  }

  public List<Message> getMessage()
  {
    return messages;
  }

}



