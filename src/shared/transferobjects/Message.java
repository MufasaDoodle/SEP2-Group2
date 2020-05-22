package shared.transferobjects;

import java.io.Serializable;

public class Message implements Serializable
{
  private String message;
  private int fromAccount;
  private int toAccount;

  public Message(String message, int fromAccount, int toAccount)
  {
    this.message = message;
    this.fromAccount = fromAccount;
    this.toAccount = toAccount;
  }

  public String getMessage()
  {
    return message;
  }

  public int getFromAccount()
  {
    return fromAccount;
  }

  public int getToAccount()
  {
    return toAccount;
  }
}