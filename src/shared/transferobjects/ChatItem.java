package shared.transferobjects;

import java.io.Serializable;

public class ChatItem implements Serializable
{
  private String chatterName;
  private int chatterID;

  public ChatItem(String chatterName, int chatterID)
  {
    this.chatterName = chatterName;
    this.chatterID = chatterID;
  }

  public String getChatterName()
  {
    return chatterName;
  }

  public int getChatterID()
  {
    return chatterID;
  }
}