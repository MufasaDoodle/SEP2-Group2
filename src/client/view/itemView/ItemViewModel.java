package client.view.itemView;

import client.model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class ItemViewModel
{
  private ClientModel clientModel;
  private StringProperty request, reply;

  public ItemViewModel(ClientModel clientModel){
    this.clientModel=clientModel;
    //clientModel.addListener("NewFeedback", this::onNewFeedback);
    request = new SimpleStringProperty();
    reply = new SimpleStringProperty();
  }

  void leaveFeedback(){
    //Todo

  }

  StringProperty requestProperty()
  {
    return request;
  }

  StringProperty replyProperty()
  {
    return reply;
  }

  private void onNewFeedback(PropertyChangeEvent evt){
    //Todo
  }

  public String getOwnerName(){ return clientModel.getUsername();}
  public String getItemName(){return clientModel.getItemName();}
  //todo
  /*public int getPrice(){return clientModel.}
  public String getLocation(){return clientModel.}
  public int getRate(){return clientModel.}*/

  void loadFeedback(){
    //Todo
  }

}
