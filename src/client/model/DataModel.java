package client.model;

import client.networking.Client;
import shared.transferobjects.Account;

import java.beans.PropertyChangeSupport;

/**
 * A class that holds information needed by all the other model managers
 * @author Group 2
 */
public class DataModel
{
  private Client client;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  private int currentItemID = 0;
  private String currentAccountName;
  private Account moderatedAccount;

  private int currentAccountID;
  private int viewingAccountID;
  private int feedbackId;

  private int currentChatterID;
  private String chatterName;
  private String itemName;

  private boolean fromListingViewOpen;
  private boolean fromModeratorOpen;

  public Client getClient()
  {
    return client;
  }

  public void setClient(Client client)
  {
    this.client = client;
  }

  public int getCurrentItemID()
  {
    return currentItemID;
  }

  public void setCurrentItemID(int currentItemID)
  {
    this.currentItemID = currentItemID;
  }

  public String getCurrentAccountName()
  {
    return currentAccountName;
  }

  public void setCurrentAccountName(String currentAccountName)
  {
    this.currentAccountName = currentAccountName;
  }

  public Account getModeratedAccount()
  {
    return moderatedAccount;
  }

  public void setModeratedAccount(Account moderatedAccount)
  {
    this.moderatedAccount = moderatedAccount;
  }

  public int getCurrentAccountID()
  {
    return currentAccountID;
  }

  public void setCurrentAccountID(int currentAccountID)
  {
    this.currentAccountID = currentAccountID;
  }

  public int getViewingAccountID()
  {
    return viewingAccountID;
  }

  public void setViewingAccountID(int viewingAccountID)
  {
    this.viewingAccountID = viewingAccountID;
  }

  public int getFeedbackId()
  {
    return feedbackId;
  }

  public void setFeedbackId(int feedbackId)
  {
    this.feedbackId = feedbackId;
  }

  public int getCurrentChatterID()
  {
    return currentChatterID;
  }

  public void setCurrentChatterID(int currentChatterID)
  {
    this.currentChatterID = currentChatterID;
  }

  public String getChatterName()
  {
    return chatterName;
  }

  public void setChatterName(String chatterName)
  {
    this.chatterName = chatterName;
  }

  public String getItemName()
  {
    return itemName;
  }

  public void setItemName(String itemName)
  {
    this.itemName = itemName;
  }

  public boolean getFromListingViewOpen()
  {
    return fromListingViewOpen;
  }

  public void setFromListingViewOpen(boolean fromListingViewOpen)
  {
    this.fromListingViewOpen = fromListingViewOpen;
  }

  public boolean isFromModeratorOpen()
  {
    return fromModeratorOpen;
  }

  public void setFromModeratorOpen(boolean fromModeratorOpen)
  {
    this.fromModeratorOpen = fromModeratorOpen;
  }
}
